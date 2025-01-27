package kono.materialreplication.registry;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;

public class MRRecipeTypes {

    public static final GTRecipeType DECONSTRUCTOR_RECIPE = GTRecipeTypes.register("deconstructor", GTRecipeTypes.ELECTRIC)
            .setMaxIOSize(1, 1, 1, 2).setEUIO(IO.IN)
            .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY)
            .setSlotOverlay(false, true, GuiTextures.LIGHTNING_OVERLAY_2)
            .setSlotOverlay(true, true, false, GuiTextures.MOLECULAR_OVERLAY_3)
            .setSlotOverlay(true, true, true, GuiTextures.MOLECULAR_OVERLAY_4)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MASS_FAB, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CENTRIFUGE);

    public static void init() {}
}
