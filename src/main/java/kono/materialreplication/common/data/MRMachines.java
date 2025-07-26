package kono.materialreplication.common.data;

import java.util.Locale;
import java.util.function.BiFunction;

import net.minecraft.network.chat.Component;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.*;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.config.ConfigHolder;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.*;
import static com.gregtechceu.gtceu.utils.FormattingUtil.toEnglishName;
import static kono.materialreplication.MRUtils.mrId;
import static kono.materialreplication.common.data.MRCreativeModeTabs.MATERIALREPLICATION;
import static kono.materialreplication.common.data.MRRegistration.REGISTRATE;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;

public class MRMachines {

    static {
        REGISTRATE.creativeModeTab(() -> MATERIALREPLICATION);
    }

    // Single Machine Section
    public static final MachineDefinition[] DECONSTRUCTOR = registerSimpleMachines("material_deconstructor",
            MRRecipeTypes.DECONSTRUCTOR_RECIPE, hvCappedTankSizeFunction);

    public static final MachineDefinition[] ANNIHILATOR = registerSimpleMachines("annihilator",
            MRRecipeTypes.ANNIHILATOR_RECIPE, largeTankSizeFunction);

    public static final MachineDefinition[] REPLICATOR = registerSimpleMachines("material_replicator",
            MRRecipeTypes.REPLICATOR_RECIPE, hvCappedTankSizeFunction, true);

