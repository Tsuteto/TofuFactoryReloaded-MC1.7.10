package tsuteto.tofufactory.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import tsuteto.tofu.item.iteminfo.TcItemInfoBase;
import tsuteto.tofufactory.core.TofuFactory;
import tsuteto.tofu.util.Utils;

import java.util.EnumSet;
import java.util.List;

public abstract class ItemSetBase<T extends ItemSetInfo> extends ItemTofuFactory
{
    protected T[] info;
    @SideOnly(Side.CLIENT)
    protected IIcon[] icons;

    public ItemSetBase(T[] info)
    {
        super();
        this.info = info;
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        ItemSetInfo info = getItemInfo(stack.getItemDamage());
        return info.itemUseDuration;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        int dmg = itemstack.getItemDamage();
        ItemSetInfo[] list = this.info;
        return "item." + this.getFullName(MathHelper.clamp_int(dmg, 0, list.length - 1));
    }

    private String getFullName(int dmg)
    {
        return TofuFactory.resourceDomain + (this.getItemSetName() != null ? this.getItemSetName() + "." : "") + info[dmg].name;
    }

    @Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List itemList)
    {
        for (int i = 0; i < this.info.length; ++i)
        {
            itemList.add(new ItemStack(this, 1, i));
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int par1)
    {
        return this.icons[MathHelper.clamp_int(par1, 0, icons.length - 1)];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register)
    {
        this.icons = new IIcon[this.info.length];
        String setName = this.getItemSetName();

        for (int i = 0; i < this.info.length; ++i)
        {
            String name = this.info[i].name;
            if (getItemSetName() != null)
            {
                this.icons[i] = register.registerIcon(TofuFactory.resourceDomain + setName + "/" + setName + Utils.capitalize(name));
            }
            else
            {
                this.icons[i] = register.registerIcon(TofuFactory.resourceDomain + name);
            }
        }
    }

    public String getItemSetName()
    {
        return null;
    }

    public T getItemInfo(int dmg)
    {
        return this.info[dmg >= 0 && dmg < this.info.length ? dmg : 0];
    }
}
