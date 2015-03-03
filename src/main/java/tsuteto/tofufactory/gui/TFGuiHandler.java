package tsuteto.tofufactory.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tsuteto.tofufactory.tileentity.TileEntityTofuCompactor;
import tsuteto.tofufactory.tileentity.TileEntityTofuCuttingMachine;
import tsuteto.tofufactory.tileentity.TileEntityTofuPulverizer;

public class TFGuiHandler implements IGuiHandler
{
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile;

        if (ID == 0)
        {
            tile = world.getTileEntity(x, y, z);

            if (tile instanceof TileEntityTofuPulverizer)
            {
                return new GuiTofuPulverizer(player.inventory, (TileEntityTofuPulverizer)tile);
            }
        }

        if (ID == 1)
        {
            tile = world.getTileEntity(x, y, z);

            if (tile instanceof TileEntityTofuCompactor)
            {
                return new GuiTofuCompactor(player.inventory, (TileEntityTofuCompactor)tile);
            }
        }

        if (ID == 2)
        {
            tile = world.getTileEntity(x, y, z);

            if (tile instanceof TileEntityTofuCuttingMachine)
            {
                return new GuiTofuCuttingMachine(player.inventory, (TileEntityTofuCuttingMachine)tile);
            }
        }

        return null;
    }

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile;

        if (ID == 0)
        {
            tile = world.getTileEntity(x, y, z);

            if (tile instanceof TileEntityTofuPulverizer)
            {
                return new ContainerPulverizer(player.inventory, (TileEntityTofuPulverizer)tile);
            }
        }

        if (ID == 1)
        {
            tile = world.getTileEntity(x, y, z);

            if (tile instanceof TileEntityTofuCompactor)
            {
                return new ContainerCompactor(player.inventory, (TileEntityTofuCompactor)tile);
            }
        }

        if (ID == 2)
        {
            tile = world.getTileEntity(x, y, z);

            if (tile instanceof TileEntityTofuCuttingMachine)
            {
                return new ContainerCuttingMachine(player.inventory, (TileEntityTofuCuttingMachine)tile);
            }
        }

        return null;
    }
}
