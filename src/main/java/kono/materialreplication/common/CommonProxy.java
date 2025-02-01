package kono.materialreplication.common;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;

import kono.materialreplication.MaterialReplicationConfig;
import kono.materialreplication.common.data.MRItems;
import kono.materialreplication.common.data.MRRegistration;
import kono.materialreplication.data.MaterialReplicationDataGen;

public class CommonProxy {

    public CommonProxy() {
        init();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::modifyMaterialInfo);
    }

    public static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MaterialReplicationConfig.init();
        MaterialReplicationDataGen.init();
        MRItems.init();
        MRRegistration.REGISTRATE.registerRegistrate();
    }

    public void modifyMaterialInfo(MaterialEvent e) {}

    public void modifyMaterialInfoLowest(MaterialEvent e) {}
}
