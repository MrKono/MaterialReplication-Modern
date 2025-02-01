package kono.materialreplication.common.data;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;

import kono.materialreplication.MaterialReplicationConfig;
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
        materialFlagAdditionInit();
    }

    public static void post() {
        materialFlagAdditionPost();
    }

    public static void modifyTagPrefix() {
        TagPrefix.dustSmall.setIgnored(PrimalMatter);
        TagPrefix.dustSmall.setIgnored(MatterAmplifier);
    }

    public static void materialFlagAdditionInit() {
        for (String str : MaterialReplicationConfig.INSTANCE.deconstruct.materialDeconstructionBlacklist) {
            if (!str.isEmpty()) {
                Material mat = GTCEuAPI.materialManager.getMaterial(str);
                if (mat == null) continue;
                mat.addFlags(MRMaterialFlags.DISABLE_DECONSTRUCTION);
            }
        }
        for (String str : MaterialReplicationConfig.INSTANCE.replicate.materialReplicationBlacklist) {
            if (!str.isEmpty()) {
                Material mat = GTCEuAPI.materialManager.getMaterial(str);
                if (mat == null) continue;
                mat.addFlags(MRMaterialFlags.DISABLE_REPLICATION);
            }
        }
    }

    public static void materialFlagAdditionPost() {
        for (Material material : GTCEuAPI.materialManager.getRegisteredMaterials()) {
            if (material.getChemicalFormula().isEmpty()) {
                for (String dec : MaterialReplicationConfig.INSTANCE.deconstruct.materialDeconstructionWhitelist) {
                    if (material != GTCEuAPI.materialManager.getMaterial(dec)) {
                        material.addFlags(MRMaterialFlags.DISABLE_DECONSTRUCTION);
                    }
                }
                for (String rep : MaterialReplicationConfig.INSTANCE.replicate.materialReplicationWhitelist) {
                    if (material != GTCEuAPI.materialManager.getMaterial(rep)) {
                        material.addFlags(MRMaterialFlags.DISABLE_REPLICATION);
                    }
                }
            }
        }
    }
}
