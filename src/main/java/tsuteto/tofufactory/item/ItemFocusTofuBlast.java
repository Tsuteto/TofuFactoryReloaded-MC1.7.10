package tsuteto.tofufactory.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.entities.monster.EntityFireBat;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.utils.EntityUtils;
import tsuteto.tofu.init.TcBlocks;

public class ItemFocusTofuBlast extends ItemFocusBasic
{
    public ItemFocusTofuBlast()
    {
        super();
    }

    @Override
    public int getFocusColor(ItemStack focusStack)
    {
        return 0xc2a934;
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
        return (new AspectList()).add(Aspect.EARTH, 25).add(Aspect.ENTROPY, 10).add(Aspect.FIRE, 20);
    }

    @Override
    public IIcon getFocusDepthLayerIcon(ItemStack focusstack)
    {
        return null;
    }

    @Override
    public WandFocusAnimation getAnimation(ItemStack focusstack)
    {
        return WandFocusAnimation.CHARGE;
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition movingobjectposition)
    {
        ItemWandCasting wand = (ItemWandCasting)itemstack.getItem();
        Entity pointedEntity = EntityUtils.getPointedEntity(player.worldObj, player, 32.0D, EntityFireBat.class);
        if (pointedEntity != null && pointedEntity instanceof EntityLivingBase)
        {
            if (wand.consumeAllVis(itemstack, player, this.getVisCost(itemstack), !world.isRemote, false))
            {
                int size = 3;
                double targetX = pointedEntity.posX;
                double targetY = pointedEntity.posY;
                double targetZ = pointedEntity.posZ;
                if (!world.isRemote)
                {
                    this.buildTofu(
                            (int) (targetX + pointedEntity.motionX),
                            (int) (targetY + pointedEntity.motionY),
                            (int) (targetZ + pointedEntity.motionZ),
                            size, world);
                    world.createExplosion(player, targetX, targetY, targetZ, 3.0F, false);
                }
            }
        }
        return itemstack;
    }

    protected void buildTofu(int ox, int oy, int oz, int height, World par1World)
    {
        int blockY, radius, blockX, relX;
        radius = (height - 1) / 2;

        for (blockY = oy; blockY < oy + height; ++blockY)
        {
            for (blockX = ox - radius; blockX <= ox + radius; ++blockX)
            {
                relX = blockX - ox;

                for (int blockZ = oz - radius; blockZ <= oz + radius; ++blockZ)
                {
                    int relZ = blockZ - oz;

                    if (par1World.getBlock(blockX, blockY, blockZ) == Blocks.air)
                    {
                        par1World.setBlock(blockX, blockY, blockZ, TcBlocks.tofuMomen, 0, 3);
                    }
                }
            }
        }
    }


    @Override
    public int getActivationCooldown(ItemStack focusstack)
    {
        return 3000;
    }
}
