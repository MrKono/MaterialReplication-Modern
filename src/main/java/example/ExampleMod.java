package example;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

@Mod(ExampleMod.MODID)
public class ExampleMod {
    public static final String MODID = "materialreplication";
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final RegistryObject<Item> testItem = ITEMS.register("test", () -> new Item(new Item.Properties()) {
        @Override
        public @NotNull Component getName(@NotNull ItemStack pStack) {
            return Component.literal("MaterialReplication Test Item");
        }
    });

    public ExampleMod() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
