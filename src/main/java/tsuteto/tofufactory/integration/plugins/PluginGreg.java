package tsuteto.tofufactory.integration.plugins;

import gregtech.api.GregTech_API;
import ic2.api.crops.CropCard;
import net.minecraft.item.ItemStack;
import tsuteto.tofufactory.integration.ITFPlugin;

public class PluginGreg implements ITFPlugin
{
    public static CropCard daizu;

    public void init()
    {
        registerOreDictionaryGreg();
    }

    public void registerOreDictionaryGreg()
    {
//        Item GTItem = GameRegistry.findItem("gregtech", "");
//        if (GTItem != null)
//        {
//            OreDictionary.registerOre("cookingFlour", new ItemStack(GTItem, 1, 14));
//        }
    }

    public static ItemStack getOreDictStack(ItemStack aOreStack)
    {
        return GregTech_API.getUnificatedOreDictStack(aOreStack);
    }
}
