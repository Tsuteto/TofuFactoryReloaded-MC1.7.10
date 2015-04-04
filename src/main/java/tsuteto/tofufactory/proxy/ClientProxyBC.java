package tsuteto.tofufactory.proxy;

public class ClientProxyBC extends CommonProxyBC
{
    public void registerIcons()
    {
        //MinecraftForge.EVENT_BUS.register(new IconTexture());
    }

    public void registerRenderersBC()
    {
//        MinecraftForgeClient.registerItemRenderer(PluginBC.pipeItemsTofuIshi, TransportProxyClient.pipeItemRenderer);
//        MinecraftForgeClient.registerItemRenderer(PluginBC.pipeFluidsTofuIshi, TransportProxyClient.pipeItemRenderer);
//        MinecraftForgeClient.registerItemRenderer(PluginBC.pipePowerTofuIshi, TransportProxyClient.pipeItemRenderer);
//        MinecraftForgeClient.registerItemRenderer(PluginBC.pipeItemsZunda, TransportProxyClient.pipeItemRenderer);
//        MinecraftForgeClient.registerItemRenderer(PluginBC.pipeFluidsZunda, TransportProxyClient.pipeItemRenderer);
//        MinecraftForgeClient.registerItemRenderer(PluginBC.pipePowerZunda, TransportProxyClient.pipeItemRenderer);
        //Localization.addLocalization("/lang/tofufactory/pipes/", "en_US");
    }

//    @SideOnly(Side.CLIENT)
//    public static class IconTexture
//    {
//        @SubscribeEvent
//        public void textureHook(TextureStitchEvent.Pre event)
//        {
//            int type = event.map.getTextureType();
//            if (type == 0)
//            {
////                TFPipeIconProvider.INSTANCE.registerIcons(event.map);
//            }
//        }
//    }
}
