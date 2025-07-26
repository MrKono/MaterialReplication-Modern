package kono.materialreplication.data.recipe;

import java.util.function.Consumer;

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
    }
}
