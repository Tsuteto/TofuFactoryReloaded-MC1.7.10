package tsuteto.tofufactory.machinerecipe;

import net.minecraft.item.ItemStack;
import tsuteto.tofufactory.api.recipes.ICuttingMachineManager;
import tsuteto.tofufactory.integration.TFIntegrationManager;
import tsuteto.tofufactory.integration.recipes.RecipeIC2;

public class CuttingMachineRecipe extends SimpleTofuMachineRecipe implements ICuttingMachineManager
{
    public ItemStack getProcessingResult(ItemStack input)
    {
        if (input == null)
        {
            return null;
        }
        else
        {
            if (TFIntegrationManager.modIC2.isReady())
            {
                ItemStack ret = RecipeIC2.getCuttingMachineResult(input);

                if (ret != null)
                {
                    return getOreDictStack(ret);
                }
            }

            ItemStack ret = this.getResult(input);

            if (ret != null)
            {
                return getOreDictStack(ret);
            }
            else
            {
                return null;
            }
        }
    }
}
