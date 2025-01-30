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
        provider.add("materialreplication.annihilator.item", "Any Items");
        provider.add("materialreplication.annihilator.fluid", "Any Fluids");
    }
}
