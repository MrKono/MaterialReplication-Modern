package kono.materialreplication.common.data;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;

import kono.materialreplication.common.data.materials.MRMaterial;
import kono.materialreplication.common.data.materials.MRMaterialFlags;

public class MRMaterials {

    public static Material PrimalMatter;
    public static Material ChargedMatter;
    public static Material NeutralMatter;
    public static Material MatterAmplifier;

    public static void init() {
        MRMaterial.init();
        modifyTagPrefix();
    }

    public static void lowest() {
        materialFlagsLow();
    }

    public static void modifyTagPrefix() {
        TagPrefix.dustSmall.setIgnored(PrimalMatter);
        TagPrefix.dustSmall.setIgnored(MatterAmplifier);
    }

    public static void materialFlagsLow() {
        for (Material material : GTCEuAPI.materialManager.getRegisteredMaterials()) {
            if (material.getChemicalFormula().isEmpty()) {
                material.addFlags(MRMaterialFlags.DISABLE_DECONSTRUCTION);
            }
        }
    }
}
