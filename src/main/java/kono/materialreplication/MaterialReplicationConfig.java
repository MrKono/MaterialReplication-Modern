package kono.materialreplication;

import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.Configurable;
import dev.toma.configuration.config.format.ConfigFormats;

@Config(id = MRUtils.MODID)
public class MaterialReplicationConfig {

    public static MaterialReplicationConfig INSTANCE;

    public static void init() {
        INSTANCE = Configuration.registerConfig(MaterialReplicationConfig.class, ConfigFormats.yaml())
                .getConfigInstance();
    }

    @Configurable
    public RecipeConfigs recipe = new RecipeConfigs();
    @Configurable
    public DeconstructConfigs deconstruct = new DeconstructConfigs();
    @Configurable
    public ReplicateConfigs replicate = new ReplicateConfigs();

    public static class DeconstructConfigs {

        @Configurable
        @Configurable.Comment({ "Deconstruction Base time in tick (int).", "Default : 600 tick (30 Sec.)",
                "Duration : BaseTime * material's Average mass" })
        @Configurable.Range(min = 1)
        public int DeconstructionBaseTime = 600;

        @Configurable
        @Configurable.Comment({ "Deconstruction Base Voltage in Long.", "Default : 30 (LV)" })
        @Configurable.Range(min = 1)
        public long DeconstructionVoltage = 30;

        @Configurable
        @Configurable.Comment({ "If true, only Elements can be deconstructed.", "Default: false" })
        public boolean DeconstructOnlyElements = false;
    }

    public static class ReplicateConfigs {}

    public static class RecipeConfigs {

        @Configurable
        @Configurable.Comment({ "Whether to add the UUMatter recipe.", "Default : false" })
        public boolean addMatterRecipe = false;
    }
}
