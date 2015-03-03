package tsuteto.tofufactory.integration;

import com.google.common.collect.Lists;
import net.minecraftforge.common.config.Configuration;
import tsuteto.tofufactory.integration.plugins.*;
import tsuteto.tofufactory.integration.recipes.*;

import java.util.List;

public class TFIntegrationManager
{
    public static final PluginSlot modBuildCraft;
    public static final PluginSlot modForestry;
    public static final PluginSlot modIC2;
    public static final PluginSlot modFC;
    public static final PluginSlot modGT;
    public static final PluginSlot modMT;
    public static final PluginSlot modBamboo;
    public static final PluginSlot modAppeng;
    public static final PluginSlot modTC;
    public static final PluginSlot modTConstruct;
    public static final PluginSlot modCraftGuide;

    private static List<PluginSlot> slotList = Lists.newArrayList();

    static
    {
        modBuildCraft   = registerPlugin(new PluginSlotRequired("BuildCraft", "BuildCraft|Core", PluginBC.class).addRecipes(RecipeBC.class));
        modForestry     = registerPlugin(new PluginSlot("Forestry", "Forestry", PluginFFM.class).addRecipes(RecipeFFM.class));
        modIC2          = registerPlugin(new PluginSlot("IC2", "IC2", PluginIC2.class).addRecipes(RecipeIC2.class));
        modFC           = registerPlugin(new PluginSlot("FarmCraftory", "FarmCraftory", PluginFC.class)); // 1.6.2 yet
        modGT           = registerPlugin(new PluginSlot("GregTech", "gregtech", PluginGreg.class).addRecipes(RecipeGreg.class));
        modMT           = registerPlugin(new PluginSlot("MapleTree", "mod_ecru_MapleTree", PluginMT.class));
        modBamboo       = registerPlugin(new PluginSlot("BambooMod", "BambooMod", PluginBamboo.class));
        modAppeng       = registerPlugin(new PluginSlot("AppliedEnergistics", "appliedenergistics2", PluginAppeng.class).addRecipes(RecipeAppeng.class));
        modTC           = registerPlugin(new PluginSlot("Thaumcraft", "Thaumcraft", PluginTC.class).addRecipes(RecipeTC.class));
        modTConstruct   = registerPlugin(new PluginSlot("TConstruct", "TConstruct", PluginTcon.class).addRecipes(RecipeTcon.class));

        modCraftGuide   = registerPlugin(new PluginSlot("CraftGuide", "craftguide", PluginCraftGuide.class));
    }

    private static PluginSlot registerPlugin(PluginSlot slot)
    {
        slotList.add(slot);
        return slot;
    }

    public static void loadConfig(Configuration cfg)
    {
        for (PluginSlot slot : slotList)
        {
            slot.loadConfig(cfg);
        }
    }

    public static void initPlugins()
    {
        for (PluginSlot slot : slotList)
        {
            slot.callPlugin();
        }
    }

    public static void registerRecipes()
    {
        for (PluginSlot slot : slotList)
        {
            slot.registerRecipes();
        }
    }

    public static void assertPluginLoaded()
    {
        for (PluginSlot slot : slotList)
        {
            slot.assertPluginLoaded();
        }
    }

}
