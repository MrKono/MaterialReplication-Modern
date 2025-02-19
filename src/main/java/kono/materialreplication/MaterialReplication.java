package kono.materialreplication;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;

import kono.materialreplication.client.ClientProxy;
import kono.materialreplication.common.CommonProxy;
import kono.materialreplication.event.EventHandler;

@Mod(MRUtils.MODID)
public class MaterialReplication {

    public MaterialReplication() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register Machine
        modEventBus.addGenericListener(MachineDefinition.class, EventHandler::registerMachines);
        // Register RecipeType
        modEventBus.addGenericListener(GTRecipeType.class, EventHandler::registerRecipeTypes);
        DistExecutor.unsafeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    }
}
