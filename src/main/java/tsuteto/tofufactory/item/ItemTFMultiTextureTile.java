package tsuteto.tofufactory.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import tsuteto.tofufactory.block.BlockTofuMetal;

public class ItemTFMultiTextureTile extends ItemBlock
{
    protected final ItemSetInfo[] info;

    public ItemTFMultiTextureTile(Block p_i45346_1_, BlockTofuMetal.Info[] info)
    {
        this(p_i45346_1_, (ItemSetInfo[])info);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    public ItemTFMultiTextureTile(Block p_i45346_1_, ItemSetInfo[] info)
    {
        super(p_i45346_1_);
        this.info = info;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int p_77617_1_)
    {
        return this.field_150939_a.getIcon(2, p_77617_1_);
    }

    public int getMetadata(int p_77647_1_)
    {
        return p_77647_1_;
    }

    public String getUnlocalizedName(ItemStack p_77667_1_)
    {
        int i = p_77667_1_.getItemDamage();

        if (i < 0 || i >= this.info.length)
        {
            i = 0;
        }

        return super.getUnlocalizedName() + "." + this.info[i].name;
    }
}
