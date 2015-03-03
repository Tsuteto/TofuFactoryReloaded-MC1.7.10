package tsuteto.tofufactory.api.recipes;

import net.minecraft.item.ItemStack;

public interface ICompactorManager extends IMachineRecipe
{
    void addRecipe(ItemStack var1, ItemStack var2);
}
