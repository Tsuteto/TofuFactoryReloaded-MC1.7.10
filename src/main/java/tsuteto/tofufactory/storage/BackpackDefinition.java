package tsuteto.tofufactory.storage;

import forestry.api.storage.IBackpackDefinition;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class BackpackDefinition implements IBackpackDefinition
{
    private ArrayList<ItemStack> items;
    private String key;
    private String name;
    private int colour;

    public BackpackDefinition(String backpackKey, ArrayList<ItemStack> item, String backpackName, int backpackColour)
    {
        this.items = item;
        this.name = backpackName;
        this.key = backpackKey;
        this.colour = backpackColour;
    }

    @Override
    public String getKey()
    {
        return this.key;
    }

    @Override
    public String getName(ItemStack itemStack)
    {
        return StatCollector.translateToLocal("item." + this.name + ".name");
    }

    @Override
    public int getPrimaryColour()
    {
        return this.colour;
    }

    @Override
    public int getSecondaryColour()
    {
        return 16777215;
    }

    @Override
    public void addValidItem(ItemStack validItem) {
        if(validItem.getItem() != null)
        {
            this.items.add(validItem);
        }
    }

    @Override
    public void addValidItems(List<ItemStack> validItems) {
        for (ItemStack validItem : validItems)
        {
            this.addValidItem(validItem);
        }
    }

    @Override
    public boolean isValidItem(ItemStack itemstack)
    {
        boolean flag = false;

        for (ItemStack item : this.items)
        {
            if (OreDictionary.itemMatches(item, itemstack, false))
            {
                flag = true;
                break;
            }
        }

        return flag;
    }
}
