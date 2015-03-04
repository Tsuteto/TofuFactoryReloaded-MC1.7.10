package tsuteto.tofufactory.core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import tsuteto.tofufactory.achievement.TFTriggerManager;
import tsuteto.tofufactory.config.TFConfig;
import tsuteto.tofufactory.fluid.TFFluids;
import tsuteto.tofufactory.gui.TFGuiHandler;
import tsuteto.tofufactory.integration.TFIntegrationManager;
import tsuteto.tofufactory.machinerecipe.TofuMachineRecipe;
import tsuteto.tofufactory.proxy.CommonProxy;
import tsuteto.tofufactory.proxy.TFIconTexture;
import tsuteto.tofufactory.registry.BlockRegister;
import tsuteto.tofufactory.registry.ItemRegister;
import tsuteto.tofufactory.util.UpdateNotification;
import tsuteto.tofufactory.village.TradeHandlerPriest;

@Mod(
    modid = TofuFactory.modId,
    name = "TofuFactory Reloaded",
    version = TofuFactory.version,
    dependencies = "required-after:TofuCraft;required-after:BuildCraft|Core",
    acceptedMinecraftVersions = "[1.7.10,1.8)"
)
public class TofuFactory
{
    public static final String modId = "TofuFactory";
    public static final String version = "1.0.1-MC1.7.10";
    public static final String resourceDomain = "tofufactory:";
    public static final TFLog log = new TFLog(TofuFactory.modId, Boolean.valueOf(System.getProperty("tofufactory.debug", "false")));

    @Instance(modId)
    public static TofuFactory instance;

    @Mod.Metadata(modId)
    public ModMetadata modMetadata;

    @SidedProxy(
        clientSide = "tsuteto.tofufactory.proxy.ClientProxy",
        serverSide = "tsuteto.tofufactory.proxy.CommonProxy"
    )
    public static CommonProxy proxy;

    public static final CreativeTabs tabsTofuFactory = new CreativeTabTofuFactory("TofuFactory");
    public static UpdateNotification update = null;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        log.info("Starting TofuFactory {}", version);
        ModInfo.load(modMetadata);
        TFConfig.ConfigRead(event);

        ItemRegister.setResourceDomain(resourceDomain);
        BlockRegister.setResourceDomain(resourceDomain);

        TFFluids.registerFluids();

        if (event.getSide() == Side.CLIENT)
        {
            MinecraftForge.EVENT_BUS.register(new TFFluids());
            MinecraftForge.EVENT_BUS.register(new TFIconTexture());
        }

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new TFGuiHandler());
        VillagerRegistry.instance().registerVillageTradeHandler(2 /*Priest*/, new TradeHandlerPriest());

        TofuMachineRecipe.initRecipe();
        TFItems.init();
        TFRecipes.register();
        TFAchievementManager.initAchievement();

        // Update check!
        if (TFConfig.updateCheck)
        {
            update = new UpdateNotification();
            update.checkUpdate();
        }
    }

    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new TFTriggerManager());
        TFOreDictionary.registerOreDictionary();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        TFIntegrationManager.initPlugins();

        TFIntegrationManager.registerRecipes();

        TFIntegrationManager.assertPluginLoaded();
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        // Notify if update is available
        if (update != null && event.getSide() == Side.SERVER)
        {
            update.notifyUpdate(event.getServer(), event.getSide());
        }
    }
}
