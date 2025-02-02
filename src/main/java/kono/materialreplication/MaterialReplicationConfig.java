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
    public RecipeConfigs RecipeConfig = new RecipeConfigs();
    @Configurable
    public DeconstructConfigs DeconstructionConfig = new DeconstructConfigs();
    @Configurable
    public ReplicateConfigs ReplicationConfig = new ReplicateConfigs();

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

        @Configurable
        @Configurable.Comment({ "Material Blacklist for Deconstruction",
                "This setting is valid only for materials with chemical formulas",
                "Default: granite, andesite, diorite, clay, brick, obsidian, flint, ice, charcoal, gunpowder, sugar, granite_black, granite_red, marble, basalt, quicklime, redrock, ash, concrete, dark_ash, water, distilled_water, steam" })
        public String[] MaterialsForDeconstructionBlacklist = new String[] {
                "granite", "andesite", "diorite", "clay", "brick", "obsidian", "flint", "ice", "charcoal", "gunpowder",
                "sugar",
                "granite_black", "granite_red", "marble", "basalt", "quicklime", "redrock", "ash", "concrete",
                "dark_ash", "water", "distilled_water", "steam"
        };

        @Configurable
        @Configurable.Comment({ "Add material to Deconstruct whitelist which does not have chemical formula" })
        public String[] MaterialsForDeconstructionWhitelist = new String[] {};
    }

    public static class ReplicateConfigs {

        @Configurable
        @Configurable.Comment({ "Replication Base time in tick (int).", "Default : 1200 tick (60 Sec.)",
                "Duration : BaseTime * material's Average mass" })
        @Configurable.Range(min = 1)
        public int ReplicationBaseTime = 1200;

        @Configurable
        @Configurable.Comment({ "Replication Base Voltage in int.", "Default : 30 (LV)" })
        @Configurable.Range(min = 1)
        public int ReplicationVoltage = 30;

        @Configurable
        @Configurable.Comment({ "Material Scan time in tick (int).", "Default : 1200 tick (60 Sec.)" })
        @Configurable.Range(min = 1)
        public int ScanTime = 1200;

        @Configurable
        @Configurable.Comment({ "Material Scan Voltage in int.", "Default : 30 (LV)" })
        @Configurable.Range(min = 1)
        public int ScanVoltage = 30;

        @Configurable
        @Configurable.Comment({ "If true, only Elements can be replicated.", "Default: false" })
        public boolean ReplicateOnlyElements = false;

        @Configurable
        @Configurable.Comment({ "Material Blacklist for Replication",
                "This setting is valid only for materials with chemical formulas",
                "Default: " })
        public String[] MaterialsForReplicationBlacklist = new String[] {};

        @Configurable
        @Configurable.Comment({ "Add material to Replicate whitelist which does not have chemical formula" })
        public String[] MaterialsForReplicationWhitelist = new String[] {};
    }

    public static class RecipeConfigs {

        @Configurable
        @Configurable.Comment({ "Whether to add the UUMatter RecipeConfig.", "Default : false" })
        public boolean AddMatterRecipe = true;

        @Configurable
        @Configurable.Comment({ "Whether to required Cleanroom  when create UU-Matter", "Default: NONE",
                "valid: [NONE, CLEANROOM, STERILE]" })
        public String CleanroomType = "NONE";

        @Configurable
        @Configurable.Comment({ "Ratio of UUMatter created from two maters", "ChargedMatter, NeutralMatter, UUMatter",
                "Default: 50, 50, 50" })
        public int[] MatterRatio = { 50, 50, 50 };

        @Configurable
        @Configurable.Comment({ "Ratio of matter amplification",
                "Matter Amplifier, Charge/Neutral Matter (Input), Charge/Neutral Matter (Output)",
                "Default: 50, 50, 50" })
        public int[] MatterAmplificationRatio = { 500, 500, 1000 };
    }
}
