package tsuteto.tofufactory.integration.craftguide;

import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeOutput;
import net.minecraft.item.ItemStack;
import tsuteto.tofu.recipe.Ingredient;
import tsuteto.tofufactory.core.TofuFactory;
import tsuteto.tofufactory.integration.TFIntegrationManager;
import uristqwerty.CraftGuide.api.*;

import java.util.Map;

public abstract class RecipeCGBase extends CraftGuideAPIObject implements RecipeProvider
{
    protected final Slot[] slots = new Slot[3];

    public RecipeCGBase()
    {
        this.slots[0] = new ItemSlot(6, 20, 16, 16, true);
        this.slots[1] = new ItemSlot(55, 20, 16, 16, true).setSlotType(SlotType.OUTPUT_SLOT);
        this.slots[2] = new ItemSlot(30, 35, 20, 20).setSlotType(SlotType.MACHINE_SLOT);
    }

    @Override
    public void generateRecipes(RecipeGenerator generator)
    {
        RecipeTemplate template;
        ItemStack machine = this.getMachineBlock();
        int posY = 58 * this.getTextureRow();
        template = generator.createRecipeTemplate(this.slots, machine,
                TofuFactory.resourceDomain + "textures/gui/craftGuideRecipe.png", 0, posY, 79, posY);

        if (TFIntegrationManager.modIC2.isAvailable())
        {
            for (Map.Entry<IRecipeInput, RecipeOutput> recipe : this.getIC2RecipeList().entrySet())
            {
                Object[] items = new Object[3];
                items[0] = recipe.getKey().getInputs();
                items[1] = recipe.getValue().items.get(0);
                items[2] = machine;
                generator.addRecipe(template, items);
            }
        }
        else
        {
            for (Map.Entry<Ingredient<?>, ItemStack> recipe : this.getLocalRecipeList().entrySet())
            {
                Object[] items = new Object[3];
                items[0] = recipe.getKey().getApplicableItems();
                items[1] = recipe.getValue();
                items[2] = machine;
                generator.addRecipe(template, items);
            }
        }
    }

    protected abstract int getTextureRow();

    protected abstract ItemStack getMachineBlock();

    protected abstract Map<Ingredient<?>, ItemStack> getLocalRecipeList();
    protected abstract Map<IRecipeInput, RecipeOutput> getIC2RecipeList();
}
