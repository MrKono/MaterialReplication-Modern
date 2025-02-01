package kono.materialreplication.data.lang;

import com.gregtechceu.gtceu.data.lang.LangHandler;

import kono.materialreplication.common.data.MRMaterials;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public class EnglishLangHandler extends LangHandler {

    public static void init(RegistrateLangProvider provider) {
        // Materials
        replace(provider, MRMaterials.PrimalMatter.getUnlocalizedName(), "Primal Matter");
        replace(provider, MRMaterials.ChargedMatter.getUnlocalizedName(), "Charged Matter");
        replace(provider, MRMaterials.NeutralMatter.getUnlocalizedName(), "Neutral Matter");
        replace(provider, MRMaterials.MatterAmplifier.getUnlocalizedName(), "Matter Amplifier");

        // Misc
        replace(provider, "materialreplication.annihilator.item", "Any Items");
        replace(provider, "materialreplication.annihilator.fluid", "Any Fluids");
        replace(provider, "materialreplication.replicator.usb.data", "§r§3Replicate Data: §r§a%s");

        // Recipe Name
        replace(provider, "gtceu.deconstructor", "Material Deconstruction");
        replace(provider, "gtceu.annihilator", "Annihilation");
        replace(provider, "gtceu.replicator", "Material Replication");
    }
}
