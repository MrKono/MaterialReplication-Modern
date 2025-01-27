package kono.materialreplication.registry.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlag;

public class MRMaterialFlags {

    /**
     * Disables deconstruction recipe generation for this material
     */
    public static final MaterialFlag DISABLE_DECONSTRUCTION = new MaterialFlag.Builder("no_deconstruction").build();

    /**
     * Disables replication recipe generation for this material
     */
    public static final MaterialFlag DISABLE_REPLICATION = new MaterialFlag.Builder("no_replication").build();
}
