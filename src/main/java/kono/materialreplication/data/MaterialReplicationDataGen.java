package kono.materialreplication.data;

import kono.materialreplication.data.lang.EnglishLangHandler;
import kono.materialreplication.common.data.MRRegistration;

import com.tterrag.registrate.providers.ProviderType;

public class MaterialReplicationDataGen {

    public static void init() {
        MRRegistration.REGISTRATE.addDataGenerator(ProviderType.LANG, EnglishLangHandler::init);
    }
}
