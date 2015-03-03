package tsuteto.tofufactory.machinerecipe;

import net.minecraft.item.ItemStack;
import tsuteto.tofu.recipe.Ingredient;
import tsuteto.tofufactory.integration.TFIntegrationManager;
import tsuteto.tofufactory.api.recipes.IPulverizerManager;
import tsuteto.tofufactory.integration.recipes.RecipeIC2;

import java.util.Map;

public class PulverizerRecipe extends SimpleTofuMachineRecipe implements IPulverizerManager
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
                ItemStack ret = RecipeIC2.getMaceratorResult(input);

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
