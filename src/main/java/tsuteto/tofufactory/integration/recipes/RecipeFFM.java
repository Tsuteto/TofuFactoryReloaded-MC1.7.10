package tsuteto.tofufactory.integration.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import forestry.api.recipes.RecipeManagers;
import forestry.core.config.ForestryItem;
import ic2.api.item.IC2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import tsuteto.tofu.block.TcBlocks;
import tsuteto.tofu.fluids.TcFluids;
import tsuteto.tofu.item.ItemFoodSet;
import tsuteto.tofu.item.ItemTcMaterials;
import tsuteto.tofu.item.TcItems;
import tsuteto.tofufactory.core.TFItems;
import tsuteto.tofufactory.fluid.TFFluids;
import tsuteto.tofufactory.integration.ITFRecipeModule;
import tsuteto.tofufactory.integration.TFIntegrationManager;
import tsuteto.tofufactory.integration.plugins.PluginFFM;
import tsuteto.tofufactory.integration.plugins.PluginIC2;
import tsuteto.tofufactory.item.ItemTFCell;
import tsuteto.tofufactory.item.ItemTFFoodSet;

public class RecipeFFM implements ITFRecipeModule
{
    public void register()
    {
        registerForCrafting();
        registerInCarpenter();
        registerInFabricator();
        registerInSqueezer();
        registerInStill();

        if (TFIntegrationManager.modIC2.isAvailable())
        {
            registerForIC2();
        }
    }

    private void registerInFabricator()
    {
        // Zunda Tube
        RecipeManagers.fabricatorManager.addRecipe(
                null, FluidRegistry.getFluidStack("glass", 500), new ItemStack(PluginFFM.TFTube, 4, 0),
                new Object[]{
                        " X ",
                        "#X#",
                        "XXX",
                        '#', Items.redstone,
                        'X', TFItems.zundaIngot});
        // Tofu Gem Tube
        RecipeManagers.fabricatorManager.addRecipe(
                null, FluidRegistry.getFluidStack("glass", 500), new ItemStack(PluginFFM.TFTube, 4, 1),
                new Object[]{
                        " X ",
                        "#X#",
                        "XXX",
                        '#', Items.redstone,
                        'X', ItemTcMaterials.tofuGem.getStack()});
        // Ishi Tofu Tube
        RecipeManagers.fabricatorManager.addRecipe(
                null, FluidRegistry.getFluidStack("glass", 500), new ItemStack(PluginFFM.TFTube, 4, 2),
                new Object[]{
                        " X ",
                        "#X#",
                        "XXX",
                        '#', Items.redstone,
                        'X', TcBlocks.tofuIshi});
        // Metal Tofu Tube
        RecipeManagers.fabricatorManager.addRecipe(
                null, FluidRegistry.getFluidStack("glass", 500), new ItemStack(PluginFFM.TFTube, 4, 3),
                new Object[]{
                        " X ",
                        "#X#",
                        "XXX",
                        '#', Items.redstone,
                        'X', TcBlocks.tofuMetal});
        // Hell Tofu Tube
        RecipeManagers.fabricatorManager.addRecipe(
                null, FluidRegistry.getFluidStack("glass", 500), new ItemStack(PluginFFM.TFTube, 4, 4),
                new Object[]{
                        " X ",
                        "#X#",
                        "XXX",
                        '#', Items.redstone,
                        'X', TcBlocks.tofuHell});
    }

    private void registerForCrafting()
    {
        // Backpacks
        GameRegistry.addRecipe(new ItemStack(PluginFFM.tofuBackpackT1, 1),
                "yxy",
                "zsz",
                "yxy",
                'z', TcItems.tofuKinu,
                'x', Blocks.wool,
                'y', Items.string,
                's', Blocks.chest);

        ItemStack inputItem = ForestryItem.craftingMaterial.getItemStack();
        inputItem.setItemDamage(3);
        RecipeManagers.carpenterManager.addRecipe(200, new FluidStack(FluidRegistry.WATER, 1000), null,
                new ItemStack(PluginFFM.tofuBackpackT2),
                "wxw",
                "wyw",
                "www",
                'w', inputItem,
                'x', Items.diamond,
                'y', PluginFFM.tofuBackpackT1);

        GameRegistry.addRecipe(new ItemStack(PluginFFM.zundaBackpackT1, 1),
                "yxy",
                "zsz",
                "yxy",
                'z', TFItems.zundaIngot,
                'x', Blocks.wool,
                'y', Items.string,
                's', Blocks.chest);

        RecipeManagers.carpenterManager.addRecipe(200, new FluidStack(FluidRegistry.WATER, 1000), null,
                new ItemStack(PluginFFM.zundaBackpackT2),
                "wxw",
                "wyw",
                "www",
                'w', inputItem,
                'x', Items.diamond,
                'y', PluginFFM.zundaBackpackT1);

        // Grafter
        GameRegistry.addRecipe(new ItemStack(PluginFFM.zundaGrafter, 1),
                "  x",
                " y ",
                "y  ",
                'x', TFItems.zundaIngot,
                'y', Items.stick);
    }

