package tsuteto.tofufactory.integration.craftguide;

import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeOutput;
import ic2.api.recipe.Recipes;
import net.minecraft.item.ItemStack;
import tsuteto.tofu.recipe.Ingredient;
import tsuteto.tofufactory.api.recipes.RecipeManagers;
import tsuteto.tofufactory.core.TFItems;

import java.util.Map;

public class CuttingMachineCG extends RecipeCGBase
{
    @Override
    protected int getTextureRow()
    {
        return 1;
    }

    @Override
    protected ItemStack getMachineBlock()
    {
        return new ItemStack(TFItems.tofuCuttingMachineIdle);
    }

    @Override
    protected Map<Ingredient<?>, ItemStack> getLocalRecipeList()
    {
        return RecipeManagers.cuttingMachineManager.getRecipeList();
    }

    @Override
    protected Map<IRecipeInput, RecipeOutput> getIC2RecipeList()
    {
        return Recipes.blockcutter.getRecipes();
    }

}
