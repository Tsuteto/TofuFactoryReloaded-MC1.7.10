package tsuteto.tofufactory.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.ItemFocusBasic;
import tsuteto.tofu.Settings;
import tsuteto.tofu.block.BlockTofuBase;
import tsuteto.tofu.block.TcBlocks;
import tsuteto.tofu.dimension.DimensionTeleportation;
import tsuteto.tofu.item.TcItems;

public class ItemTFFocus extends ItemFocusBasic
{
    public ItemTFFocus()
    {
        super();
    }

    @Override
    public int getFocusColor(ItemStack focusStack)
    {
        return 16777215;
    }

    /**
     * Sets the unlocalized name of this item to the string passed as the parameter, prefixed by "item."
     */
    @Override
    public Item setUnlocalizedName(String par1Str)
    {
        super.setUnlocalizedName(par1Str);
        return this;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        this.icon = TcItems.tofuKinu.getIconFromDamage(0);
    }

    @Override
    public AspectList getVisCost(ItemStack focusStack)
    {
        return (new AspectList()).add(Aspect.EARTH, 1);
    }

    @Override
    public IIcon getFocusDepthLayerIcon(ItemStack focusstack)
    {
        return this.icon;
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition movingobjectposition)
    {
        if (player.isSneaking() && !world.isRemote)
        {
            ;
        }

        if (movingobjectposition == null)
        {
            return itemstack;
        }
        else
        {
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                int var5 = movingobjectposition.blockX;
                int var6 = movingobjectposition.blockY;
                int var7 = movingobjectposition.blockZ;
                Block var11 = world.getBlock(var5, var6, var7);
                BlockTofuBase var13 = null;

                if (var11 == TcBlocks.soymilkStill)
                {
                    var13 = TcBlocks.tofuKinu;
                }
                else if (var11 == TcBlocks.soymilkHellStill)
                {
                    var13 = TcBlocks.tofuHell;
                }

                if (var13 != null)
                {
                    world.playSoundEffect((double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F), (double)((float)var7 + 0.5F), var13.stepSound.getStepResourcePath(), (var13.stepSound.getVolume() + 1.0F) / 2.0F, var13.stepSound.getPitch() * 0.8F);
                    world.setBlock(var5, var6, var7, var13);
                }
            }

            return itemstack;
        }
    }

    private boolean travelDimension(EntityPlayer player)
    {
        int currDim = player.worldObj.provider.dimensionId;
        int travelTo;

        if (currDim == Settings.tofuDimNo)
        {
            travelTo = 0;
        }
        else
        {
            if (currDim != 0)
            {
                return false;
            }

            travelTo = Settings.tofuDimNo;
        }

        (new DimensionTeleportation()).transferPlayerToDimension((EntityPlayerMP)player, travelTo);
        return true;
    }
}
