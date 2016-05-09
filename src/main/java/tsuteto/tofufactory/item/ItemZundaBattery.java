package tsuteto.tofufactory.item;

import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tsuteto.tofufactory.integration.plugins.PluginIC2;

public class ItemZundaBattery extends ItemTofuFactory implements IElectricItem
{
    public ItemZundaBattery()
    {
        super();
        this.setMaxDamage(1000);
    }

    /**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
        if (par3Entity instanceof EntityPlayerMP)
        {
            EntityPlayerMP thePlayer = (EntityPlayerMP)par3Entity;
            ItemStack BodyItem = thePlayer.getCurrentArmor(2);
            ItemStack item = thePlayer.getCurrentEquippedItem();
            double Bc;
            double Bmyc;

            if (item != null && item.getItem() != PluginIC2.zundaBattery)
            {
                Bc = 0;
                Bmyc = ElectricItem.manager.getCharge(par1ItemStack);

                if (Bmyc > 0)
                {
                    if (Bmyc >= 1000)
                    {
                        Bc = ElectricItem.manager.charge(item, 1000, 3, true, false);
                    }
                    else
                    {
                        Bc = ElectricItem.manager.charge(item, Bmyc, 3, true, false);
                    }
                }

                if (Bc > 0)
                {
                    ElectricItem.manager.discharge(par1ItemStack, Bc, 1, true, false, true);
                }
            }

            if (BodyItem != null)
            {
                Bc = 0;
                Bmyc = ElectricItem.manager.getCharge(par1ItemStack);

                if (Bmyc > 0)
                {
                    if (Bmyc >= 1000)
                    {
                        Bc = ElectricItem.manager.charge(BodyItem, 1000, 3, true, false);
                    }
                    else
                    {
                        Bc = ElectricItem.manager.charge(BodyItem, Bmyc, 3, true, false);
                    }
                }

                if (Bc > 0)
                {
                    ElectricItem.manager.discharge(par1ItemStack, Bc, 1, true, false, true);
                }
            }
        }
    }

    @Override
    public boolean canProvideEnergy(ItemStack itemStack)
    {
        return true;
    }

    @Override
    public Item getChargedItem(ItemStack itemStack)
    {
        return this;
    }

    @Override
    public Item getEmptyItem(ItemStack itemStack)
    {
        return this;
    }

    @Override
    public double getMaxCharge(ItemStack itemStack)
    {
        return 40000;
    }

    @Override
    public int getTier(ItemStack itemStack)
    {
        return 1;
    }

    @Override
    public double getTransferLimit(ItemStack itemStack)
    {
        return 300;
    }
}
