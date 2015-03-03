package tsuteto.tofufactory.item;

import net.minecraft.item.Item;
import tsuteto.tofufactory.core.TFItems;

public class ItemTFDust extends ItemSetMaterial
{
    public static final Info tofuSteel = new Info(TFItems.tofuSteel);
    public static final Info mithrilTofu = new Info(TFItems.mithrilTofu);
    public static final Info sol = new Info(TFItems.sol);
    public static final Info luna = new Info(TFItems.luna);
    public static final Info iron = new Info(4, "iron");
    public static final Info diamondTofu = new Info(5, "diamondTofu");
    public static final Info mithril = new Info(6, "mithril");
    public static final Info[] dusts = new Info[]{tofuSteel, mithrilTofu, sol, luna, iron, diamondTofu, mithril};

    public ItemTFDust()
    {
        super(dusts);
    }

    public boolean isDamageable()
    {
        return false;
    }

    public boolean isRepairable()
    {
        return false;
    }

    @Override
    public String getItemSetName()
    {
        return "dust";
    }

    public static class Info extends ItemMaterialInfo
    {
        public Info(TFItems.CommonMaterial m)
        {
            super(m);
        }

        public Info(int id, String name)
        {
            super(id, name);
        }

        @Override
        protected Item getItemInstance()
        {
            return TFItems.tofuDust;
        }
    }

}
