package kono.materialreplication;

import net.minecraft.resources.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

public class MRUtils {

    public static final String MODID = "materialreplication";

    public static ResourceLocation mrId(String name) {
        return new ResourceLocation(MODID, name);
    }

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
