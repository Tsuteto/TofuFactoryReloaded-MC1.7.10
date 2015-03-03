package tsuteto.tofufactory.machinerecipe;

import com.google.common.collect.Maps;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tsuteto.tofu.recipe.Ingredient;
import tsuteto.tofu.recipe.IngredientDic;
import tsuteto.tofu.recipe.IngredientItem;
import tsuteto.tofufactory.api.recipes.IMachineRecipe;

import java.util.HashMap;
import java.util.Map;

public abstract class SimpleTofuMachineRecipe extends TofuMachineRecipe implements IMachineRecipe
{
    protected final HashMap<Ingredient<?>, ItemStack> recipes = Maps.newHashMap();

    public void addRecipe(ItemStack input, ItemStack output)
    {
        int[] oreIds = OreDictionary.getOreIDs(input);
        if (oreIds.length > 0)
        {
            for (int oreId : oreIds)
            {
                this.recipes.put(new IngredientDic(OreDictionary.getOreName(oreId)), output);
            }
        }
        else
        {
            this.recipes.put(new IngredientItem(input), output);
        }
    }

    public void addRecipe(String input, ItemStack output)
    {
        this.recipes.put(new IngredientDic(input), output);
    }

    public ItemStack getResult(ItemStack input)
    {

        int[] oreIds = OreDictionary.getOreIDs(input);
        if (oreIds.length > 0)
        {
            for (int oreId : oreIds)
            {
                ItemStack output = this.recipes.get(new IngredientDic(OreDictionary.getOreName(oreId)));
                if (output != null) return output;
            }
            return null;
        }
        else
        {
            return this.recipes.get(new IngredientItem(input));
        }
    }

    public Map<Ingredient<?>, ItemStack> getRecipeList()
    {
        return recipes;
    }
}
