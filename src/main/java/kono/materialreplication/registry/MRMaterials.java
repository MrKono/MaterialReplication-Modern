package kono.materialreplication.registry;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import kono.materialreplication.registry.materials.MRMaterial;

public class MRMaterials {

    public static Material PrimalMatter;
    public static Material ChargedMatter;
    public static Material NeutralMatter;
    public static Material MatterAmplifier;

    public static void init() {
        MRMaterial.init();
        modifyTagPrefix();
    }

    public static void modifyTagPrefix() {
        TagPrefix.dustSmall.setIgnored(PrimalMatter);
        TagPrefix.dustSmall.setIgnored(MatterAmplifier);
    }

    public static void materialFlags() {}
}
