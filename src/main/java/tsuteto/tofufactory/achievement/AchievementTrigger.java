package tsuteto.tofufactory.achievement;

import net.minecraft.item.ItemStack;

public class AchievementTrigger
{
    private final ItemStack itemstack;

    public AchievementTrigger(ItemStack itemstack)
    {
        this.itemstack = itemstack;
    }

    public boolean equals(Object obj)
    {
        return obj != null && obj instanceof ItemStack && ((ItemStack) obj).isItemEqual(this.itemstack);
    }
}
