package tsuteto.tofufactory.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tsuteto.tofu.item.ItemTcMaterials;
import tsuteto.tofufactory.api.recipes.RecipeManagers;
import tsuteto.tofufactory.block.BlockTofuMetal;
import tsuteto.tofufactory.item.*;

public class TFRecipes
{
    public static void register()
    {
        registerCrafting();
        registerSmelting();
        registerPulverizer();
        registerCompactor();
        registerCuttingMachine();
    }

    private static void registerCrafting()
    {
        for (int i = 0; i < TFItems.materials.length; ++i)
        {
            // Crafting: ingot + tofu hammer -> plate
            addShapelessRecipe(ItemTofuPlate.plates[i].getStack(),
                    ItemTofuIngot.ingots[i].getStack(), new ItemStack(TFItems.craftingHammer, 1, OreDictionary.WILDCARD_VALUE));

            // Crafting: ingot -> gear
            addShapedRecipe(ItemTofuGear.gears[i].getStack(),
                    " x ",
                    "x x",
                    " x ",
                    'x', ItemTofuIngot.ingots[i].getStack());

            // Crafting: ingot -> block
            addShapedRecipe(BlockTofuMetal.blocks[i].getStack(),
                    "xxx",
                    "xxx",
                    "xxx",
                    'x', ItemTofuIngot.ingots[i].getStack());
        }

        addShapedRecipe(new ItemStack(TFItems.zundaSword, 1),
                "x",
                "x",
                "y",
                'x', TFItems.zundaIngot,
                'y', Items.stick);
        addShapedRecipe(new ItemStack(TFItems.tofuPulverizerIdle, 1),
                "z z",
                "yay",
                " x ",
                'x', "blockTfMachineCase",
                'y', "gearMithrilTofu",
                'z', Items.flint,
                'a', "craftingMagicCircuit");
        addShapedRecipe(new ItemStack(TFItems.tofuCompactorIdle, 1),
                "z z",
                "yay",
                " x ",
                'x', "blockTfMachineCase",
                'y', "gearMithrilTofu",
                'z', Blocks.stone,
                'a', "craftingMagicCircuit");
        addShapedRecipe(new ItemStack(TFItems.tofuCuttingMachineIdle, 1),
                "z z",
                "yay",
                " x ",
                'x', "blockTfMachineCase",
                'y', "gearMithrilTofu",
                'z', "craftingDiamondBlade",
                'a', "craftingMagicCircuit");
        addShapedRecipe(new ItemStack(Items.cake, 1),
                "xxx",
                "yzy",
                "aaa",
                'x', Items.milk_bucket,
                'y', Items.sugar,
                'z', Items.egg,
                'a', "cookingFlour");

        // Alloy
        addShapelessRecipe(ItemTFDust.tofuSteel.getStack(),
                "dustDiamondTofu", "dustIron");
        addShapelessRecipe(ItemTFDust.sol.getStack(2),
                "dustMithrilTofu", "dustGold");
        addShapelessRecipe(ItemTFDust.luna.getStack(2),
                "dustMithrilTofu", "dustSilver");

        // Circuits
        addShapelessRecipe(ItemTFMaterial.magicCircuit.getStack(),
                "ingotSol", "dustRedstone");
        addShapelessRecipe(ItemTFMaterial.magicCircuitAdv.getStack(),
                "ingotLuna", "dustTofuSteel");

        // Materials
        addShapedRecipe(ItemTFMaterial.tofuBlade.getStack(),
                " x ",
                "xyx",
                " x ",
                'x', "dustLuna",
                'y', "gearTofuSteel");
        addShapedRecipe(ItemTFMaterial.ringSun.getStack(2),
                "xxx",
                "x x",
                "xxx",
                'x', "ingotSol");
        addShapedRecipe(ItemTFMaterial.ringMoon.getStack(2),
                "xxx",
                "x x",
                "xxx",
                'x', "ingotLuna");
        addShapedRecipe(new ItemStack(TFItems.craftingHammer),
                "xx ",
                "x--",
                "xx ",
                'x', "blockTofuMetal",
                '-', Items.stick);
        addShapedRecipe(new ItemStack(TFItems.craftingHammer),
                " xx",
                "--x",
                " xx",
                'x', "blockTofuMetal",
                '-', Items.stick);

        // Consolation recipe: Mithril + 2 tofu gems -> Mithril tofu dust
        addShapelessRecipe(ItemTFDust.mithrilTofu.getStack(),
                "tofuGem", "tofuGem",
                ItemTFDust.mithril.getStack());
    }

    private static void registerSmelting()
    {
        // Diamond tofu ingot
        GameRegistry.addSmelting(new ItemStack(TFItems.tofuDust, 1, 0), new ItemStack(TFItems.tofuIngot, 1, 0), 1.5F);
        // Mithril Tofu ingot
        GameRegistry.addSmelting(TFItems.oreMithrilTofu, new ItemStack(TFItems.tofuIngot, 1, 1), 1.5F);
        // Bread from flour
        GameRegistry.addSmelting(TFItems.flour, new ItemStack(Items.bread, 1, 0), 1.5F);

        // Dust -> ingot
        for (int i = 0; i < TFItems.materials.length; ++i)
        {
            GameRegistry.addSmelting(ItemTFDust.dusts[i].getStack(), ItemTofuIngot.ingots[i].getStack(), 1.5F);
        }

        // Iron dust -> ingot
        GameRegistry.addSmelting(ItemTFDust.iron.getStack(), new ItemStack(Items.iron_ingot), 1.0F);
    }

    private static void registerPulverizer()
    {
        // Mithril Tofu Dust
        RecipeManagers.pulverizerManager.addRecipe(
                new ItemStack(TFItems.oreMithrilTofu), ItemTFDust.mithrilTofu.getStack(2));

        // Diamond Tofu Dust
        RecipeManagers.pulverizerManager.addRecipe(
                ItemTcMaterials.tofuDiamondNugget.getStack(), ItemTFDust.diamondTofu.getStack());
        // Flour
        RecipeManagers.pulverizerManager.addRecipe(
                new ItemStack(Items.wheat), new ItemStack(TFItems.flour, 2, 0));

        // Iron dust
        RecipeManagers.pulverizerManager.addRecipe(
                new ItemStack(Blocks.iron_ore), ItemTFDust.iron.getStack(2));
        RecipeManagers.pulverizerManager.addRecipe(
                new ItemStack(Items.iron_ingot), ItemTFDust.iron.getStack());

        for (int i = 0; i < TFItems.materials.length; ++i)
        {
            // Pulverizer: ingot -> dust
            RecipeManagers.pulverizerManager.addRecipe(
                    ItemTofuIngot.ingots[i].getStack(), ItemTFDust.dusts[i].getStack());
        }

        // Consolation recipe: Mithril dust
        RecipeManagers.pulverizerManager.addRecipe(
                new ItemStack(TFItems.oreMithril), ItemTFDust.mithril.getStack()
        );
    }

    private static void registerCompactor() {}

    private static void registerCuttingMachine()
    {
        // Cutting machine: block -> plates
        for (int i = 0; i < TFItems.materials.length; ++i)
        {
            RecipeManagers.cuttingMachineManager.addRecipe(
                    BlockTofuMetal.blocks[i].getStack(),
                    ItemTofuPlate.plates[i].getStack(9));
        }
    }

    private static void addShapelessRecipe(ItemStack is, Object... recipe)
    {
        GameRegistry.addRecipe(new ShapelessOreRecipe(is, recipe));
    }

    private static void addShapedRecipe(ItemStack is, Object... recipe)
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(is, recipe));
    }

}
