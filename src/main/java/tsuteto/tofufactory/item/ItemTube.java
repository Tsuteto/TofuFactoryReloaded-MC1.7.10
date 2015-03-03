package tsuteto.tofufactory.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import tsuteto.tofufactory.core.TofuFactory;
import tsuteto.tofufactory.integration.plugins.PluginFFM;

import java.util.List;

public class ItemTube extends ItemSetBase
{
    private ItemTubeOverlayInfo[] overlays;
    @SideOnly(Side.CLIENT)
    private IIcon primaryIcon;
    @SideOnly(Side.CLIENT)
    private IIcon secondaryIcon;

    public ItemTube(ItemTubeOverlayInfo[] overlays)
    {
        super(overlays);
        this.overlays = overlays;

    }

    @Override
    public boolean isDamageable()
    {
        return false;
    }

    @Override
    public boolean isRepairable()
    {
        return false;
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List itemList)
    {
        for (int i = 0; i < this.overlays.length; ++i)
        {
            if (!this.overlays[i].isSecret)
            {
                itemList.add(new ItemStack(this, 1, i));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int p_77617_1_)
    {
        return this.itemIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register)
    {
        this.primaryIcon = register.registerIcon("forestry:" + this.getUnlocalizedName().replace("item." + TofuFactory.resourceDomain, "") + ".0");
        this.secondaryIcon = register.registerIcon("forestry:" + this.getUnlocalizedName().replace("item." + TofuFactory.resourceDomain, "") + ".1");
    }

    @Override
    @SideOnly(Side.CLIENT)

    /**
     * Gets an icon index based on an item's damage value and the given render pass
     */
    public IIcon getIconFromDamageForRenderPass(int i, int j)
    {
        return j > 0 ? this.secondaryIcon : this.primaryIcon;
    }

    @Override
    public int getRenderPasses(int metadata)
    {
        return 2;
    }

    @Override
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }

    @Override
    public int getColorFromItemStack(ItemStack itemstack, int j)
    {
        return j != 0 && this.overlays[itemstack.getItemDamage()].secondaryColor != 0 ? this.overlays[itemstack.getItemDamage()].secondaryColor : this.overlays[itemstack.getItemDamage()].primaryColor;
    }

    @Override
    public String getItemSetName()
    {
        return "tube";
    }

}
