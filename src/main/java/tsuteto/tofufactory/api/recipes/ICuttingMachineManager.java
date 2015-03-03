package tsuteto.tofufactory.api.recipes;

import net.minecraft.item.ItemStack;

public interface ICuttingMachineManager extends IMachineRecipe
{
    void addRecipe(ItemStack var1, ItemStack var2);
}
