package kono.materialreplication.data.recipe;

import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;

import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import kono.materialreplication.common.data.MRMachines;

import static com.gregtechceu.gtceu.data.recipe.GTCraftingComponents.*;
import static com.gregtechceu.gtceu.data.recipe.misc.MetaTileEntityLoader.registerMachineRecipe;

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
    }
}
