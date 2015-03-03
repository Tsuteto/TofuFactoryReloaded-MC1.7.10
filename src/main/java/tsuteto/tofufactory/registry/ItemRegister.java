package tsuteto.tofufactory.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ItemRegister<T extends Item>
{
    private static String resourceDomain = "";

    public static void setResourceDomain(String resourceDomain)
    {
        ItemRegister.resourceDomain = resourceDomain;
    }

    public static <T extends Item> ItemRegister<T> of(String name, T item)
    {
        return new ItemRegister<T>(name, item);
    }

    T item;
    String uniqueName;
    String resourceName;

    private ItemRegister(String name, T item)
    {
        this.item = item;
        this.uniqueName = name;
        this.resourceName = name;
    }

    public ItemRegister<T> withResource(String name)
    {
        this.resourceName = name;
        return this;
    }

    public T register()
    {
        item.setUnlocalizedName(resourceDomain + resourceName);
        item.setTextureName(resourceDomain + resourceName);
        GameRegistry.registerItem(item, uniqueName);
        return item;
    }
}