    // Multi Machine Section
    public static final MultiblockMachineDefinition LARGE_DECONSTRUCTOR = REGISTRATE.multiblock(
            "large_deconstructor", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeTypes(MRRecipeTypes.DECONSTRUCTOR_RECIPE)
            .tooltips(Component.translatable("gtceu.multiblock.parallelizable.tooltip"))
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH, GTRecipeModifiers.OC_NON_PERFECT_SUBTICK)
            .appearanceBlock(GCYMBlocks.CASING_ATOMIC)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("XXXXX", "XGGGX", "XFVFX", "XGGGX", "XXXXX")
                    .aisle("XXXXX", "GAAAG", "FACAF", "GAAAG", "XXXXX")
                    .aisle("XXVXX", "GACAG", "VCCCV", "GACAG", "XXVXX")
                    .aisle("XXXXX", "GAAAG", "FACAF", "GAAAG", "XXXXX")
                    .aisle("XXXXX", "XGGGX", "XFSFX", "XGGGX", "XXXXX")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('X', blocks(GCYMBlocks.CASING_ATOMIC.get()).setMinGlobalLimited(45)
                            .or(autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, true)))
                    .where('V', blocks(GCYMBlocks.HEAT_VENT.get()))
                    .where('F', blocks(GTBlocks.FUSION_CASING_MK2.get()))
                    .where('C', blocks(GTBlocks.FUSION_COIL.get()))
                    .where('G', blocks(GTBlocks.FUSION_GLASS.get()))
                    .where('A', air())
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/gcym/atomic_casing"),
                    GTCEu.id("block/multiblock/large_chemical_reactor"))
            .register();

    public static final MultiblockMachineDefinition LARGE_ANNIHILATOR = REGISTRATE.multiblock(
            "large_annihilator", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeTypes(MRRecipeTypes.ANNIHILATOR_RECIPE)
            .tooltips(Component.translatable("gtceu.multiblock.parallelizable.tooltip"))
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH, GTRecipeModifiers.OC_NON_PERFECT_SUBTICK)
            .appearanceBlock(GCYMBlocks.CASING_VIBRATION_SAFE)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("XXXXX", "XXXXX", "XXXXX", "XXXXX", "XXXXX")
                    .aisle("XXXXX", "XCCCX", "XBBBX", "XCCCX", "XXXXX")
                    .aisle("XXXXX", "XCCCX", "XBTBX", "XCCCX", "XXMXX")
                    .aisle("XXXXX", "XCCCX", "XBBBX", "XCCCX", "XXXXX")
                    .aisle("XXXXX", "XXSXX", "XXXXX", "XXXXX", "XXXXX")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('X', blocks(GCYMBlocks.CASING_VIBRATION_SAFE.get()).setMinGlobalLimited(45)
                            .or(autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, true)))
                    .where('C', blocks(GCYMBlocks.CRUSHING_WHEELS.get()))
                    .where('B', blocks(GCYMBlocks.SLICING_BLADES.get()))
                    .where('M', abilities(PartAbility.MUFFLER))
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/gcym/vibration_safe_casing"),
                    GTCEu.id("block/multiblock/large_chemical_reactor"))
            .register();

    public static final MultiblockMachineDefinition LARGE_REPLICATOR = REGISTRATE.multiblock(
            "large_replicator", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeTypes(MRRecipeTypes.REPLICATOR_RECIPE)
            .tooltips(Component.translatable("gtceu.multiblock.parallelizable.tooltip"))
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH, GTRecipeModifiers.OC_NON_PERFECT_SUBTICK)
            .appearanceBlock(GCYMBlocks.CASING_ATOMIC)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("XXXXX", "XXXXX", "XFFFX", "XXXXX", "XXXXX")
                    .aisle("XXXXX", "GAAAG", "FAAAF", "GAAAG", "XXXXX")
                    .aisle("XXXXX", "GAAAG", "FACAF", "GAAAG", "XXXXX")
                    .aisle("XXXXX", "GAAAG", "FAAAF", "GAAAG", "XXXXX")
                    .aisle("XXSXX", "XGGGX", "XFFFX", "XGGGX", "XXXXX")
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('X', blocks(GCYMBlocks.CASING_ATOMIC.get()).setMinGlobalLimited(40)
                            .or(autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, true)))
                    .where('F', blocks(GTBlocks.FUSION_CASING_MK3.get()))
                    .where('G', blocks(GTBlocks.FUSION_GLASS.get()))
                    .where('C', blocks(GTBlocks.FUSION_COIL.get()))
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/gcym/atomic_casing"),
                    GTCEu.id("block/multiblock/large_chemical_reactor"))
            .register();

    public static MachineDefinition[] registerSimpleMachines(String name, GTRecipeType recipeType,
                                                             Int2IntFunction tankScalingFunction,
                                                             boolean hasPollutionDebuff) {
        return registerSimpleMachines(name, recipeType, tankScalingFunction, hasPollutionDebuff, ELECTRIC_TIERS);
    }

    public static MachineDefinition[] registerSimpleMachines(String name, GTRecipeType recipeType,
                                                             Int2IntFunction tankScalingFunction) {
        return registerSimpleMachines(name, recipeType, tankScalingFunction, false, ELECTRIC_TIERS);
    }

    public static MachineDefinition[] registerSimpleMachines(String name, GTRecipeType recipeType) {
        return registerSimpleMachines(name, recipeType, defaultTankSizeFunction);
    }

    public static MachineDefinition[] registerSimpleMachines(String name,
                                                             GTRecipeType recipeType,
                                                             Int2IntFunction tankScalingFunction,
                                                             boolean hasPollutionDebuff,
                                                             int... tiers) {
        return registerTieredMachines(name,
                (holder, tier) -> new SimpleTieredMachine(holder, tier, tankScalingFunction), (tier, builder) -> {
                    if (hasPollutionDebuff) {
                        builder.recipeModifiers(GTRecipeModifiers.ENVIRONMENT_REQUIREMENT
                                .apply(GTMedicalConditions.CARBON_MONOXIDE_POISONING, 100 * tier),
                                GTRecipeModifiers.OC_NON_PERFECT)
                                .conditionalTooltip(defaultEnvironmentRequirement(),
                                        ConfigHolder.INSTANCE.gameplay.environmentalHazards);
                    } else {
                        builder.recipeModifier(GTRecipeModifiers.OC_NON_PERFECT);
                    }
                    return builder
                            .langValue("%s %s %s".formatted(VLVH[tier], toEnglishName(name), VLVT[tier]))
                            .editableUI(SimpleTieredMachine.EDITABLE_UI_CREATOR.apply(GTCEu.id(name), recipeType))
                            .rotationState(RotationState.NON_Y_AXIS)
                            .recipeType(recipeType)
                            .recipeModifier(
                                    GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
                            .workableTieredHullModel(mrId("block/machines/" + name))
                            .tooltips(workableTiered(tier, GTValues.V[tier], GTValues.V[tier] * 64, recipeType,
                                    tankScalingFunction.apply(tier), true))
                            .register();
                },
                tiers);
    }

    public static MachineDefinition[] registerTieredMachines(String name,
                                                             BiFunction<IMachineBlockEntity, Integer, MetaMachine> factory,
                                                             BiFunction<Integer, MachineBuilder<MachineDefinition>, MachineDefinition> builder,
                                                             int... tiers) {
        MachineDefinition[] definitions = new MachineDefinition[GTValues.TIER_COUNT];
        for (int tier : tiers) {
            var register = REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_" + name,
                            holder -> factory.apply(holder, tier))
                    .tier(tier);
            definitions[tier] = builder.apply(tier, register);
        }
        return definitions;
    }

    public static void init() {}
}
