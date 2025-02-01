package kono.materialreplication.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.PostMaterialEvent;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;

import kono.materialreplication.MRUtils;
import kono.materialreplication.common.data.MRMachines;
import kono.materialreplication.common.data.MRMaterials;
import kono.materialreplication.common.data.MRRecipeTypes;

@Mod.EventBusSubscriber(modid = MRUtils.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler {

    public static void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event) {
        MRMachines.init();
    }

    public static void registerRecipeTypes(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeType> event) {
        MRRecipeTypes.init();
    }

    @SubscribeEvent
    public static void registerMaterials(MaterialEvent event) {
        MRMaterials.init();
    }

    @SubscribeEvent
    public static void registerMaterialPost(PostMaterialEvent event) {
        MRMaterials.post();
    }
}
