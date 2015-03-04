package tsuteto.tofufactory.core;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tsuteto.tofu.item.TcItems;
import tsuteto.tofu.util.Utils;
import tsuteto.tofufactory.item.*;

public class TFOreDictionary
{
    public static void registerOreDictionary()
    {
        // Register original items
        OreDictionary.registerOre("oreMithril", TFItems.oreMithril);
        OreDictionary.registerOre("oreMithrilTofu", TFItems.oreMithrilTofu);
        OreDictionary.registerOre("flour", TFItems.flour);
        OreDictionary.registerOre("cookingFlour", TFItems.flour);
        OreDictionary.registerOre("ingotZunda", TFItems.zundaIngot);
        OreDictionary.registerOre("foodKamaboko", ItemTFFoodSet.kamaboko.getStack());
        OreDictionary.registerOre("udonNoodles", TFItems.udonNoodles);
        OreDictionary.registerOre("ramenNoodles", TFItems.ramenNoodles);
        OreDictionary.registerOre("craftingMagicCircuit", ItemTFMaterial.magicCircuit.getStack());
        OreDictionary.registerOre("craftingMagicCircuitAdv", ItemTFMaterial.magicCircuitAdv.getStack());

        for (ItemTFDust.Info m : ItemTFDust.dusts)
        {
            OreDictionary.registerOre(getOreDicName("dust", m.name), m.getStack());
        }

        for (ItemTofuIngot.Info m : ItemTofuIngot.ingots)
        {
            OreDictionary.registerOre(getOreDicName("ingot", m.name), m.getStack());
        }

        for (ItemTofuPlate.Info m : ItemTofuPlate.plates)
        {
            OreDictionary.registerOre(getOreDicName("plate", m.name), m.getStack());
        }

        for (ItemTofuGear.Info m : ItemTofuGear.gears)
        {
            OreDictionary.registerOre(getOreDicName("gear", m.name), m.getStack());
        }

        for (ItemTofuGem.Info m : ItemTofuGem.tofuGems)
        {
            OreDictionary.registerOre(getOreDicName("gem", m.name), m.getStack());
        }

        // Integration
        OreDictionary.registerOre("ingotTofuMetal", TcItems.tofuMetal);
        OreDictionary.registerOre("vegetableLeek", TcItems.leek);
        OreDictionary.registerOre("craftingToolForgeHammer", new ItemStack(TFItems.craftingHammer, 1, OreDictionary.WILDCARD_VALUE)); // IC2: Forge Hammer
        OreDictionary.registerOre("craftingMacerator", new ItemStack(TFItems.tofuPulverizerIdle)); // IC2: Macerator (Registered by Greg)
        OreDictionary.registerOre("craftingCompressor", new ItemStack(TFItems.tofuCompactorIdle)); // IC2: Compressor (Registered by Greg)
        OreDictionary.registerOre("craftingDiamondBlade", ItemTFMaterial.tofuBlade.getStack()); // Greg: Diamond Blade
        OreDictionary.registerOre("ringSol", ItemTFMaterial.ringSun.getStack()); // Greg: Ring
        OreDictionary.registerOre("ringLuna", ItemTFMaterial.ringMoon.getStack()); // Greg: Ring
    }


    private static String getOreDicName(String prefix, String name)
    {
        return prefix + Utils.capitalize(name);
    }

}
