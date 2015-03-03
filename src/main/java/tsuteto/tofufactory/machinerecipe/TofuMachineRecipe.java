package tsuteto.tofufactory.machinerecipe;

import net.minecraft.item.ItemStack;
import tsuteto.tofufactory.integration.TFIntegrationManager;
import tsuteto.tofufactory.api.recipes.RecipeManagers;
import tsuteto.tofufactory.integration.plugins.PluginGreg;

public class TofuMachineRecipe
{
    public static void initRecipe()
    {
        RecipeManagers.pulverizerManager = new PulverizerRecipe();
        RecipeManagers.compactorManager = new CompactorRecipe();
        RecipeManagers.cuttingMachineManager = new CuttingMachineRecipe();
    }

    public static ItemStack getOreDictStack(ItemStack aOreStack)
    {
        return TFIntegrationManager.modGT.isReady() ? PluginGreg.getOreDictStack(aOreStack.copy()) : aOreStack;
    }


}
