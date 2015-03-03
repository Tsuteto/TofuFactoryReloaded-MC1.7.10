package tsuteto.tofufactory.ffm.farming.crop;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tsuteto.tofufactory.util.TFVector;

import java.util.List;

public class CropEdamameBeans extends TFCropBlock
{
    public CropEdamameBeans(World world, Block block, int meta, TFVector pos)
    {
        super(world, block, meta, pos);
    }

    @Override
    protected List<ItemStack> harvestBlock(TFVector pos)
    {
        List<ItemStack> harvested = this.block.getDrops(this.world, pos.x, pos.y, pos.z, this.meta, 0);
        this.world.setBlock(pos.x, pos.y, pos.z, this.block, meta - 2, 2);
        return harvested;
    }
}
