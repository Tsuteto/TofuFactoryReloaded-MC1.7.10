package tsuteto.tofufactory.integration.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import ic2.api.item.IBlockCuttingBlade;
import ic2.api.item.IC2Items;
import ic2.api.recipe.*;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tsuteto.tofu.item.TcItems;
import tsuteto.tofu.recipe.Ingredient;
import tsuteto.tofu.recipe.IngredientDic;
import tsuteto.tofu.recipe.IngredientItem;
import tsuteto.tofufactory.api.recipes.IMachineRecipe;
import tsuteto.tofufactory.api.recipes.RecipeManagers;
import tsuteto.tofufactory.core.TFItems;
import tsuteto.tofufactory.core.TofuFactory;
import tsuteto.tofufactory.integration.ITFRecipeModule;
import tsuteto.tofufactory.integration.plugins.PluginIC2;
import tsuteto.tofufactory.item.ItemTofuIngot;
import tsuteto.tofufactory.item.ItemTofuPlate;

import java.util.Map;

public class RecipeIC2 implements ITFRecipeModule
{
    public void register()
    {
        registerCrafting();
        registerMacerator();
        registerCompressor();
        registerMetalFormer();
        registerCuttingMachine();
    }

    private void registerCrafting()
    {
        // BatBox recipe
        GameRegistry.addRecipe(new ShapedOreRecipe(IC2Items.getItem("batBox"),
                "xyx",
                "xzx",
                "xxx",
                'y', TFItems.zundaIngot,
                'z', new ItemStack(PluginIC2.zundaBattery, 1, 32767),
                'x', "plankWood"));

        // Zunda Battery
        GameRegistry.addShapelessRecipe(new ItemStack(PluginIC2.zundaBattery, 1),
                new ItemStack(TFItems.zundaIngot, 1),
                new ItemStack(Items.redstone, 1));

        // Zunda Sword 2
        GameRegistry.addShapelessRecipe(new ItemStack(PluginIC2.zundaSwordBow, 1),
                new ItemStack(PluginIC2.zundaBattery, 1),
                new ItemStack(TcItems.zundaBow, 1),
                new ItemStack(TFItems.zundaSword, 1));
    }

    private void registerMacerator()
    {
        this.integrateRecipes(RecipeManagers.pulverizerManager, Recipes.macerator);
    }

    private void registerCompressor()
    {
        this.integrateRecipes(RecipeManagers.compactorManager, Recipes.compressor);
    }

    public void registerMetalFormer()
    {
        for (int i = 0; i < TFItems.materials.length; i++)
        {
            Recipes.metalformerRolling.addRecipe(
                    new RecipeInputItemStack(ItemTofuIngot.ingots[i].getStack()), null, ItemTofuPlate.plates[i].getStack());
        }
    }

    public void registerCuttingMachine()
    {
        this.integrateRecipes(RecipeManagers.cuttingMachineManager, Recipes.blockcutter);
    }


    private void integrateRecipes(IMachineRecipe factoryRecipe, IMachineRecipeManager ic2RecipeManager)
    {
        for (Map.Entry<Ingredient<?>, ItemStack> entry : factoryRecipe.getRecipeList().entrySet())
        {
            if (entry.getKey() instanceof IngredientItem)
            {
                ItemStack inputItem = ((IngredientItem)entry.getKey()).itemObj;
                if (ic2RecipeManager.getOutputFor(inputItem, false) == null) // Check duplication here
                {
                    ic2RecipeManager.addRecipe(new RecipeInputItemStack(inputItem), null, entry.getValue());
                    TofuFactory.log.debug("Registered a recipe in {}: {}", ic2RecipeManager.toString(), inputItem);
                }
            }
            if (entry.getKey() instanceof IngredientDic)
            {
                String oreName = ((IngredientDic)entry.getKey()).itemObj;
                try
                {
                    ic2RecipeManager.addRecipe(
                            new RecipeInputOreDict(oreName), null, entry.getValue());
                    TofuFactory.log.debug("Registered a recipe in {}: {}", ic2RecipeManager.toString(), oreName);
                }
                catch (Exception e)
                {
                    // Catches exception because it's hard to avoid duplication
                    TofuFactory.log.debug("Failed to register due to a duplication recipe: {}", oreName);
                }
            }
        }
    }

    public static ItemStack getMaceratorResult(ItemStack input)
    {
        return Recipes.macerator.getOutputFor(input.copy(), true) == null ? null
                : (Recipes.macerator.getOutputFor(input.copy(), true).items == null ? null
                : Recipes.macerator.getOutputFor(input.copy(), true).items.get(0));
    }

    public static ItemStack getCompressorResult(ItemStack input)
    {
        return Recipes.compressor.getOutputFor(input.copy(), true) == null ? null
                : (Recipes.compressor.getOutputFor(input.copy(), true).items == null ? null
                : Recipes.compressor.getOutputFor(input.copy(), true).items.get(0));
    }

    public static ItemStack getCuttingMachineResult(ItemStack input)
    {
        ItemStack inItem = input.copy();
        RecipeOutput re3 = Recipes.blockcutter.getOutputFor(inItem, false);
        if (re3 != null)
        {
            if (re3.metadata == null)
            {
                return null;
            }

            IBlockCuttingBlade bladeBuiltIn = (IBlockCuttingBlade) PluginIC2.diamondCuttingBlade.getItem();
            if (re3.metadata.getInteger("hardness") > bladeBuiltIn.gethardness())
            {
                return null;
            }
            return re3.items != null && re3.items.size() > 0 ? re3.items.get(0) : null;
        }
        return null;
    }
}