    private void registerInCarpenter()
    {
        // 4x Flour
        RecipeManagers.carpenterManager.addRecipe(20, (FluidStack)null, null,
                new ItemStack(TFItems.flour, 4),
                "x",
                'x', Items.wheat);

        // 2x Udon
        RecipeManagers.carpenterManager.addRecipe(20, new FluidStack(FluidRegistry.WATER, 500), null,
                new ItemStack(TFItems.udonNoodles, 2),
                "yxy",
                'x', "cookingFlour",
                'y', "starch");

        // 2x Ramen
        RecipeManagers.carpenterManager.addRecipe(20, new FluidStack(FluidRegistry.WATER, 500), null,
                new ItemStack(TFItems.ramenNoodles, 2),
                "yxy",
                'x', "cookingFlour",
                'y', TFItems.bakingSoda);

        // 6x Tofu Somen
        RecipeManagers.carpenterManager.addRecipe(20, new FluidStack(FluidRegistry.WATER, 500), null,
                ItemTcMaterials.tofuSomen.getStack(6),
                "STx",
                'T', "tofuKinu",
                'S', "starch",
                'x', "salt");

        // 3x Iron ingot
        RecipeManagers.carpenterManager.addRecipe(20, new FluidStack(FluidRegistry.LAVA, 200), null,
                new ItemStack(Items.iron_ingot, 3),
                "x",
                'x', "oreIron");

        // 3x Gold ingot
        RecipeManagers.carpenterManager.addRecipe(20, new FluidStack(FluidRegistry.LAVA, 200), null,
                new ItemStack(Items.gold_ingot, 3),
                "x",
                'x', "oreGold");

        // 3x Copper ingot
        RecipeManagers.carpenterManager.addRecipe(20, new FluidStack(FluidRegistry.LAVA, 200), null,
                ForestryItem.ingotCopper.getItemStack(3),
                "x",
                'x', "oreCopper");

        // 3x Tin ingot
        RecipeManagers.carpenterManager.addRecipe(20, new FluidStack(FluidRegistry.LAVA, 200), null,
                ForestryItem.ingotTin.getItemStack(3),
                "x",
                'x', "oreTin");

        // Zunda ingot
        RecipeManagers.carpenterManager.addRecipe(20, new FluidStack(TFFluids.zunda, 500), null,
                new ItemStack(TFItems.zundaIngot, 1),
                "x",
                'x', Items.iron_ingot);

        // Kamaboko
        RecipeManagers.carpenterManager.addRecipe(20, new FluidStack(TFFluids.drinkingWater, 500), new ItemStack(Blocks.wooden_slab, 1),
                ItemTFFoodSet.kamaboko.getStack(6),
                " D ",
                "SET",
                "F F",
                'D', "dyeRed",
                'F', Items.fish,
                'S', "salt",
                'E', Items.egg,
                'T', "starch");

        // Chikuwa
        RecipeManagers.carpenterManager.addRecipe(20, new FluidStack(TFFluids.drinkingWater, 500), new ItemStack(Items.stick, 1),
                ItemFoodSet.chikuwa.getStack(6),
                " F ",
                "SET",
                " F ",
                'F', Items.fish,
                'S', "salt",
                'E', Items.egg,
                'T', "starch");

        // Natto hiyayakko
        RecipeManagers.carpenterManager.addRecipe(20, new FluidStack(TcFluids.SOY_SAUCE, 100), new ItemStack(Items.bowl, 1),
                new ItemStack(TcItems.nattoHiyayakko, 1),
                "x",
                "y",
                "z",
                'x', "vegetableLeek",
                'y', "natto",
                'z', "tofuKinu");

        // Miso soup
        RecipeManagers.carpenterManager.addRecipe(20, new FluidStack(TFFluids.soupStock, 50), new ItemStack(Items.bowl, 1),
                new ItemStack(TcItems.misoSoup, 1),
                "x",
                "y",
                'x', "miso",
                'y', "tofuKinu");

        // Fukumeni
        RecipeManagers.carpenterManager.addRecipe(20, new FluidStack(TFFluids.soupStock, 50), null,
                new ItemStack(TcItems.fukumeni, 1),
                "x",
                "y",
                'x', "salt",
                'y', "tofuDried");

        // Sesame tofu
        RecipeManagers.carpenterManager.addRecipe(20, new FluidStack(TFFluids.soupStock, 50), null,
                new ItemStack(TcItems.tofuSesame, 1),
                "x",
                "y",
                "z",
                'x', "salt",
                'y', "starch",
                'z', "sesame");

        // Agedashi tofu
        RecipeManagers.carpenterManager.addRecipe(20, new FluidStack(TFFluids.soupStock, 50), new ItemStack(Items.bowl, 1),
                new ItemStack(TcItems.agedashiTofu, 1),
                "x",
                'x', "tofuFriedPouch");

        // Udon
        RecipeManagers.carpenterManager.addRecipe(20, new FluidStack(TFFluids.noodleSoup, 100), new ItemStack(Items.bowl, 1),
                ItemTFFoodSet.soySauceUdon.getStack(),
                "Y",
                "N",
                'Y', "leek",
                'N', "udonNoodles");
        // Miso udon
        RecipeManagers.carpenterManager.addRecipe(30, new FluidStack(TFFluids.soupStock, 100), new ItemStack(Items.bowl, 1),
                ItemTFFoodSet.misoUdonStew.getStack(),
                "YOK",
                " N ",
                " M ",
                'Y', "leek",
                'O', Items.egg,
                'K', "foodKamaboko",
                'N', "udonNoodles",
                'M', "miso");
        // Ramen
        RecipeManagers.carpenterManager.addRecipe(20, new FluidStack(TFFluids.noodleSoup, 100), new ItemStack(Items.bowl, 1),
                ItemTFFoodSet.soySauceRamen.getStack(),
                "Y",
                "N",
                'Y', "leek",
                'N', "ramenNoodles");
    }

