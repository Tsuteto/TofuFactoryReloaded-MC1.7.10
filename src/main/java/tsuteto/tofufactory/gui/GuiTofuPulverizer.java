package tsuteto.tofufactory.gui;

import net.minecraft.entity.player.InventoryPlayer;
import tsuteto.tofufactory.tileentity.TileEntityFactoryTfMachine;

public class GuiTofuPulverizer extends GuiFactoryMachine
{
    public GuiTofuPulverizer(InventoryPlayer par1InventoryPlayer, TileEntityFactoryTfMachine par2TileEntityTofuMachine)
    {
        super(new ContainerPulverizer(par1InventoryPlayer, par2TileEntityTofuMachine), par2TileEntityTofuMachine,
                FactoryMachineGuiParts.progressPulverizerBg, FactoryMachineGuiParts.progressPulverizer);
    }
}
