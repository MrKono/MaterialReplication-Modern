package kono.materialreplication.data;

import com.tterrag.registrate.providers.ProviderType;
import kono.materialreplication.data.lang.EnglishLangHandler;
import kono.materialreplication.registry.MRRegistration;

public class MaterialReplicationDataGen {

    public static void init() {
        MRRegistration.REGISTRATE.addDataGenerator(ProviderType.LANG, EnglishLangHandler::init);
    }
}
