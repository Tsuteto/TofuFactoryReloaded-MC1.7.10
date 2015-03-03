package tsuteto.tofufactory.item;

import net.minecraft.item.Item;
import tsuteto.tofufactory.core.TFItems;

public class ItemTofuPlate extends ItemSetBase
{
    public static final Info tofuSteel = new Info(TFItems.tofuSteel);
    public static final Info mithril = new Info(TFItems.mithrilTofu);
    public static final Info sol = new Info(TFItems.sol);
    public static final Info luna = new Info(TFItems.luna);
    public static final Info[] plates = new Info[]{tofuSteel, mithril, sol, luna};

    public ItemTofuPlate()
    {
        super(plates);
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
        return "plate";
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
            return TFItems.tofuPlate;
        }
    }

}
