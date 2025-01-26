package kono.materialreplication;

import net.minecraft.resources.ResourceLocation;

public class MRUtils {

    public static final String MODID = "materialreplication";


    public static ResourceLocation mrId(String name) {
        return new ResourceLocation(MODID, name);
    }
}