    public void registerForIC2()
    {
        ItemStack electronicCircuit = IC2Items.getItem("electronicCircuit");
        electronicCircuit.stackSize = 3;
        RecipeManagers.carpenterManager.addRecipe(14, new FluidStack(TFFluids.drinkingWater, 200),
                null, electronicCircuit,
                "xxx",
                "yzy",
                "xxx",
                'x', IC2Items.getItem("insulatedCopperCableItem"),
                'y', Items.redstone,
                'z', Items.iron_ingot);

        ItemStack advancedCircuit = IC2Items.getItem("advancedCircuit");
        advancedCircuit.stackSize = 2;
        RecipeManagers.carpenterManager.addRecipe(14, new FluidStack(TFFluids.drinkingWater, 200),
                null, advancedCircuit,
                "xax",
                "yzy",
                "xax",
                'x', IC2Items.getItem("insulatedCopperCableItem"),
                'y', "ingotTofuSteel",
                'z', Items.redstone,
                'a', Items.glowstone_dust);

        // Noodle Soup
        RecipeManagers.carpenterManager.addRecipe(20, new FluidStack(TFFluids.soupStock, 500), PluginIC2.ic2Cell.copy(),
                ItemTFCell.noodleSoup.getStack(),
                "x",
                'x', "bottleSoySauce");
    }

    private void registerInSqueezer()
    {
        ItemStack[] myFish = new ItemStack[] {new ItemStack(Items.cooked_fished, 1)};
        ItemStack[] myTofu = new ItemStack[] {new ItemStack(TcItems.soybeans, 1)};
        ItemStack[] myZunda = new ItemStack[] {new ItemStack(TcItems.zunda, 1)};

        // Soup stock
        RecipeManagers.squeezerManager.addRecipe(30, myFish,
                new FluidStack(TFFluids.soupStock, 1000), new ItemStack(Items.bone, 1), 50);
        // Soymilk
        RecipeManagers.squeezerManager.addRecipe(20, myTofu,
                new FluidStack(TcFluids.SOYMILK, 1000), new ItemStack(TcItems.okara, 2), 80);
        // Zunda water
        RecipeManagers.squeezerManager.addRecipe(20, myZunda,
                new FluidStack(TFFluids.zunda, 500), null, 0);
    }

    private void registerInStill()
    {
        // Drinking water
        RecipeManagers.stillManager.addRecipe(10, new FluidStack(FluidRegistry.WATER, 10),
                new FluidStack(TFFluids.drinkingWater, 10));
    }
}
