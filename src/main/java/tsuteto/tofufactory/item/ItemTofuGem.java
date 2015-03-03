package tsuteto.tofufactory.item;

import net.minecraft.item.Item;
import tsuteto.tofufactory.core.TFItems;

public class ItemTofuGem extends ItemSetBase
{
    public static final Info emeraldTofu = new Info(0, "emeraldTofu");
    public static final Info peridotTofu = new Info(1, "peridotTofu");
    public static final Info rubyTofu = new Info(2, "rubyTofu");
    public static final Info sapphireTofu = new Info(3, "sapphireTofu");
    public static final Info sugiliteTofu = new Info(4, "sugiliteTofu");
    public static final Info topazTofu = new Info(5, "topazTofu");
    public static final Info[] tofuGems = new Info[]{emeraldTofu, peridotTofu, rubyTofu, sapphireTofu, sugiliteTofu, topazTofu};

    public ItemTofuGem()
    {
        super(tofuGems);
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

    @Override
    public String getItemSetName()
    {
        return "gem";
    }

    public static class Info extends ItemSetInfo
    {
        public Info(int id, String name)
        {
            super(id, name);
        }

        @Override
        protected Item getItemInstance()
        {
            return TFItems.tofuGem;
        }
    }

}
