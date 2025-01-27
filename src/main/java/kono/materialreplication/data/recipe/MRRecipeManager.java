package kono.materialreplication.data.recipe;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class MRRecipeManager {

    public static void init(Consumer<FinishedRecipe> provider) {
        MRMatterRecipes.register(provider);
    }
}
