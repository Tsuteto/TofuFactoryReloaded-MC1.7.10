package tsuteto.tofufactory.integration.plugins;

import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import tsuteto.tofufactory.integration.ITFPlugin;

public class PluginFC implements ITFPlugin
{
    public static Item emptyGlass;
    public static Item turnipItem;
    public static Item cabbageItem;
    public static Item onionItem;
    public static Item spinachItem;
    public static Item leekItem;
    public static Item cucumberItem;
    public static Item tomatoItem;
    public static Item cornItem;
    public static Item eggplantItem;
    public static Item greenPepperItem;
    public static Item yamItem;
    public static Item strawberryItem;
    public static Item pineappleItem;

    public void init() throws Exception
    {
        registerOreDictionary();
    }

    public void registerOreDictionary() throws Exception
    {
        emptyGlass = (Item)Class.forName("farmcraftory.common.FarmCraftory").getField("emptyGlass").get(Item.class);
        turnipItem = (Item)Class.forName("farmcraftory.common.FarmCraftory").getField("turnipItem").get(Item.class);
        cabbageItem = (Item)Class.forName("farmcraftory.common.FarmCraftory").getField("cabbageItem").get(Item.class);
        onionItem = (Item)Class.forName("farmcraftory.common.FarmCraftory").getField("onionItem").get(Item.class);
        spinachItem = (Item)Class.forName("farmcraftory.common.FarmCraftory").getField("spinachItem").get(Item.class);
        leekItem = (Item)Class.forName("farmcraftory.common.FarmCraftory").getField("leekItem").get(Item.class);
        cucumberItem = (Item)Class.forName("farmcraftory.common.FarmCraftory").getField("cucumberItem").get(Item.class);
        tomatoItem = (Item)Class.forName("farmcraftory.common.FarmCraftory").getField("tomatoItem").get(Item.class);
        cornItem = (Item)Class.forName("farmcraftory.common.FarmCraftory").getField("cornItem").get(Item.class);
        eggplantItem = (Item)Class.forName("farmcraftory.common.FarmCraftory").getField("eggplantItem").get(Item.class);
        greenPepperItem = (Item)Class.forName("farmcraftory.common.FarmCraftory").getField("greenPepperItem").get(Item.class);
        yamItem = (Item)Class.forName("farmcraftory.common.FarmCraftory").getField("yamItem").get(Item.class);
        strawberryItem = (Item)Class.forName("farmcraftory.common.FarmCraftory").getField("strawberryItem").get(Item.class);
        pineappleItem = (Item)Class.forName("farmcraftory.common.FarmCraftory").getField("pineappleItem").get(Item.class);

        OreDictionary.registerOre("containerGlass", emptyGlass);
        OreDictionary.registerOre("vegetableTurnip", turnipItem);
        OreDictionary.registerOre("vegetableCabbage", cabbageItem);
        OreDictionary.registerOre("vegetableOnion", onionItem);
        OreDictionary.registerOre("vegetableSpinach", spinachItem);
        OreDictionary.registerOre("vegetableLeek", leekItem);
        OreDictionary.registerOre("vegetableCucumber", cucumberItem);
        OreDictionary.registerOre("vegetableTomato", tomatoItem);
        OreDictionary.registerOre("vegetableCorn", cornItem);
        OreDictionary.registerOre("vegetableEggplant", eggplantItem);
        OreDictionary.registerOre("vegetableGreenPepper", greenPepperItem);
        OreDictionary.registerOre("vegetableYam", yamItem);
        OreDictionary.registerOre("fruitStrawberry", strawberryItem);
        OreDictionary.registerOre("fruitPineapple", pineappleItem);
    }
}
