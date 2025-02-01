package kono.materialreplication.data.recipe;

import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;

public class MRRecipeManager {

    public static void addRecipes(Consumer<FinishedRecipe> provider) {
        MRMatterRecipes.register(provider);
        MRMiscRecipes.register(provider);
        MRMachineRecipes.register(provider);
    }

    public static void removeRecipes(Consumer<ResourceLocation> consumer) {
        MRMiscRecipes.removeRecipe(consumer);
    }
}
