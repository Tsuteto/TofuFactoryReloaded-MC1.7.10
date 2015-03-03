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
import tsuteto.tofufactory.gui.GuiTofuPulverizer;

import java.util.Map;

public class PulverizerRecipeHandler extends MachineRecipeHandler
{
    public PulverizerRecipeHandler()
    {
        this.setProgressBar(FactoryMachineGuiParts.progressPulverizerBg, FactoryMachineGuiParts.progressPulverizer);
    }

    public Class<? extends GuiContainer> getGuiClass()
    {
        return GuiTofuPulverizer.class;
    }

    public String getRecipeName()
    {
        return StatCollector.translateToLocal("container.tofufactory:tfPulverizer");
    }

    public String getRecipeId()
    {
        return "tofufactory.pulverizer";
    }

    public String getOverlayIdentifier()
    {
        return "pulverizer";
    }

    public Map<Ingredient<?>, ItemStack> getNativeRecipeList()
    {
        return RecipeManagers.pulverizerManager.getRecipeList();
    }

    @Override
    public Map<IRecipeInput, RecipeOutput> getIC2RecipeList()
    {
        return Recipes.macerator.getRecipes();
    }
}
