package tsuteto.tofufactory.integration.nei;

import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.google.common.collect.Lists;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeOutput;
import ic2.core.util.StackUtil;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import tsuteto.tofu.TcTextures;
import tsuteto.tofu.gui.guiparts.*;
import tsuteto.tofu.recipe.Ingredient;
import tsuteto.tofufactory.gui.GuiFactoryMachine;
import tsuteto.tofufactory.integration.TFIntegrationManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class MachineRecipeHandler extends TemplateRecipeHandler
{
    public static final int GUI_OFFSET_X = 20;
    public static final int GUI_OFFSET_Y = 2;

//    private static Field fldGuiLeft = ReflectionHelper.findField(GuiContainer.class, "field_146294_l", "guiLeft");
//    private static Field fldGuiTop = ReflectionHelper.findField(GuiContainer.class, "field_146295_m", "guiTop");

    protected TfMachineDrawingHandler drawingHandler = new TfMachineDrawingHandler();
    protected int ticks;

    protected GuiPartGaugeBase progressBar;
    protected GuiPartGaugeBase gaugeTfCharged = new GuiPartGaugeV(32, 43, TfMachineGuiParts.gaugeTfChargedBg, TfMachineGuiParts.gaugeTfCharged)
            .setInfoTip(HoverTextPosition.MC_STANDARD);

    public MachineRecipeHandler()
    {
        this.drawingHandler.setGuiPosition(GUI_OFFSET_X, GUI_OFFSET_Y);
    }

    @Override
    public abstract String getRecipeName();

    public abstract Map<Ingredient<?>, ItemStack> getNativeRecipeList();

    public abstract Map<IRecipeInput, RecipeOutput> getIC2RecipeList();

    public abstract String getRecipeId();

    protected int getInputPosX()
    {
        return 51;
    }

    protected int getInputPosY()
    {
        return 24;
    }

    protected int getOutputPosX()
    {
        return 111;
    }

    protected int getOutputPosY()
    {
        return 24;
    }

    protected void setProgressBar(TfMachineGuiParts bg, TfMachineGuiParts fg)
    {
        this.progressBar = new GuiPartGaugeH(54, 21, bg, fg);
    }

    @Override
    public String getGuiTexture()
    {
        return TcTextures.tfMachineGui.toString();
    }

    public void drawBackground(int i)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawingHandler.bindStandardTexture();
        drawingHandler.drawBasePanel(165 - 40, 63);

        drawingHandler.drawGuiPart(29, 20, TfMachineGuiParts.itemSlotL1);
        drawingHandler.drawGuiPart(87, 18, TfMachineGuiParts.itemSlotL2);
    }

    public void drawExtras(int i)
    {
        float f;
        f = this.ticks <= 20 ? (float) this.ticks / 20.0F : 1.0F;
        //this.drawProgressBar(51, 25, 176, 0, 14, 14, f, 3);
        this.gaugeTfCharged.setPercentage(f).draw(drawingHandler);

        drawingHandler.bindTexture(GuiFactoryMachine.factoryMachineTexture);
        f = this.ticks >= 20 ? (float) ((this.ticks - 20) % 20) / 20.0F : 0.0F;
        //this.drawProgressBar(74, 23, 176, 14, 25, 16, f, 0);
        this.progressBar.setPercentage(f).draw(drawingHandler);
    }

    public void onUpdate()
    {
        super.onUpdate();
        ++this.ticks;
    }

    public void loadTransferRects()
    {
        // Recipes
        this.transferRects.add(new RecipeTransferRect(new Rectangle(74, 23, 25, 16), this.getRecipeId()));
    }

    public void loadCraftingRecipes(String outputId, Object... results)
    {
        if (outputId.equals(this.getRecipeId()))
        {
            if (TFIntegrationManager.modIC2.isReady())
            {
                for (Map.Entry<IRecipeInput, RecipeOutput> entry : this.getIC2RecipeList().entrySet())
                {
                    this.arecipes.add(new CachedIORecipe(entry.getKey(), entry.getValue()));
                }
            }
            else
            {
                for (Map.Entry<Ingredient<?>, ItemStack> entry : this.getNativeRecipeList().entrySet())
                {
                    this.arecipes.add(new CachedIORecipe(entry.getKey(), entry.getValue()));
                }
            }
        }
        else
        {
            super.loadCraftingRecipes(outputId, results);
        }

    }

    public void loadCraftingRecipes(ItemStack result)
    {

        if (TFIntegrationManager.modIC2.isReady())
        {
            for (Map.Entry<IRecipeInput, RecipeOutput> entry : this.getIC2RecipeList().entrySet())
            {
                ItemStack output = entry.getValue().items.get(0);
                if (NEIServerUtils.areStacksSameTypeCrafting(output, result))
                {
                    this.arecipes.add(new CachedIORecipe(entry.getKey(), entry.getValue()));
                    break;
                }
            }
        }
        else
        {
            for (Map.Entry<Ingredient<?>, ItemStack> entry : this.getNativeRecipeList().entrySet())
            {
                ItemStack output = entry.getValue();
                if (NEIServerUtils.areStacksSameTypeCrafting(output, result))
                {
                    this.arecipes.add(new CachedIORecipe(entry.getKey(), entry.getValue()));
                    break;
                }
            }
        }

    }

    public void loadUsageRecipes(ItemStack ingredient)
    {

        if (TFIntegrationManager.modIC2.isReady())
        {
            for (Map.Entry<IRecipeInput, RecipeOutput> entry : this.getIC2RecipeList().entrySet())
            {
                if (entry.getKey().matches(ingredient))
                {
                    this.arecipes.add(new CachedIORecipe(entry.getKey(), entry.getValue()));
                }
            }
        }
        else
        {
            for (Map.Entry<Ingredient<?>, ItemStack> entry : this.getNativeRecipeList().entrySet())
            {
                if (entry.getKey().matches(ingredient))
                {
                    this.arecipes.add(new CachedIORecipe(entry.getKey(), entry.getValue()));
                }
            }
        }
    }

