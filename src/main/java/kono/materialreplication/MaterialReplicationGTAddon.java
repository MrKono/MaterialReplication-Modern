package kono.materialreplication;

import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import kono.materialreplication.common.data.MRRegistration;
import kono.materialreplication.data.recipe.MRRecipeManager;

@GTAddon
public class MaterialReplicationGTAddon implements IGTAddon {

    @Override
    public GTRegistrate getRegistrate() {
        return MRRegistration.REGISTRATE;
    }

    @Override
    public void initializeAddon() {}

    @Override
    public String addonModId() {
        return MRUtils.MODID;
    }

    @Override
    public void registerTagPrefixes() {}

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        MRRecipeManager.addRecipes(provider);
    }

    @Override
    public void removeRecipes(Consumer<ResourceLocation> consumer) {
        MRRecipeManager.removeRecipes(consumer);
    }
}
