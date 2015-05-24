package tsuteto.tofufactory.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.items.wands.ItemWandCasting;
import tsuteto.tofu.entity.EntityFukumame;
import tsuteto.tofu.item.TcItems;

public class ItemFocusSoy extends ItemFocusBasic
{
    public ItemFocusSoy()
    {
        super();
    }

    @Override
    public int getFocusColor(ItemStack focusStack)
    {
        return 0xf6d433;
    }

    @Override
    public void registerIcons(IIconRegister p_94581_1_)
    {
        super.registerIcons(p_94581_1_);
        this.icon = this.itemIcon;
    }

    @Override
    public AspectList getVisCost(ItemStack focusStack)
    {
        return (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.AIR, 2).add(Aspect.ENTROPY, 2);
    }

    @Override
    public IIcon getFocusDepthLayerIcon(ItemStack focusstack)
    {
        return TcItems.fukumame.getIconFromDamage(0);
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition movingobjectposition)
    {
        ItemWandCasting wand = (ItemWandCasting)itemstack.getItem();
        if (wand.consumeAllVis(itemstack, player, this.getVisCost(itemstack), !player.worldObj.isRemote, false))
        {
            for (int i = 0; i < 8; i++) {
                EntityFukumame fukumame = new EntityFukumame(world, player);

                if (!world.isRemote)
                {
                    world.spawnEntityInWorld(fukumame);
                }
            }
            world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        }
        return itemstack;
    }
}
