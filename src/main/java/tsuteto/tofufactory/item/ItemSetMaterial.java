package tsuteto.tofufactory.item;

import tsuteto.tofufactory.core.TFItems;

public abstract class ItemSetMaterial extends ItemSetBase
{
    public ItemSetMaterial(ItemSetInfo[] list)
    {
        super(list);
    }

    public ItemSetInfo getInfo(TFItems.CommonMaterial m)
    {
        return this.info[m.id];
    }
}
