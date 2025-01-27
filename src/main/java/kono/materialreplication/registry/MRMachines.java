package kono.materialreplication.registry;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;

import java.util.function.BiFunction;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.GTCreativeModeTabs.MACHINE;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.*;
import static com.gregtechceu.gtceu.utils.FormattingUtil.toEnglishName;
import static kono.materialreplication.MRUtils.mrId;
import static kono.materialreplication.registry.MRRegistration.REGISTRATE;

public class MRMachines {
    static {
        REGISTRATE.creativeModeTab(() -> MACHINE);
    }

    public static final MachineDefinition[] DECONSTRUCTOR = registerSimpleMachines("deconstructor",
            MRRecipeTypes.DECONSTRUCTOR_RECIPE, hvCappedTankSizeFunction);

    public static MachineDefinition[] registerSimpleMachines(String name,
                                                             GTRecipeType recipeType,
                                                             Int2IntFunction tankScalingFunction,
                                                             int... tiers) {
        return registerTieredMachines(name, (holder, tier) -> new SimpleTieredMachine(holder, tier, tankScalingFunction), (tier, builder) -> builder
                .langValue("%s %s %s".formatted(VLVH[tier], toEnglishName(name), VLVT[tier]))
                .editableUI(SimpleTieredMachine.EDITABLE_UI_CREATOR.apply(GTCEu.id(name), recipeType))
                .rotationState(RotationState.NON_Y_AXIS)
                .recipeType(recipeType)
                .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
                .workableTieredHullRenderer(mrId("block/machines/" + name))
                .tooltips(workableTiered(tier, GTValues.V[tier], GTValues.V[tier] * 64, recipeType, tankScalingFunction.apply(tier), true))
                .register(), tiers);
    }

    public static MachineDefinition[] registerSimpleMachines(String name, GTRecipeType recipeType, Int2IntFunction tankScalingFunction) {
        return registerSimpleMachines(name, recipeType, tankScalingFunction, ELECTRIC_TIERS);
    }

    public static MachineDefinition[] registerSimpleMachines(String name, GTRecipeType recipeType) {
        return registerSimpleMachines(name, recipeType, defaultTankSizeFunction);
    }

    public static MachineDefinition[] registerTieredMachines(String name,
                                                             BiFunction<IMachineBlockEntity, Integer, MetaMachine> factory,
                                                             BiFunction<Integer, MachineBuilder<MachineDefinition>, MachineDefinition> builder,
                                                             int... tiers) {
        MachineDefinition[] definitions = new MachineDefinition[tiers.length];
        for (int i = 0; i < tiers.length; i++) {
            int tier = tiers[i];
            var register =  REGISTRATE.machine(GTValues.VN[tier].toLowerCase() + "_" + name, holder -> factory.apply(holder, tier))
                    .tier(tier);
            definitions[i] = builder.apply(tier, register);
        }
        return definitions;
    }

    public static void init() {}
}
