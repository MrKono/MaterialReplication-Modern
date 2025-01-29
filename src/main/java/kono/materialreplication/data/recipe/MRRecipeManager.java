package kono.materialreplication.data.recipe;

import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;

public class MRRecipeManager {

    public static void init(Consumer<FinishedRecipe> provider) {
        MRMatterRecipes.register(provider);
        MRMiscRecipes.register(provider);
    }
}
