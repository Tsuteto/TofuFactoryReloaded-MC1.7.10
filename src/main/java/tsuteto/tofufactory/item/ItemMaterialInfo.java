package tsuteto.tofufactory.item;

import tsuteto.tofufactory.core.TFItems;

public abstract class ItemMaterialInfo extends ItemSetInfo
{

    public ItemMaterialInfo(int id, String name)
    {
        super(id, name);
    }

    public ItemMaterialInfo(TFItems.CommonMaterial m)
    {
        this(m.id, m.name);
    }
}
