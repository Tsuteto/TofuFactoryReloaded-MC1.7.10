package tsuteto.tofufactory.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemCraftingHammer extends ItemTofuFactory
{
    public ItemCraftingHammer()
    {
        super();
        this.setMaxDamage(50);
        this.setMaxStackSize(1);
        this.canRepair = false;
    }

    public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean b)
    {
        info.add(StatCollector.translateToLocalFormatted(
                "item.tofufactory:craftingHammer.tooltip.UsesLeft", itemStack.getMaxDamage() - itemStack.getItemDamage()));
    }

    public boolean hasContainerItem(ItemStack stack)
    {
        return true;
    }

    public ItemStack getContainerItem(ItemStack stack)
    {
        ItemStack ret = stack.copy();
        ret.attemptDamageItem(1, itemRand);
        return ret;
    }

    public boolean doesContainerItemLeaveCraftingGrid(ItemStack stack)
    {
        return false;
    }
}
