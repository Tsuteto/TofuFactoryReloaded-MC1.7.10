package tsuteto.tofufactory.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import tsuteto.tofufactory.api.recipes.IMachineRecipe;
import tsuteto.tofufactory.api.recipes.RecipeManagers;
import tsuteto.tofufactory.tileentity.TileEntityFactoryTfMachine;

public class ContainerPulverizer extends ContainerFactoryMachine
{
    public ContainerPulverizer(InventoryPlayer par1InventoryPlayer, TileEntityFactoryTfMachine par2TileEntityFurnace)
    {
        super(par1InventoryPlayer, par2TileEntityFurnace);
    }

    public IMachineRecipe getMachineRecipe()
    {
        return RecipeManagers.pulverizerManager;
    }

    public SlotTofuMachine getMachineSlot(EntityPlayer player, TileEntityFactoryTfMachine par2TileEntityFurnace, int i, int j, int k)
    {
        return new SlotPulverizer(player, par2TileEntityFurnace, i, j, k);
    }
}
