package tsuteto.tofufactory.machinerecipe;

import net.minecraft.item.ItemStack;
import tsuteto.tofufactory.api.recipes.RecipeManagers;
import tsuteto.tofufactory.integration.TFIntegrationManager;
import tsuteto.tofufactory.integration.plugins.PluginGreg5;
import tsuteto.tofufactory.integration.plugins.PluginGreg6;

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
        if (TFIntegrationManager.modGT6.isReady())
        {
            return PluginGreg6.getOreDictStack(aOreStack.copy());
        }
        else if (TFIntegrationManager.modGT5.isReady())
        {
            return PluginGreg5.getOreDictStack(aOreStack.copy());
        }
        else
        {
            return aOreStack;
        }
    }


}
