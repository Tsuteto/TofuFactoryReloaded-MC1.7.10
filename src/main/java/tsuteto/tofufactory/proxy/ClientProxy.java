package tsuteto.tofufactory.proxy;

import buildcraft.transport.TransportProxyClient;
import net.minecraftforge.client.MinecraftForgeClient;
import tsuteto.tofufactory.integration.plugins.PluginBC;
import tsuteto.tofufactory.integration.plugins.PluginBC;

public class ClientProxy extends CommonProxy
{
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
}
