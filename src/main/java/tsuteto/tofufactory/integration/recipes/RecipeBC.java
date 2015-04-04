package tsuteto.tofufactory.integration.recipes;

import buildcraft.BuildCraftTransport;
import buildcraft.core.recipes.RefineryRecipeManager;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tsuteto.tofu.fluids.TcFluids;
import tsuteto.tofufactory.core.TFItems;
import tsuteto.tofufactory.core.TofuFactory;
import tsuteto.tofufactory.fluid.TFFluids;
import tsuteto.tofufactory.integration.ITFRecipeModule;
import tsuteto.tofufactory.integration.plugins.PluginBC;

public class RecipeBC implements ITFRecipeModule
{
    public void register() throws Exception
    {
        // Refinery Recipes
        RefineryRecipeManager.INSTANCE.addRecipe(TofuFactory.resourceDomain + "noodleSoup", new FluidStack(TFFluids.soupStock, 500), new FluidStack(TcFluids.SOY_SAUCE, 1000), new FluidStack(TFFluids.noodleSoup, 1500), 20, 30);

        // Crafting Recipes
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PluginBC.pipeItemsTofuIshi, 8),
                "zxz",
                'z', "tofuIshi",
                'x', Blocks.glass));

        GameRegistry.addShapelessRecipe(new ItemStack(PluginBC.pipeFluidsTofuIshi, 1),
                new ItemStack(PluginBC.pipeItemsTofuIshi, 1),
                new ItemStack(BuildCraftTransport.pipeWaterproof, 1));

        GameRegistry.addShapelessRecipe(new ItemStack(PluginBC.pipePowerTofuIshi, 1),
                new ItemStack(PluginBC.pipeItemsTofuIshi, 1),
                new ItemStack(Items.redstone, 1));

        GameRegistry.addRecipe(new ItemStack(PluginBC.pipeItemsZunda, 8),
                "zxz",
                'z', TFItems.zundaIngot,
                'x', Blocks.glass);

        GameRegistry.addShapelessRecipe(new ItemStack(PluginBC.pipeFluidsZunda, 1),
                new ItemStack(PluginBC.pipeItemsZunda, 1),
                new ItemStack(BuildCraftTransport.pipeWaterproof, 1));

        GameRegistry.addShapelessRecipe(new ItemStack(PluginBC.pipePowerZunda, 1),
                new ItemStack(PluginBC.pipeItemsZunda, 1),
                new ItemStack(Items.redstone, 1));


    }
}
