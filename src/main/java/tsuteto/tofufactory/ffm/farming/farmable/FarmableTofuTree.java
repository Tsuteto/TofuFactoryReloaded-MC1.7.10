package tsuteto.tofufactory.ffm.farming.farmable;

import forestry.api.farming.ICrop;
import forestry.core.utils.Vect;
import forestry.farming.logic.CropBlock;
import forestry.farming.logic.FarmableGenericSapling;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import tsuteto.tofu.block.TcBlocks;

public class FarmableTofuTree extends FarmableGenericSapling
{
    public FarmableTofuTree()
    {
        super(TcBlocks.tcSapling, 1);
    }

    @Override
    public ICrop getCropAt(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        return block != TcBlocks.tofuIshi ? null : new CropBlock(world, block, world.getBlockMetadata(x, y, z), new Vect(x, y, z));
    }

}
