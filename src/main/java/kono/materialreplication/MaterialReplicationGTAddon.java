package kono.materialreplication;

import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import kono.materialreplication.data.recipe.MRRecipeManager;
import kono.materialreplication.registry.MRRegistration;

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
        MRRecipeManager.init(provider);
    }
}
