package kono.materialreplication.data.recipe;

import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

import kono.materialreplication.common.data.MRItems;
import kono.materialreplication.common.data.MRRecipeTypes;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static kono.materialreplication.MRUtils.mrId;

public class MRMiscRecipes {

    public static void register(Consumer<FinishedRecipe> provider) {
        scrapRecipe(provider);
    }

    /**
     * Fake recipe for annihilator
     **/
    public static void scrapRecipe(Consumer<FinishedRecipe> provider) {
        ItemStack itemStack = new ItemStack(Items.BARRIER);
        itemStack.setHoverName(Component.translatable("materialreplication.annihilator.item"));
        MRRecipeTypes.ANNIHILATOR_RECIPE.recipeBuilder(mrId("item"))
                .inputItems(itemStack)
                .chancedOutput(MRItems.SCRAP.asStack(), 1, 0)
                .duration(600).EUt(VA[LV]).save(provider);

        itemStack.setHoverName(Component.translatable("materialreplication.annihilator.fluid"));
        MRRecipeTypes.ANNIHILATOR_RECIPE.recipeBuilder(mrId("fluid"))
                .inputItems(itemStack)
                .inputFluids(new FluidStack(Fluids.WATER, 1000))
                .chancedOutput(MRItems.SCRAP.asStack(), 1, 0)
                .duration(600).EUt(VA[LV]).save(provider);
    }
}
