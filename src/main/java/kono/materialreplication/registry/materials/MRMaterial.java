package kono.materialreplication.registry.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;

import kono.materialreplication.registry.MRMaterials;

import static kono.materialreplication.MRUtils.mrId;

public class MRMaterial {

    public static void init() {
        MRMaterials.PrimalMatter = new Material.Builder(mrId("primal_matter"))
                .dust()
                .liquid(10)
                .color(0xc86edc)
                .iconSet(MaterialIconSet.NETHERSTAR).buildAndRegister();

        MRMaterials.ChargedMatter = new Material.Builder(mrId("charged_matter"))
                .liquid(1)
                .color(0x0064c8)
                .buildAndRegister();

        MRMaterials.NeutralMatter = new Material.Builder(mrId("neutral_matter"))
                .liquid(1)
                .color(0xc80a14)
                .buildAndRegister();

        MRMaterials.MatterAmplifier = new Material.Builder(mrId("matter_amplifier"))
                .dust()
                .liquid(300)
                .color(0xda70d6)
                .buildAndRegister();
    }
}
