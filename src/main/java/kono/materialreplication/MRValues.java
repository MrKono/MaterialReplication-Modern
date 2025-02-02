package kono.materialreplication;

import static com.gregtechceu.gtceu.api.GTValues.LV;
import static com.gregtechceu.gtceu.api.GTValues.VA;

public class MRValues {

    private static final MaterialReplicationConfig.DeconstructConfigs deconstructionCfg = MaterialReplicationConfig.INSTANCE.DeconstructionConfig;
    private static final MaterialReplicationConfig.ReplicateConfigs replicationCfg = MaterialReplicationConfig.INSTANCE.ReplicationConfig;
    private static final MaterialReplicationConfig.RecipeConfigs recipeCfg = MaterialReplicationConfig.INSTANCE.RecipeConfig;

    // Strings
    public static final String REPLICATE_NBT_TAG = "replicate_research";
    public static final String REPLICATE_ID_NBT_TAG = "material_id";

    // Misc
    public static final int sec = 20;
    public static final int min = 1200;

    public static final int amplifierAmount = getValidValue(recipeCfg.MatterAmplificationRatio[0], 500);
    public static final int matterInput = getValidValue(recipeCfg.MatterAmplificationRatio[1], 500);
    public static final int matterOutput = getValidValue(recipeCfg.MatterAmplificationRatio[2], 1000);

    public static final int chargedMatterAmount = getValidValue(
            MaterialReplicationConfig.INSTANCE.RecipeConfig.MatterRatio[0], 50);
    public static final int neutralMatterAmount = getValidValue(
            MaterialReplicationConfig.INSTANCE.RecipeConfig.MatterRatio[1], 50);
    public static final int matterAmount = getValidValue(MaterialReplicationConfig.INSTANCE.RecipeConfig.MatterRatio[1],
            50);

    // time
    public static final int deconstructionBaseTime = getValidValue(deconstructionCfg.DeconstructionBaseTime, 30 * sec);
    public static final int replicationBaseTime = getValidValue(replicationCfg.ReplicationBaseTime, 1 * min);
    public static final int scanTime = getValidValue(replicationCfg.ScanVoltage, 1 * min);

    // Voltage
    public static final long deconstructionVoltage = getValidValue(deconstructionCfg.DeconstructionVoltage, VA[LV]);
    public static final long replicationVoltage = getValidValue(replicationCfg.ReplicationVoltage, VA[LV]);
    public static final long scanVoltage = getValidValue(replicationCfg.ScanVoltage, VA[LV]);

    private static int getValidValue(int value, int defaultValue) {
        return getValidValue(1, value, defaultValue);
    }

    /**
     * Checks if value is greater than or equal to min (int)
     * 
     * @param min          minimum value
     * @param value        what to check
     * @param defaultValue default value
     * @return value (value >= min) or defaultValue (value < min)
     */
    private static int getValidValue(int min, int value, int defaultValue) {
        return value >= min ? value : defaultValue;
    }

    private static long getValidValue(long value, long defaultValue) {
        return getValidValue(1, value, defaultValue);
    }

    /**
     * Checks if value is greater than or equal to min (long)
     * 
     * @param min          minimum value
     * @param value        what to check
     * @param defaultValue default value
     * @return value (value >= min) or defaultValue (value < min)
     */
    private static long getValidValue(long min, long value, long defaultValue) {
        return value >= min ? value : defaultValue;
    }
}
