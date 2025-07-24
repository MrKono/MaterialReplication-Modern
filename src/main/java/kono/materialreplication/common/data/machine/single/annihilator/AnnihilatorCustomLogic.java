package kono.materialreplication.common.data.machine.single.annihilator;

import java.util.ArrayList;
import java.util.Objects;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

import org.jetbrains.annotations.Nullable;

import com.gregtechceu.gtceu.api.capability.recipe.*;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;

import kono.materialreplication.common.data.MRItems;
import kono.materialreplication.common.data.MRRecipeTypes;

import static com.gregtechceu.gtceu.api.GTValues.LV;
import static com.gregtechceu.gtceu.api.GTValues.VA;
import static kono.materialreplication.MRUtils.mrId;

public class AnnihilatorCustomLogic implements GTRecipeType.ICustomRecipeLogic {

    @Override
    public @Nullable GTRecipe createCustomRecipe(IRecipeCapabilityHolder holder) {
        IItemHandlerModifiable[] itemInputs = Objects
                .requireNonNullElseGet(holder.getCapabilitiesFlat(IO.IN, ItemRecipeCapability.CAP),
                        ArrayList::new)
                .stream()
                .filter(IItemHandlerModifiable.class::isInstance)
                .map(IItemHandlerModifiable.class::cast)
                .toArray(IItemHandlerModifiable[]::new);

        IFluidHandler[] fluidInputs = Objects
                .requireNonNullElseGet(holder.getCapabilitiesFlat(IO.IN, FluidRecipeCapability.CAP),
                        ArrayList::new)
                .stream()
                .filter(IFluidHandler.class::isInstance).map(IFluidHandler.class::cast)
                .toArray(IFluidHandler[]::new);

        // Item annihilation
        CombinedInvWrapper inputs = new CombinedInvWrapper(itemInputs);
        for (int i = 0; i < inputs.getSlots(); i++) {
            ItemStack item = inputs.getStackInSlot(i);
            if (!item.isEmpty()) {
                ItemStack inputStack = item.copy();
                inputStack.setCount(1);
                return MRRecipeTypes.ANNIHILATOR_RECIPE
                        .recipeBuilder(mrId(inputStack.getItem().toString().toLowerCase()))
                        .inputItems(inputStack)
                        .chancedOutput(MRItems.SCRAP.asStack(), 1, 0)
                        .duration(600).EUt(VA[LV]).buildRawRecipe();
            }
        }

        // Fluid annihilation
        for (IFluidHandler fluidInput : fluidInputs) {
            FluidStack fluidStack1 = fluidInput.getFluidInTank(0);
            if (fluidStack1.isEmpty()) {
                continue;
            }
            fluidStack1 = fluidStack1.copy();
            if (fluidStack1.getAmount() > 0) {
                return MRRecipeTypes.ANNIHILATOR_RECIPE.recipeBuilder(mrId(fluidStack1.getTranslationKey()))
                        .inputFluids(new FluidStack(fluidStack1, 1000))
                        .chancedOutput(MRItems.SCRAP.asStack(), 1, 0)
                        .duration(600).EUt(VA[LV]).buildRawRecipe();
            }
        }

        return null;
    }
}
