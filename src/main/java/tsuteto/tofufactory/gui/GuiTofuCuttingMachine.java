package tsuteto.tofufactory.gui;

import net.minecraft.entity.player.InventoryPlayer;
import tsuteto.tofufactory.tileentity.TileEntityFactoryTfMachine;

public class GuiTofuCuttingMachine extends GuiFactoryMachine
{
    public GuiTofuCuttingMachine(InventoryPlayer par1InventoryPlayer, TileEntityFactoryTfMachine par2TileEntityTofuMachine)
    {
        super(new ContainerCuttingMachine(par1InventoryPlayer, par2TileEntityTofuMachine), par2TileEntityTofuMachine,
                FactoryMachineGuiParts.progressCuttingMachineBg, FactoryMachineGuiParts.progressCuttingMachine);
    }
}
