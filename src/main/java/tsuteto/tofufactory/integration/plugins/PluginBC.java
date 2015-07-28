package tsuteto.tofufactory.integration.plugins;

import buildcraft.BuildCraftTransport;
import buildcraft.transport.BlockGenericPipe;
import buildcraft.transport.PipeTransportFluids;
import buildcraft.transport.PipeTransportPower;
import buildcraft.transport.TransportProxyClient;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import tsuteto.tofufactory.core.TofuFactory;
import tsuteto.tofufactory.integration.ITFPlugin;
import tsuteto.tofufactory.pipe.*;

public class PluginBC implements ITFPlugin
{
    public static Item pipeItemsTofuIshi;
    public static Item pipeFluidsTofuIshi;
    public static Item pipePowerTofuIshi;
    public static Item pipeItemsZunda;
    public static Item pipeFluidsZunda;
    public static Item pipePowerZunda;

    @Override
    public void preInit() throws Exception
    {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
        {
            MinecraftForge.EVENT_BUS.register(new IconTexture());
        }
    }

    public void init() throws Exception
    {
        // Pipe Items
        pipeItemsTofuIshi = BlockGenericPipe.registerPipe(PipeItemsTofuIshi.class, null);
        pipeItemsTofuIshi.setUnlocalizedName(TofuFactory.resourceDomain + "pipe.item.tofuIshi").setCreativeTab(TofuFactory.tabsTofuFactory);

        PipeTransportFluids.fluidCapacities.put(PipeFluidsTofuIshi.class, 2 * BuildCraftTransport.pipeFluidsBaseFlowRate);
        pipeFluidsTofuIshi = BlockGenericPipe.registerPipe(PipeFluidsTofuIshi.class, null);
        pipeFluidsTofuIshi.setUnlocalizedName(TofuFactory.resourceDomain + "pipe.fluid.tofuIshi").setCreativeTab(TofuFactory.tabsTofuFactory);

        PipeTransportPower.powerCapacities.put(PipePowerTofuIshi.class, 128);
        pipePowerTofuIshi = BlockGenericPipe.registerPipe(PipePowerTofuIshi.class, null);
        pipePowerTofuIshi.setUnlocalizedName(TofuFactory.resourceDomain + "pipe.power.tofuIshi").setCreativeTab(TofuFactory.tabsTofuFactory);

        pipeItemsZunda = BlockGenericPipe.registerPipe(PipeItemsZunda.class, null);
        pipeItemsZunda.setUnlocalizedName(TofuFactory.resourceDomain + "pipe.item.zunda").setCreativeTab(TofuFactory.tabsTofuFactory);

        PipeTransportFluids.fluidCapacities.put(PipeFluidsZunda.class, 2 * BuildCraftTransport.pipeFluidsBaseFlowRate);
        pipeFluidsZunda = BlockGenericPipe.registerPipe(PipeFluidsZunda.class, null);
        pipeFluidsZunda.setUnlocalizedName(TofuFactory.resourceDomain + "pipe.fluid.zunda").setCreativeTab(TofuFactory.tabsTofuFactory);

        PipeTransportPower.powerCapacities.put(PipePowerZunda.class, 128);
        pipePowerZunda = BlockGenericPipe.registerPipe(PipePowerZunda.class, null);
        pipePowerZunda.setUnlocalizedName(TofuFactory.resourceDomain + "pipe.power.zunda").setCreativeTab(TofuFactory.tabsTofuFactory);

        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
        {
            this.registerRenderersBC();
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerRenderersBC()
    {
        MinecraftForgeClient.registerItemRenderer(PluginBC.pipeItemsTofuIshi, TransportProxyClient.pipeItemRenderer);
        MinecraftForgeClient.registerItemRenderer(PluginBC.pipeFluidsTofuIshi, TransportProxyClient.pipeItemRenderer);
        MinecraftForgeClient.registerItemRenderer(PluginBC.pipePowerTofuIshi, TransportProxyClient.pipeItemRenderer);
        MinecraftForgeClient.registerItemRenderer(PluginBC.pipeItemsZunda, TransportProxyClient.pipeItemRenderer);
        MinecraftForgeClient.registerItemRenderer(PluginBC.pipeFluidsZunda, TransportProxyClient.pipeItemRenderer);
        MinecraftForgeClient.registerItemRenderer(PluginBC.pipePowerZunda, TransportProxyClient.pipeItemRenderer);
        //Localization.addLocalization("/lang/tofufactory/pipes/", "en_US");
    }

    @SideOnly(Side.CLIENT)
    public static class IconTexture
    {
        @SubscribeEvent
        public void textureHook(TextureStitchEvent.Pre event)
        {
            int type = event.map.getTextureType();
            if (type == 0)
            {
                TFPipeIconProvider.INSTANCE.registerIcons(event.map);
            }
        }
    }

}
