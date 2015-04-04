package tsuteto.tofufactory.ffm.farming.farmable;

import forestry.api.farming.ICrop;
import forestry.api.farming.IFarmable;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tsuteto.tofu.block.TcBlocks;
import tsuteto.tofu.item.TcItems;
import tsuteto.tofufactory.ffm.farming.crop.CropCyclic;
import tsuteto.tofufactory.util.TFVector;

public class FarmableEdamame implements IFarmable
{
    public static final ItemStack seed = new ItemStack(TcItems.soybeans);
    public static final Block block = TcBlocks.soybean;
    public static final int mature = 5;

    public boolean isSaplingAt(World world, int x, int y, int z)
    {
        return !world.isAirBlock(x, y, z) && world.getBlock(x, y, z) == block;
    }

    public ICrop getCropAt(World world, int x, int y, int z)
    {
        return world.isAirBlock(x, y, z) ? null
                : (world.getBlock(x, y, z) != block ? null
                : (world.getBlockMetadata(x, y, z) < mature ? null
                : new CropCyclic(world, block, world.getBlockMetadata(x, y, z), new TFVector(x, y, z))));
    }

    public boolean isGermling(ItemStack itemstack)
    {
        return seed.getItem() == itemstack.getItem();
    }

    public boolean plantSaplingAt(EntityPlayer player, ItemStack germling, World world, int x, int y, int z)
    {
        return germling.copy().tryPlaceItemIntoWorld(player, world, x, y - 1, z, 1, 0.0F, 0.0F, 0.0F);
    }

    public boolean isWindfall(ItemStack itemstack) {
        return false;
    }
}
