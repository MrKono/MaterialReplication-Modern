package kono.materialreplication.common.data.materials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;

import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import kono.materialreplication.common.data.MRMaterials;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
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
                .liquid(300)
                .color(0xda70d6)
                .buildAndRegister();

        MRMaterials.Trinaquadalloy = new Material.Builder(mrId("trinaquadalloy"))
                .ingot().fluid()
                .color(0x281832).iconSet(MaterialIconSet.BRIGHT)
                .flags(GENERATE_PLATE)
                .components(GTMaterials.Trinium, 6, GTMaterials.Naquadah, 2, GTMaterials.Carbon, 1)
                .blastTemp(8747, BlastProperty.GasTier.HIGHER, GTValues.VA[GTValues.ZPM], 1200)
                .buildAndRegister();
    }
}
