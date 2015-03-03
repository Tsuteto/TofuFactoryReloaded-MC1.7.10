package tsuteto.tofufactory.api.recipes;

import net.minecraft.item.ItemStack;
import tsuteto.tofu.recipe.Ingredient;

import java.util.Map;

public interface IMachineRecipe
{
    ItemStack getProcessingResult(ItemStack var1);
    Map<Ingredient<?>, ItemStack> getRecipeList();
}
