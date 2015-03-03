package tsuteto.tofufactory.integration.nei;

import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeOutput;
import ic2.api.recipe.Recipes;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import tsuteto.tofu.recipe.Ingredient;
import tsuteto.tofufactory.api.recipes.RecipeManagers;
import tsuteto.tofufactory.gui.FactoryMachineGuiParts;
import tsuteto.tofufactory.gui.GuiTofuCompactor;
import tsuteto.tofufactory.gui.GuiTofuCuttingMachine;
import tsuteto.tofufactory.integration.TFIntegrationManager;

import java.util.Map;

public class CuttingMachineRecipeHandler extends MachineRecipeHandler
{
    public CuttingMachineRecipeHandler()
    {
        this.setProgressBar(FactoryMachineGuiParts.progressCuttingMachineBg, FactoryMachineGuiParts.progressCuttingMachine);
    }

    public Class<? extends GuiContainer> getGuiClass()
    {
        return GuiTofuCuttingMachine.class;
    }

    public String getRecipeName()
    {
        return StatCollector.translateToLocal("container.tofufactory:tfCuttingMachine");
    }

    public String getRecipeId()
    {
        return "tofufactory.cuttingMachine";
    }

    public String getOverlayIdentifier()
    {
        return "cuttingMachine";
    }

    public Map<Ingredient<?>, ItemStack> getNativeRecipeList()
    {
        return RecipeManagers.cuttingMachineManager.getRecipeList();
    }

    @Override
    public Map<IRecipeInput, RecipeOutput> getIC2RecipeList()
    {
        return Recipes.blockcutter.getRecipes();
    }
}
