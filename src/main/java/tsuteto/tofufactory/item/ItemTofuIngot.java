package tsuteto.tofufactory.item;

import net.minecraft.item.Item;
import tsuteto.tofufactory.core.TFItems;

public class ItemTofuIngot extends ItemSetBase
{
    public static final Info tofuSteel = new Info(TFItems.tofuSteel);
    public static final Info mithrilTofu = new Info(TFItems.mithrilTofu);
    public static final Info sol = new Info(TFItems.sol);
    public static final Info luna = new Info(TFItems.luna);
    public static final Info[] ingots = new Info[]{tofuSteel, mithrilTofu, sol, luna};

    public ItemTofuIngot()
    {
        super(ingots);
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
        return "ingot";
    }

    public static class Info extends ItemMaterialInfo
    {
        public Info(TFItems.CommonMaterial m)
        {
            super(m);
        }

        @Override
        protected Item getItemInstance()
        {
            return TFItems.tofuIngot;
        }
    }

}
