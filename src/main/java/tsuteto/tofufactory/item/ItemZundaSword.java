package tsuteto.tofufactory.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import tsuteto.tofufactory.core.TofuFactory;

public class ItemZundaSword extends ItemSword
{
    public ItemZundaSword(ToolMaterial par2EnumToolMaterial)
    {
        super(par2EnumToolMaterial);
        this.setCreativeTab(TofuFactory.tabsTofuFactory);
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
}
