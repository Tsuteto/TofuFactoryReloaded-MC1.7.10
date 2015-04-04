package tsuteto.tofufactory.ffm.farming.farmable;

import forestry.api.farming.ICrop;
import forestry.farming.logic.FarmableGenericCrop;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tsuteto.tofu.block.TcBlocks;
import tsuteto.tofu.item.TcItems;
import tsuteto.tofufactory.ffm.farming.crop.CropCyclic;
import tsuteto.tofufactory.util.TFVector;

public class FarmableSprouts extends FarmableGenericCrop
{
    private final Block block;
    private final int mature;

    public FarmableSprouts()
    {
        super(new ItemStack(TcItems.soybeans), TcBlocks.sprouts, 7);
        this.block = TcBlocks.sprouts;
        this.mature = 7;
    }

    public ICrop getCropAt(World world, int x, int y, int z)
    {
        return world.isAirBlock(x, y, z) ? null
                : (world.getBlock(x, y, z) != block ? null
                : (world.getBlockMetadata(x, y, z) < mature ? null
                : new CropCyclic(world, block, world.getBlockMetadata(x, y, z), new TFVector(x, y, z))));
    }
}
