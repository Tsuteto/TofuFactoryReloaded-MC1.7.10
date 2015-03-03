package tsuteto.tofufactory.tileentity;

import tsuteto.tofufactory.api.recipes.IMachineRecipe;
import tsuteto.tofufactory.api.recipes.RecipeManagers;
import tsuteto.tofufactory.block.BlockTofuMachine;
import tsuteto.tofufactory.core.TFItems;

public class TileEntityTofuPulverizer extends TileEntityFactoryTfMachine
{
    public String getInventoryNameTranslate()
    {
        return "container.tofufactory:tfPulverizer";
    }

    public BlockTofuMachine getMachineBlock()
    {
        return (BlockTofuMachine) TFItems.tofuPulverizerActive;
    }

    public IMachineRecipe getMachineRecipe()
    {
        return RecipeManagers.pulverizerManager;
    }
}
