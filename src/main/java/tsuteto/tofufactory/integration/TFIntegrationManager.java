package tsuteto.tofufactory.integration;

import com.google.common.collect.Lists;
import net.minecraftforge.common.config.Configuration;
import tsuteto.tofufactory.integration.slot.PluginSlot;
import tsuteto.tofufactory.integration.slot.PluginSlotBC;
import tsuteto.tofufactory.integration.slot.PluginSlotGT5;

import java.util.List;

public class TFIntegrationManager
{
    public static final PluginSlot modBC;
    public static final PluginSlot modFFM;
    public static final PluginSlot modIC2;
    public static final PluginSlot modFC;
    public static final PluginSlot modGT5;
    public static final PluginSlot modGT6;
    public static final PluginSlot modMT;
    public static final PluginSlot modBamboo;
    public static final PluginSlot modAE;
    public static final PluginSlot modTC;
    public static final PluginSlot modTCon;
    public static final PluginSlot modCG;

    private static List<PluginSlot> slotList = Lists.newArrayList();

    static
    {
        modBC       = registerPlugin(new PluginSlotBC("BuildCraft", ModIDs.BC, "BC").withRecipes());
        modFFM      = registerPlugin(new PluginSlot("Forestry", ModIDs.FFM, "FFM").withRecipes());
        modIC2      = registerPlugin(new PluginSlot("IC2", ModIDs.IC2, "IC2").withRecipes());
        modFC       = registerPlugin(new PluginSlot("FarmCraftory", ModIDs.FC, "FC")); // 1.6.2 yet
        modGT5      = registerPlugin(new PluginSlotGT5("GregTech5", ModIDs.GT5, "Greg5").withRecipes());
        modGT6      = registerPlugin(new PluginSlot("GregTech6", ModIDs.GT6, "Greg6").withRecipes());
        modMT       = registerPlugin(new PluginSlot("MapleTree", ModIDs.MT, "MT"));
        modBamboo   = registerPlugin(new PluginSlot("BambooMod", ModIDs.Bamboo, "Bamboo"));
        modAE       = registerPlugin(new PluginSlot("AppliedEnergistics", ModIDs.AE, "Appeng").withRecipes());
        modTC       = registerPlugin(new PluginSlot("Thaumcraft", ModIDs.TC, "TC").withRecipes());
        modTCon     = registerPlugin(new PluginSlot("TConstruct", ModIDs.TCon, "Tcon").withRecipes());

        modCG       = registerPlugin(new PluginSlot("CraftGuide", ModIDs.CG, "CraftGuide"));
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

    public static void preInitPlugins()
    {
        for (PluginSlot slot : slotList)
        {
            slot.callPlugin();
            slot.callPreInit();
        }
    }

    public static void initPlugins()
    {
        for (PluginSlot slot : slotList)
        {
            slot.callInit();
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
