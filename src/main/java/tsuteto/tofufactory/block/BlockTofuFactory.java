package tsuteto.tofufactory.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import tsuteto.tofufactory.core.TofuFactory;

public class BlockTofuFactory extends Block
{
    public BlockTofuFactory(Material par2Material)
    {
        super(par2Material);
        this.setCreativeTab(TofuFactory.tabsTofuFactory);
    }

    @Override
    public Block setBlockName(String par1Str)
    {
        super.setBlockName(par1Str);
        return this;
    }
}
