package kono.materialreplication.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlag;

public class MRMaterialFlags {

    /**
     * Disables deconstruction RecipeConfig generation for this material
     */
    public static final MaterialFlag DISABLE_DECONSTRUCTION = new MaterialFlag.Builder("no_deconstruction").build();

    /**
     * Disables replication RecipeConfig generation for this material
     */
    public static final MaterialFlag DISABLE_REPLICATION = new MaterialFlag.Builder("no_replication").build();
}
