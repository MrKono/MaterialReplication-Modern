package kono.materialreplication.registry;

import net.minecraft.world.item.Item;

import com.gregtechceu.gtceu.api.item.ComponentItem;

import static kono.materialreplication.registry.MRCreativeModeTabs.MATERIALREPLICATION;
import static kono.materialreplication.registry.MRRegistration.REGISTRATE;

import com.tterrag.registrate.util.entry.ItemEntry;

public class MRItems {

    static {
        REGISTRATE.creativeModeTab(() -> MATERIALREPLICATION);
    }

    public static ItemEntry<ComponentItem> USB_STICK = REGISTRATE
            .item("usb_stick", ComponentItem::create)
            .lang("USB Stick")
            .register();

    public static ItemEntry<Item> SCRAP = REGISTRATE
            .item("scrap", Item::new)
            .lang("Scrap")
            .register();

    public static ItemEntry<Item> SCRAP_BOX = REGISTRATE
            .item("scrap_box", Item::new)
            .lang("Scrap Box")
            .register();

    public static void init() {}
}
