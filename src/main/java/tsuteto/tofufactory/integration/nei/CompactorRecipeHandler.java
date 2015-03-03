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
import tsuteto.tofufactory.gui.GuiTofuPulverizer;

import java.util.Map;

public class CompactorRecipeHandler extends MachineRecipeHandler
{
    public CompactorRecipeHandler()
    {
        this.setProgressBar(FactoryMachineGuiParts.progressCompactorBg, FactoryMachineGuiParts.progressCompactor);
    }

    public Class<? extends GuiContainer> getGuiClass()
    {
        return GuiTofuCompactor.class;
    }

    public String getRecipeName()
    {
        return StatCollector.translateToLocal("container.tofufactory:tfCompactor");
    }

    public String getRecipeId()
    {
        return "tofufactory.compactor";
    }

    public String getOverlayIdentifier()
    {
        return "compactor";
    }

    public Map<Ingredient<?>, ItemStack> getNativeRecipeList()
    {
        return RecipeManagers.compactorManager.getRecipeList();
    }

    @Override
    public Map<IRecipeInput, RecipeOutput> getIC2RecipeList()
    {
        return Recipes.compressor.getRecipes();
    }
}
