package kono.materialreplication.common.data;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;

import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;

import kono.materialreplication.common.data.machine.single.annihilator.AnnihilatorCustomLogic;

public class MRRecipeTypes {

    public static final GTRecipeType DECONSTRUCTOR_RECIPE = GTRecipeTypes
            .register("deconstructor", GTRecipeTypes.ELECTRIC)
            .setMaxIOSize(1, 1, 1, 2).setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY)
            .setSlotOverlay(false, true, GuiTextures.LIGHTNING_OVERLAY_2)
            .setSlotOverlay(true, true, false, GuiTextures.MOLECULAR_OVERLAY_3)
            .setSlotOverlay(true, true, true, GuiTextures.MOLECULAR_OVERLAY_4)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MASS_FAB, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CENTRIFUGE);

    public static final GTRecipeType ANNIHILATOR_RECIPE = GTRecipeTypes
            .register("annihilator", GTRecipeTypes.ELECTRIC)
            .setMaxIOSize(1, 1, 1, 0).setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY)
            .setSlotOverlay(false, true, GuiTextures.FLUID_SLOT)
            .setSlotOverlay(true, false, GuiTextures.LIGHTNING_OVERLAY_1)
            .setProgressBar(GuiTextures.PROGRESS_BAR_RECYCLER, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .addCustomRecipeLogic(new AnnihilatorCustomLogic())
            .setSound(GTSoundEntries.MACERATOR);

    public static final GTRecipeType REPLICATOR_RECIPE = GTRecipeTypes
            .register("replicator", GTRecipeTypes.ELECTRIC)
            .setMaxIOSize(2, 1, 3, 1).setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.DATA_ORB_OVERLAY)
            .setSlotOverlay(false, true, false, GuiTextures.ATOMIC_OVERLAY_1)
            .setSlotOverlay(false, true, true, GuiTextures.ATOMIC_OVERLAY_2)
            .setSlotOverlay(true, false, GuiTextures.DUST_OVERLAY)
            .setSlotOverlay(true, true, GuiTextures.VIAL_OVERLAY_1)
            .setProgressBar(GuiTextures.PROGRESS_BAR_REPLICATOR, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.ASSEMBLER);
    /*
     * .onRecipeBuild((recipeBuilder, provider) -> {
     * 
     * });
     */

    public static void init() {}
}
