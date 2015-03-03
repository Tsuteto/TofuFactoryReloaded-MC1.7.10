package tsuteto.tofufactory.machinerecipe;

import net.minecraft.item.ItemStack;
import tsuteto.tofufactory.api.recipes.ICompactorManager;
import tsuteto.tofufactory.integration.TFIntegrationManager;
import tsuteto.tofufactory.integration.recipes.RecipeIC2;

public class CompactorRecipe extends SimpleTofuMachineRecipe implements ICompactorManager
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
                ItemStack re3 = RecipeIC2.getCompressorResult(input.copy());

                if (re3 != null)
                {
                    return getOreDictStack(re3);
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
