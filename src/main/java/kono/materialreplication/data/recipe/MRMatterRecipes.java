package kono.materialreplication.data.recipe;

import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;

import org.jetbrains.annotations.NotNull;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.data.recipe.builder.GTRecipeBuilder;

import kono.materialreplication.MaterialReplicationConfig;
import kono.materialreplication.registry.MRMaterials;
import kono.materialreplication.registry.materials.MRMaterialFlags;

import static kono.materialreplication.MRUtils.*;
import static kono.materialreplication.registry.MRRecipeTypes.*;

public class MRMatterRecipes {

    public static int deconstructionBaseTime = MaterialReplicationConfig.INSTANCE.deconstruct.DeconstructionBaseTime;
    public static long deconstructionVoltage = MaterialReplicationConfig.INSTANCE.deconstruct.DeconstructionVoltage;

    public static void register(Consumer<FinishedRecipe> provider) {
        for (Material material : GTCEuAPI.materialManager.getRegisteredMaterials()) {
            if (material.hasFlag(MRMaterialFlags.DISABLE_DECONSTRUCTION)) break;
            if (!MaterialReplicationConfig.INSTANCE.deconstruct.DeconstructOnlyElements) {
                registerDeconstructRecipe(material, provider);
            } else if (material.isElement()) {
                registerDeconstructRecipe(material, provider);
            }
        }
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
                .chancedOutput(TagPrefix.dustTiny, MRMaterials.PrimalMatter, 1, 1, 0)
                .duration(getMass(material) * deconstructionBaseTime).EUt(deconstructionVoltage).save(provider);
    }
}
