package kono.materialreplication;

import net.minecraft.resources.ResourceLocation;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

public class MRUtils {

    public static final String MODID = "materialreplication";

    public static final String NAME = "MaterialReplication";

    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public static ResourceLocation mrId(String name) {
        return new ResourceLocation(MODID, name);
    }

    public static final int sec = 20;
    public static final int min = 1200;

    public static int getProtons(@NotNull Material material) {
        return (int) material.getProtons();
    }

    public static int getNeutrons(@NotNull Material material) {
        return (int) material.getProtons();
    }

    public static int getMass(@NotNull Material material) {
        return (int) material.getMass();
    }
}
