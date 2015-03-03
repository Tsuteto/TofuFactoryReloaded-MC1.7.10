package tsuteto.tofufactory.block;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tsuteto.tofufactory.core.TofuFactory;
import tsuteto.tofufactory.core.TFItems;
import tsuteto.tofufactory.tileentity.TileEntityTofuCuttingMachine;

public class BlockTofuCuttingMachine extends BlockTofuMachine
{
    public BlockTofuCuttingMachine(boolean b)
    {
        super(b);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityTofuCuttingMachine();
    }

    public Block getBlockIdle()
    {
        return TFItems.tofuCuttingMachineIdle;
    }

    public Block getBlockActive()
    {
        return TFItems.tofuCuttingMachineActive;
    }

    public void displayGUIBook(EntityPlayer par5EntityPlayer, World par1World, int par2, int par3, int par4)
    {
        par5EntityPlayer.openGui(TofuFactory.instance, 2, par1World, par2, par3, par4);
    }
}
