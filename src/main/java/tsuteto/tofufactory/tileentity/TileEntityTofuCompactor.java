package tsuteto.tofufactory.tileentity;

import tsuteto.tofufactory.api.recipes.IMachineRecipe;
import tsuteto.tofufactory.api.recipes.RecipeManagers;
import tsuteto.tofufactory.block.BlockTofuMachine;
import tsuteto.tofufactory.core.TFItems;

public class TileEntityTofuCompactor extends TileEntityFactoryTfMachine
{
    public String getInventoryNameTranslate()
    {
        return "container.tofufactory:tfCompactor";
    }

    public BlockTofuMachine getMachineBlock()
    {
        return (BlockTofuMachine) TFItems.tofuCompactorActive;
    }

    public IMachineRecipe getMachineRecipe()
    {
        return RecipeManagers.compactorManager;
    }
}
