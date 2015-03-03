package tsuteto.tofufactory.tileentity;

import tsuteto.tofufactory.api.recipes.IMachineRecipe;
import tsuteto.tofufactory.api.recipes.RecipeManagers;
import tsuteto.tofufactory.block.BlockTofuMachine;
import tsuteto.tofufactory.core.TFItems;

public class TileEntityTofuCuttingMachine extends TileEntityFactoryTfMachine
{
    public String getInventoryNameTranslate()
    {
        return "container.tofufactory:tfCuttingMachine";
    }

    public BlockTofuMachine getMachineBlock()
    {
        return (BlockTofuMachine) TFItems.tofuCuttingMachineActive;
    }

    public IMachineRecipe getMachineRecipe()
    {
        return RecipeManagers.cuttingMachineManager;
    }
}
