package kono.materialreplication.data.lang;

import com.gregtechceu.gtceu.data.lang.LangHandler;

import kono.materialreplication.registry.MRMaterials;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public class EnglishLangHandler extends LangHandler {

    public static void init(RegistrateLangProvider provider) {
        // Materials
        LangHandler.replace(provider, MRMaterials.PrimalMatter.getUnlocalizedName(), "Primal Matter");
        LangHandler.replace(provider, MRMaterials.ChargedMatter.getUnlocalizedName(), "Charged Matter");
        LangHandler.replace(provider, MRMaterials.NeutralMatter.getUnlocalizedName(), "Neutral Matter");
        LangHandler.replace(provider, MRMaterials.MatterAmplifier.getUnlocalizedName(), "Matter Amplifier");
    }
}
