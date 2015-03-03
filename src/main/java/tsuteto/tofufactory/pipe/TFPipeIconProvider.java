package tsuteto.tofufactory.pipe;

import buildcraft.api.core.IIconProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import tsuteto.tofufactory.core.TofuFactory;

public class TFPipeIconProvider implements IIconProvider
{
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int pipeIconIndex)
    {
        return pipeIconIndex == -1 ? null : TYPE.values()[pipeIconIndex].getIcon();
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        for (TYPE type : TYPE.values())
        {
            type.registerIcon(iconRegister);
        }
    }

    public static enum TYPE
    {
        PipeItemsTofuIshi("pipeItemsTofuIshi"),
        PipeFluidsTofuIshi("pipeFluidsTofuIshi"),
        PipePowerTofuIshi("pipePowerTofuIshi"),
        PipeAllZunda_Solid("pipeAllZunda_solid"),
        PipeItemsZunda_Standard("pipeItemsZunda_standard"),
        PipeFluidsZunda_Standard("pipeFluidsZunda_standard"),
        PipePowerZunda_Standard("pipePowerZunda_standard");

        private final String iconTag;
        private IIcon icon;

        private TYPE(String iconTag)
        {
            this.iconTag = iconTag;
        }

        private void registerIcon(IIconRegister iconRegister)
        {
            this.icon = iconRegister.registerIcon(TofuFactory.resourceDomain + this.iconTag);
        }

        public IIcon getIcon()
        {
            return this.icon;
        }
    }
}