//    public static class MyRecipeTransferRect extends RecipeTransferRect
//    {
//        protected Rectangle rect;
//
//        public MyRecipeTransferRect(Rectangle rectangle, String outputId, Object... results)
//        {
//            super(rectangle, outputId, results);
//            this.rect = rectangle;
//        }
//
//        public boolean contains(Point point)
//        {
//            return this.rect.contains(point);
//        }
//    }


    public class CachedIORecipe extends CachedRecipe
    {
        private final List<PositionedStack> ingredients = Lists.newArrayList();
        private final PositionedStack output;
        private final List<PositionedStack> otherStacks = Lists.newArrayList();

        public java.util.List<PositionedStack> getIngredients()
        {
            return this.getCycledIngredients(MachineRecipeHandler.this.cycleticks / 20, this.ingredients);
        }

        public PositionedStack getResult()
        {
            return this.output;
        }

        public java.util.List<PositionedStack> getOtherStacks()
        {
            return this.otherStacks;
        }

        public CachedIORecipe(ItemStack input, ItemStack output1)
        {
            this.ingredients.add(new PositionedStack(input,
                    MachineRecipeHandler.this.getInputPosX(), MachineRecipeHandler.this.getInputPosY()));
            this.output = new PositionedStack(output1,
                    MachineRecipeHandler.this.getOutputPosX(), MachineRecipeHandler.this.getOutputPosY());
        }

        public CachedIORecipe(Ingredient<?> input, ItemStack output1)
        {
            this.ingredients.add(new PositionedStack(input.getApplicableItems(),
                    MachineRecipeHandler.this.getInputPosX(), MachineRecipeHandler.this.getInputPosY()));
            this.output = new PositionedStack(output1,
                    MachineRecipeHandler.this.getOutputPosX(), MachineRecipeHandler.this.getOutputPosY());
        }

        public CachedIORecipe(IRecipeInput input, RecipeOutput output1)
        {
            ArrayList<ItemStack> items = Lists.newArrayList();

            for (ItemStack item : input.getInputs())
            {
                items.add(StackUtil.copyWithSize(item, input.getAmount()));
            }

            this.ingredients.add(new PositionedStack(items,
                    MachineRecipeHandler.this.getInputPosX(), MachineRecipeHandler.this.getInputPosY()));
            this.output = new PositionedStack(output1.items.get(0),
                    MachineRecipeHandler.this.getOutputPosX(), MachineRecipeHandler.this.getOutputPosY());
        }
    }

}
