package tsuteto.tofufactory.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import tsuteto.tofufactory.item.ItemSetInfo;

abstract public class BlockSetColored<T extends ItemSetInfo & BlockSetColored.IBlockColorInfo> extends BlockSetBase<T>
{
    protected BlockSetColored(Material p_i45394_1_, T[] info)
    {
        super(p_i45394_1_, info);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return this.blockIcon;
    }

    @Override
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
    {
        int meta = p_149720_1_.getBlockMetadata(p_149720_2_, p_149720_3_, p_149720_4_);
        return info[meta >= 0 && meta < info.length ? meta : 0].getRenderColor();
    }

    @Override
    public int getRenderColor(int meta)
    {
        return info[meta >= 0 && meta < info.length ? meta : 0].getRenderColor();
    }

    @Override
    public boolean canRenderInPass(int pass)
    {
        return super.canRenderInPass(pass);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = p_149651_1_.registerIcon(this.getTextureName());
    }

    public interface IBlockColorInfo
    {
        public int getRenderColor();
    }
}
