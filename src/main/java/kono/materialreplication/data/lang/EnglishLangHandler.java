package kono.materialreplication.data.lang;

import com.gregtechceu.gtceu.data.lang.LangHandler;

import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.format.ConfigFormats;
import dev.toma.configuration.config.value.ConfigValue;
import dev.toma.configuration.config.value.ObjectValue;
import kono.materialreplication.MRUtils;
import kono.materialreplication.MaterialReplicationConfig;
import kono.materialreplication.common.data.MRMaterials;

import com.tterrag.registrate.providers.RegistrateLangProvider;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

        // Config
        dfs(provider, new HashSet<>(),
                Configuration.registerConfig(MaterialReplicationConfig.class, ConfigFormats.yaml()).getValueMap());
    }

    private static void dfs(RegistrateLangProvider provider, Set<String> added, Map<String, ConfigValue<?>> map) {
        for (var entry : map.entrySet()) {
            var id = entry.getValue().getId();
            if (added.add(id)) {
                provider.add(String.format("config.%s.option.%s", MRUtils.MODID, id), id);
            }
            if (entry.getValue() instanceof ObjectValue objectValue) {
                dfs(provider, added, objectValue.get());
            }
        }
    }
}
