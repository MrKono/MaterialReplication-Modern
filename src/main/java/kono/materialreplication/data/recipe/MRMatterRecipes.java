package kono.materialreplication.data.recipe;

import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.NotNull;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;

import kono.materialreplication.MaterialReplicationConfig;
import kono.materialreplication.common.data.MRItems;
import kono.materialreplication.common.data.MRMaterials;

import static com.gregtechceu.gtceu.api.GTValues.LV;
import static com.gregtechceu.gtceu.api.GTValues.VA;
import static kono.materialreplication.MRUtils.*;
import static kono.materialreplication.common.data.MRRecipeTypes.DECONSTRUCTOR_RECIPE;
import static kono.materialreplication.common.data.MRRecipeTypes.REPLICATOR_RECIPE;

public class MRMatterRecipes {

    public static String REPLICATE_NBT_TAG = "replicate_research";
    public static String REPLICATE_ID_NBT_TAG = "material_id";

    public static int deconstructionBaseTime = MaterialReplicationConfig.INSTANCE.deconstruct.DeconstructionBaseTime >
            1 ?
                    MaterialReplicationConfig.INSTANCE.deconstruct.DeconstructionBaseTime : 600;
    public static int replicationBaseTime = MaterialReplicationConfig.INSTANCE.replicate.ReplicationBaseTime > 1 ?
            MaterialReplicationConfig.INSTANCE.replicate.ReplicationBaseTime : 1200;
    public static int scanTIme = MaterialReplicationConfig.INSTANCE.replicate.ScanTime > 1 ?
            MaterialReplicationConfig.INSTANCE.replicate.ScanTime : 1200;
    public static long deconstructionVoltage = MaterialReplicationConfig.INSTANCE.deconstruct.DeconstructionVoltage >
            1 ?
                    MaterialReplicationConfig.INSTANCE.deconstruct.DeconstructionVoltage : VA[LV];
    public static long replicationVoltage = MaterialReplicationConfig.INSTANCE.replicate.ReplicationVoltage > 1 ?
            MaterialReplicationConfig.INSTANCE.replicate.ReplicationVoltage : VA[LV];
    public static long scanVoltage = MaterialReplicationConfig.INSTANCE.replicate.ScanVoltage > 1 ?
            MaterialReplicationConfig.INSTANCE.replicate.ScanVoltage : VA[LV];

    public static void register(Consumer<FinishedRecipe> provider) {
        matterRecipe(provider);
        amplifierRecipe(provider);
    }

    public static void matterRecipe(Consumer<FinishedRecipe> provider) {
        for (Material material : GTCEuAPI.materialManager.getRegisteredMaterials()) {
            if (material.getUnlocalizedName().isEmpty()) break;
            // if (material.hasFlag(MRMaterialFlags.DISABLE_DECONSTRUCTION)) break;
            if (!MaterialReplicationConfig.INSTANCE.deconstruct.DeconstructOnlyElements) {
                registerDeconstructRecipe(material, provider);
            } else if (material.isElement()) {
                registerDeconstructRecipe(material, provider);
            }
            registerReplicateRecipe(material, provider);
        }
    }

