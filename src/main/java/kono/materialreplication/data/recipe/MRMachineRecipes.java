package kono.materialreplication.data.recipe;

import java.util.function.Consumer;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.sun.jna.platform.win32.OaIdl;
import net.minecraft.data.recipes.FinishedRecipe;

import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.config.ConfigHolder;

import kono.materialreplication.common.data.MRMachines;
import kono.materialreplication.common.data.MRMaterials;

import static com.gregtechceu.gtceu.common.data.GCYMBlocks.CASING_ATOMIC;
import static com.gregtechceu.gtceu.data.recipe.GTCraftingComponents.*;
import static com.gregtechceu.gtceu.data.recipe.misc.MetaTileEntityLoader.registerMachineRecipe;
import static kono.materialreplication.MRUtils.mrId;

public class MRMachineRecipes {

    public static void register(Consumer<FinishedRecipe> provider) {
        // Deconstructor
        registerMachineRecipe(provider, MRMachines.DECONSTRUCTOR, "CSC", "FHF", "CQC",
                'C', BETTER_CIRCUIT,
                'S', SENSOR,
                'F', FIELD_GENERATOR,
                'H', HULL,
                'Q', CABLE_QUAD);
        // Annihilator
        registerMachineRecipe(provider, MRMachines.ANNIHILATOR, "GCG", "PHP", "WCW",
                'G', new MaterialEntry(TagPrefix.dust, GTMaterials.Glowstone),
                'C', CIRCUIT,
                'P', PISTON,
                'W', CABLE,
                'H', HULL);
        // Replicator
        registerMachineRecipe(provider, MRMachines.REPLICATOR, "EFE", "CHC", "SQS",
                'E', EMITTER,
                'F', FIELD_GENERATOR,
                'C', BETTER_CIRCUIT,
                'H', HULL,
                'S', SENSOR,
                'Q', CABLE_QUAD);

        // Atomic Casing
        GTRecipeTypes.ASSEMBLER_RECIPES.recipeBuilder(mrId("atomic_casing"))
                .inputItems(TagPrefix.plate, MRMaterials.Trinaquadalloy, 6)
                .inputItems(TagPrefix.frameGt, GTMaterials.NaquadahAlloy)
                .circuitMeta(6)
                .outputItems(CASING_ATOMIC.asStack(ConfigHolder.INSTANCE.recipes.casingsPerCraft))
                .EUt(16).duration(50)
                .addMaterialInfo(true).save(provider);

        // Large Deconstructor
        VanillaRecipeHelper.addShapedRecipe(provider, mrId("large_deconstructor"), MRMachines.LARGE_DECONSTRUCTOR.asStack(),
                "FCF", "EDE", "FQF",
                'C', CustomTags.ZPM_CIRCUITS,
                'D', MRMachines.DECONSTRUCTOR[GTValues.LuV].asStack(),
                'F', GTItems.FIELD_GENERATOR_LuV.asStack(),
                'E', GTItems.EMITTER_LuV.asStack(),
                'Q', new MaterialEntry(TagPrefix.cableGtQuadruple, GTMaterials.YttriumBariumCuprate));

        // Large Annihilator
        VanillaRecipeHelper.addShapedRecipe(provider, mrId("large_annihilator"), MRMachines.LARGE_ANNIHILATOR.asStack(),
                "PCP", "BSB", "MWM",
                'P', GTItems.ELECTRIC_PISTON_IV.asStack(),
                'C', CustomTags.IV_CIRCUITS,
                'B', GTItems.COMPONENT_GRINDER_TUNGSTEN.asStack(),
                'S', MRMachines.ANNIHILATOR[GTValues.IV].asStack(),
                'M', GTItems.ELECTRIC_MOTOR_IV.asStack(),
                'W', new MaterialEntry(TagPrefix.cableGtDouble, GTMaterials.Platinum));

        // Large Replicator
        VanillaRecipeHelper.addShapedRecipe(provider, mrId("large_replicator"), MRMachines.LARGE_REPLICATOR.asStack(),
                "EDE", "CMC", "SPS",
                'E', GTItems.EMITTER_UV.asStack(),
                'D', new MaterialEntry(TagPrefix.block, GTMaterials.Neutronium),
                'C', CustomTags.UHV_CIRCUITS,
                'M', MRMachines.REPLICATOR[GTValues.UV].asStack(),
                'S', GTItems.SENSOR_UV.asStack(),
                'P', new MaterialEntry(TagPrefix.pipeHugeFluid, GTMaterials.Neutronium));
    }
}
