package tsuteto.tofufactory.gui;

import net.minecraft.entity.player.InventoryPlayer;
import tsuteto.tofufactory.tileentity.TileEntityFactoryTfMachine;

public class GuiTofuCompactor extends GuiFactoryMachine
{
    public GuiTofuCompactor(InventoryPlayer par1InventoryPlayer, TileEntityFactoryTfMachine par2TileEntityTofuMachine)
    {
        super(new ContainerCompactor(par1InventoryPlayer, par2TileEntityTofuMachine), par2TileEntityTofuMachine,
                FactoryMachineGuiParts.progressCompactorBg, FactoryMachineGuiParts.progressCompactor);
    }
}
