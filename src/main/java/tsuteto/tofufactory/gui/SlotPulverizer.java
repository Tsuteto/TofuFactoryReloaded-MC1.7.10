package tsuteto.tofufactory.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotPulverizer extends SlotTofuMachine
{
    public SlotPulverizer(EntityPlayer par1EntityPlayer, IInventory par2iInventory, int par2, int par3, int par4)
    {
        super(par1EntityPlayer, par2iInventory, par2, par3, par4);
    }

    public void getAchievement(ItemStack Item) {}
}
