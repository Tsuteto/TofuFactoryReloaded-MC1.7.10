package tsuteto.tofufactory.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tsuteto.tofu.util.ItemUtils;
import tsuteto.tofu.util.Utils;

public abstract class ItemSetInfo
{
    public final int id;
    public final String name;
    public ItemStack container = null;
    public int itemUseDuration = 0;

    public ItemSetInfo(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public ItemSetInfo setContainerItem(ItemStack stack)
    {
        this.container = stack;
        return this;
    }

    public ItemSetInfo setMaxItemUseDuration(int itemUseDuration)
    {
        this.itemUseDuration = itemUseDuration;
        return this;
    }

    public ItemStack getContainerItem()
    {
        if (container != null)
        {
            return container.copy();
        }
        else
        {
            return null;
        }
    }

    public boolean hasContainerItem()
    {
        return container != null;
    }

    public ItemStack getStack()
    {
        return this.getStack(1);
    }

    public ItemStack getStack(int qty)
    {
        return new ItemStack(this.getItemInstance(), qty, this.id);
    }

    abstract protected Item getItemInstance();
}