    public static void amplifierRecipe(Consumer<FinishedRecipe> provider) {
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder(mrId("scrap"))
                .inputItems(MRItems.SCRAP)
                .outputFluids(MRMaterials.MatterAmplifier.getFluid(1))
                .duration(1200).EUt(30).save(provider);
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder(mrId("scrap_box"))
                .inputItems(MRItems.SCRAP_BOX)
                .outputFluids(MRMaterials.MatterAmplifier.getFluid(9))
                .chancedFluidOutput(MRMaterials.MatterAmplifier.getFluid(1), "3/4", 0)
                .chancedFluidOutput(MRMaterials.MatterAmplifier.getFluid(1), "1/10", 0)
                .chancedFluidOutput(MRMaterials.MatterAmplifier.getFluid(1), "1/100", 0)
                .chancedFluidOutput(MRMaterials.MatterAmplifier.getFluid(1), "1/100", 0)
                .duration(12000).EUt(30).save(provider);

        // Primal -> Charged & Neutral
        GTRecipeTypes.CENTRIFUGE_RECIPES.recipeBuilder(mrId("primal_centrifuge"))
                .inputFluids(MRMaterials.PrimalMatter.getFluid(1))
                .outputFluids(MRMaterials.ChargedMatter.getFluid(1))
                .outputFluids(MRMaterials.NeutralMatter.getFluid(1))
                .duration(12000).EUt(16).save(provider);

        // MatterAmplifier
        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(mrId("charged_matter_amplified"))
                .inputFluids(MRMaterials.MatterAmplifier.getFluid(500))
                .inputFluids(MRMaterials.ChargedMatter.getFluid(500))
                .outputFluids(MRMaterials.ChargedMatter.getFluid(1000))
                .duration(replicationBaseTime).EUt(replicationVoltage).save(provider);

        GTRecipeTypes.CHEMICAL_RECIPES.recipeBuilder(mrId("neutral_matter_amplified"))
                .inputFluids(MRMaterials.MatterAmplifier.getFluid(500))
                .inputFluids(MRMaterials.NeutralMatter.getFluid(500))
                .outputFluids(MRMaterials.NeutralMatter.getFluid(1000))
                .duration(replicationBaseTime).EUt(replicationVoltage).save(provider);
    }

    public static void registerDeconstructRecipe(@NotNull Material material, Consumer<FinishedRecipe> provider) {
        GTRecipeBuilder builder = DECONSTRUCTOR_RECIPE.recipeBuilder(mrId(material.getName()));
        if (material.hasProperty(PropertyKey.DUST)) {
            builder.inputItems(TagPrefix.dust, material);
        } else if (material.hasProperty(PropertyKey.FLUID)) {
            builder.inputFluids(material.getFluid(1000));
        }
        builder.outputFluids(MRMaterials.ChargedMatter.getFluid(getProtons(material)))
                .outputFluids(MRMaterials.NeutralMatter.getFluid(getNeutrons(material)))
                .chancedOutput(TagPrefix.dustTiny, MRMaterials.PrimalMatter, "1/100", 0)
                .duration(getMass(material) * deconstructionBaseTime).EUt(deconstructionVoltage).save(provider);
    }

    public static void registerReplicateRecipe(@NotNull Material material, Consumer<FinishedRecipe> provider) {
        String name = material.getName();
        String nameUn = material.getUnlocalizedName();
        ItemStack usb = MRItems.USB_STICK_SAVED.asStack();
        CompoundTag tag = usb.getOrCreateTag();
        CompoundTag compound = new CompoundTag();
        compound.putString(REPLICATE_ID_NBT_TAG, name);
        tag.put(REPLICATE_NBT_TAG, compound);
        usb.setHoverName(Component.translatable("materialreplication.replicator.usb.data",
                Component.translatable(nameUn)));

        // Replication
        GTRecipeBuilder replicateBuilder = REPLICATOR_RECIPE.recipeBuilder(mrId(name))
                .notConsumable(usb)
                .duration(getMass(material) * replicationBaseTime).EUt(replicationVoltage);
        if (material.hasProperty(PropertyKey.DUST)) {
            replicateBuilder.outputItems(TagPrefix.dust, material);
        } else if (material.hasProperty(PropertyKey.FLUID)) {
            replicateBuilder.outputFluids(material.getFluid(1000));
        }
        if (getProtons(material) > 1) {
            replicateBuilder.inputFluids(MRMaterials.ChargedMatter.getFluid(getProtons(material)));
        }
        if (getNeutrons(material) > 1) {
            replicateBuilder.inputFluids(MRMaterials.NeutralMatter.getFluid(getNeutrons(material)));
        }
        replicateBuilder.save(provider);

        // Scan
        GTRecipeBuilder scanBuilder = GTRecipeTypes.SCANNER_RECIPES.recipeBuilder(mrId(name))
                .inputItems(MRItems.USB_STICK).duration(scanTIme).EUt(scanVoltage);
        if (material.hasProperty(PropertyKey.DUST)) {
            scanBuilder.inputItems(TagPrefix.dust, material);
        } else if (material.hasProperty(PropertyKey.FLUID)) {
            scanBuilder.inputFluids(material.getFluid(1000))
                    .outputFluids(material.getFluid(1000));
        }
        scanBuilder.outputItems(usb).save(provider);
    }
}
