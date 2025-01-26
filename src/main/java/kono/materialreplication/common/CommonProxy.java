package kono.materialreplication.common;

import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import kono.materialreplication.registry.MRMaterials;
import kono.materialreplication.registry.MRRegistration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class CommonProxy {

    public CommonProxy() {
        init();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::modifyMaterialInfo);
    }

    public static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MRRegistration.REGISTRATE.registerRegistrate();
    }

    public void modifyMaterialInfo(MaterialEvent e) {
        MRMaterials.materialFlags();
    }
}
