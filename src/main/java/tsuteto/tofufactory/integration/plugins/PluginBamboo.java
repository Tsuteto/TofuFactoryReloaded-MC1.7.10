package tsuteto.tofufactory.integration.plugins;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tsuteto.tofufactory.integration.ITFPlugin;

import java.util.List;

public class PluginBamboo implements ITFPlugin
{
    @Override
    public void preInit() throws Exception {}

    public void init() throws Exception
    {
        registerOreDictionary();
    }

    public void registerOreDictionary() throws Exception
    {
        List<ItemStack> list;
        list = OreDictionary.getOres("bamboo");
        for (ItemStack item : list)
        {
            OreDictionary.registerOre("vegetableBambooShoot", item);
        }
        list = OreDictionary.getOres("foodFlour");
        for (ItemStack item : list)
        {
            OreDictionary.registerOre("cookingFlour", item);
        }
    }
}
