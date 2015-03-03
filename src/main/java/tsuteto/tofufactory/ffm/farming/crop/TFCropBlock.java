package tsuteto.tofufactory.ffm.farming.crop;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tsuteto.tofufactory.util.TFVector;
import tsuteto.tofufactory.ffm.farming.TFCrop;
import tsuteto.tofufactory.ffm.farming.TFCrop;
import tsuteto.tofufactory.util.TFVector;

import java.util.List;

public class TFCropBlock extends TFCrop
{
    Block block;
    int meta;

    public TFCropBlock(World world, Block block, int meta, TFVector pos)
    {
        super(world, pos);
        this.block = block;
        this.meta = meta;
    }

    protected List<ItemStack> harvestBlock(TFVector pos)
    {
        List<ItemStack> harvested = this.block.getDrops(this.world, pos.x, pos.y, pos.z, this.meta, 0);
        this.world.setBlockToAir(pos.x, pos.y, pos.z);
        return harvested;
    }
}
