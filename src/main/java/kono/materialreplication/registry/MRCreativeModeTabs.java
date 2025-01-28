package kono.materialreplication.registry;

import net.minecraft.world.item.CreativeModeTab;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;

import static kono.materialreplication.MRUtils.mrId;
import static kono.materialreplication.registry.MRRegistration.REGISTRATE;

import com.tterrag.registrate.util.entry.RegistryEntry;

public class MRCreativeModeTabs {

    public static RegistryEntry<CreativeModeTab> MATERIALREPLICATION = REGISTRATE.defaultCreativeTab("item",
            builder -> builder.displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator("item", REGISTRATE))
                    .icon(MRItems.USB_STICK::asStack)
                    .title(REGISTRATE.addLang("itemGroup", mrId("item"), "Material Replication"))
                    .build())
            .register();
}
