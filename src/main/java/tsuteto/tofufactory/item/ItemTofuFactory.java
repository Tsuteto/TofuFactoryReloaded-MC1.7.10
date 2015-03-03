package tsuteto.tofufactory.item;

import net.minecraft.item.Item;
import tsuteto.tofufactory.core.TofuFactory;
import tsuteto.tofufactory.core.TofuFactory;

public class ItemTofuFactory extends Item
{
    public ItemTofuFactory()
    {
        super();
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
