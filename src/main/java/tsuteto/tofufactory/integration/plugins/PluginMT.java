package tsuteto.tofufactory.integration.plugins;

import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import tsuteto.tofufactory.integration.ITFPlugin;

public class PluginMT implements ITFPlugin
{
    public static Item Item_tomato;
    public static Item Item_eggplant;
    public static Item Item_onion;
    public static Item Item_flour;
    public static Item Item_salt;
    public static Item Item_dough;
    public static Item Item_cheese;

    @Override
    public void preInit() throws Exception {}

    public void init() throws Exception
    {
        registerOreDictionary();
    }

    public void registerOreDictionary() throws Exception
    {
        Item_tomato = (Item)Class.forName("ecru.MapleTree.mod_ecru_MapleTree").getField("Item_tomato").get(Item.class);
        Item_eggplant = (Item)Class.forName("ecru.MapleTree.mod_ecru_MapleTree").getField("Item_eggplant").get(Item.class);
        Item_onion = (Item)Class.forName("ecru.MapleTree.mod_ecru_MapleTree").getField("Item_onion").get(Item.class);
        Item_flour = (Item)Class.forName("ecru.MapleTree.mod_ecru_MapleTree").getField("Item_flour").get(Item.class);
        Item_salt = (Item)Class.forName("ecru.MapleTree.mod_ecru_MapleTree").getField("Item_salt").get(Item.class);
        Item_dough = (Item)Class.forName("ecru.MapleTree.mod_ecru_MapleTree").getField("Item_dough").get(Item.class);
        Item_cheese = (Item)Class.forName("ecru.MapleTree.mod_ecru_MapleTree").getField("Item_cheese").get(Item.class);

        OreDictionary.registerOre("vegetableTomato", Item_tomato);
        OreDictionary.registerOre("vegetableEggplant", Item_eggplant);
        OreDictionary.registerOre("vegetableOnion", Item_onion);
        OreDictionary.registerOre("cookingFlour", Item_flour);
        OreDictionary.registerOre("flour", Item_flour);
        OreDictionary.registerOre("cookingSalt", Item_salt);
        OreDictionary.registerOre("salt", Item_salt);
        OreDictionary.registerOre("cookingCheese", Item_cheese);
    }
}
