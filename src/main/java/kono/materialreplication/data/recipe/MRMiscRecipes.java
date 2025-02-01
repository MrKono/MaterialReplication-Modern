package kono.materialreplication.data.recipe;

import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;

import kono.materialreplication.common.data.MRItems;
import kono.materialreplication.common.data.MRMaterials;
import kono.materialreplication.common.data.MRRecipeTypes;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static kono.materialreplication.MRUtils.*;

public class MRMiscRecipes {

    public static void register(Consumer<FinishedRecipe> provider) {
        scrapRecipe(provider);
        miscRecipe(provider);
    }

    /**
     * Fake RecipeConfig for annihilator
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

    /**
     * Misc
     */
    public static void miscRecipe(Consumer<FinishedRecipe> provider) {
        // USB Stick
        GTRecipeTypes.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder(mrId("usb_stick"))
                .inputItems(GTItems.PLASTIC_CIRCUIT_BOARD)
                .inputItems(GTItems.CENTRAL_PROCESSING_UNIT, 2)
                .inputItems(GTItems.NAND_MEMORY_CHIP, 32)
                .inputItems(GTItems.RANDOM_ACCESS_MEMORY, 4)
                .inputItems(TagPrefix.wireFine, GTMaterials.Electrum, 16)
                .inputItems(TagPrefix.plate, GTMaterials.Polyethylene, 4)
                .outputItems(MRItems.USB_STICK)
                .solderMultiplier(2)
                .cleanroom(CleanroomType.CLEANROOM)
                .duration(20 * sec).EUt(VA[MV]).save(provider);

        // USB Stick (Saved) -> USB Stick
        VanillaRecipeHelper.addShapelessRecipe(provider, "usb_data_removal", MRItems.USB_STICK.asStack(),
                MRItems.USB_STICK_SAVED);

        // Primal (tiny) -> Primal (normal)
        GTRecipeTypes.COMPRESSOR_RECIPES.recipeBuilder(mrId("tiny_dust_assembling_primal_matter"))
                .inputItems(TagPrefix.dustTiny, MRMaterials.PrimalMatter, 9)
                .outputItems(TagPrefix.dust, MRMaterials.PrimalMatter)
                .duration(10 * sec).EUt(VA[HV]).save(provider);

        // Scrap -> Scrap Box
        GTRecipeTypes.COMPRESSOR_RECIPES.recipeBuilder(mrId("compressing_scrap"))
                .inputItems(MRItems.SCRAP, 9)
                .outputItems(MRItems.SCRAP_BOX)
                .duration(10 * sec).EUt(VA[HV]).save(provider);
    }

    /**
     * Remove RecipeConfig
     */
    public static void removeRecipe(Consumer<ResourceLocation> registry) {
        // Crafting
        registry.accept(new ResourceLocation("gtceu:shaped/tiny_dust_assembling_primal_matter"));

        // Machine
        registry.accept(new ResourceLocation("gtceu:packer/package_primal_matter_tiny_dust"));
    }
}
