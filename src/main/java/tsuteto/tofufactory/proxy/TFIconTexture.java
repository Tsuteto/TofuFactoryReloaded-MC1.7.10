package tsuteto.tofufactory.proxy;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.TextureStitchEvent.Pre;
import tsuteto.tofu.TofuCraftCore;
import tsuteto.tofufactory.core.TofuFactory;
import tsuteto.tofufactory.integration.plugins.PluginBC;

@SideOnly(Side.CLIENT)
public class TFIconTexture
{
    private static TFIconTexture instance;

    public static IIcon smokeTofu;
    public static IIcon smokeTofuGlow;
    public static IIcon soybeansHell;

    @SubscribeEvent
    public void textureHook(Pre event)
    {
        int type = event.map.getTextureType();
        if (type == 0)
        {
            PluginBC.pipeIconProvider.registerIcons(event.map);
        }

        if (type == 1)
        {
            getInstance().registerIcons(event.map);
        }
    }

    public static TFIconTexture getInstance()
    {
        if (instance == null)
        {
            instance = new TFIconTexture();
        }

        return instance;
    }

    public void registerIcons(IIconRegister par1IconRegister)
    {
        smokeTofu = par1IconRegister.registerIcon(TofuFactory.resourceDomain + "smokeTofu");
        smokeTofuGlow = par1IconRegister.registerIcon(TofuFactory.resourceDomain + "smokeTofuGlow");
        soybeansHell = par1IconRegister.registerIcon(TofuCraftCore.resourceDomain + "seeds_soybeansHell");
    }
}
