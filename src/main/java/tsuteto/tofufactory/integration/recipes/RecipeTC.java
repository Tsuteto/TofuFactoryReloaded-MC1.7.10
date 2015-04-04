package tsuteto.tofufactory.integration.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import tsuteto.tofu.block.TcBlocks;
import tsuteto.tofu.item.TcItems;
import tsuteto.tofufactory.integration.ITFRecipeModule;
import tsuteto.tofufactory.integration.plugins.PluginTC;
import tsuteto.tofufactory.item.ItemTFMaterial;
import tsuteto.tofufactory.item.ItemTofuIngot;

public class RecipeTC implements ITFRecipeModule
{
    public static final String tofuKinuK = "ArcaneKinuTofu";
    public static final String tofuFocusK = "tofuFocus";
    public static final String MagicCircuitK = "MagicCircuit";
    public static final String MithrilK = "Mithril";
    public static final String SolK = "Sol";
    public static final String LunaK = "Luna";

    public static ShapelessArcaneRecipe tofuKinuR;
    public static ShapelessArcaneRecipe tofuFocusR;
    public static ShapelessArcaneRecipe MagicCircuitR;
    public static CrucibleRecipe MithrilR;
    public static CrucibleRecipe SolR;
    public static CrucibleRecipe LunaR;

    public void register() throws Exception
    {
        registerArcaneCrafting();
        initResearch();
    }

    private void registerArcaneCrafting()
    {
        // ArcaneCrafting: Kinu Tofu
        tofuKinuR = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                tofuKinuK,
                new ItemStack(TcBlocks.tofuKinu, 1),
                (new AspectList()).add(Aspect.WATER, 2),
                new ItemStack(TcItems.bucketSoymilk, 1));

        // ArcaneCrafting: Tofu Focus
        tofuFocusR = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                tofuKinuK,
                new ItemStack(PluginTC.tofuFocus, 1),
                (new AspectList()).add(PluginTC.TOFU, 2).add(Aspect.WATER, 2),
                new ItemStack(TcBlocks.tofuKinu, 1));

        // ArcaneCrafting: Magic Circuit
        MagicCircuitR = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                MagicCircuitK,
                ItemTFMaterial.magicCircuit.getStack(4),
                (new AspectList()).add(PluginTC.TOFU, 8).add(Aspect.ENERGY, 2).add(Aspect.MECHANISM, 1),
                ItemTofuIngot.mithrilTofu.getStack(),
                new ItemStack(Items.redstone, 1));

        // Crucible: Mithril Tofu
        MithrilR = ThaumcraftApi.addCrucibleRecipe(
                MithrilK,
                ItemTofuIngot.mithrilTofu.getStack(2),
                "gemTofu",
                (new AspectList()).add(PluginTC.TOFU, 5).add(Aspect.WATER, 5).add(Aspect.COLD, 5));

        // Crucible: Sol
        SolR = ThaumcraftApi.addCrucibleRecipe(
                SolK,
                ItemTofuIngot.sol.getStack(2),
                "ingotMithrilTofu",
                (new AspectList()).add(PluginTC.TOFU, 8).add(Aspect.LIGHT, 3));

        // Crucible: Luna
        LunaR = ThaumcraftApi.addCrucibleRecipe(
                LunaK,
                ItemTofuIngot.luna.getStack(2),
                "ingotMithrilTofu",
                (new AspectList()).add(PluginTC.TOFU, 8).add(Aspect.WEATHER, 3));
    }

    public void initResearch()
    {
        ResearchCategories.addResearch((new ResearchItem("TofuAspect", PluginTC.RESEARCH_TOFU, null, 0, 0, 1,
                new ResourceLocation("thaumcraft", "textures/aspects/terra.png")))
                .setPages(new ResearchPage("TofuAspect", "tc.research_page.TofuAspect"),
                        new ResearchPage((new AspectList()).add(PluginTC.TOFU, 1)))
                .setAutoUnlock().setRound());

        ResearchCategories.addResearch((new ResearchItem(tofuKinuK, PluginTC.RESEARCH_TOFU,
                (new AspectList()).add(PluginTC.TOFU, 1).add(Aspect.WATER, 1).add(Aspect.GREED, 1), -2, 0, 1, new ItemStack(TcBlocks.tofuKinu, 1)))
                .setPages(new ResearchPage(tofuKinuK, "tc.research_page." + tofuKinuK),
                        new ResearchPage(tofuKinuR)).setParents("TofuAspect"));

        ResearchCategories.addResearch((new ResearchItem(tofuFocusK, PluginTC.RESEARCH_TOFU,
                (new AspectList()).add(PluginTC.TOFU, 2).add(Aspect.ENERGY, 1).add(Aspect.WATER, 2), -1, 2, 1, new ItemStack(PluginTC.tofuFocus, 1)))
                .setPages(new ResearchPage(tofuFocusK, "tc.research_page." + tofuFocusK),
                        new ResearchPage(tofuFocusR)).setParents(new String[] {"TofuAspect"}).setSpecial());

        ResearchCategories.addResearch((new ResearchItem(MithrilK, PluginTC.RESEARCH_TOFU,
                (new AspectList()).add(PluginTC.TOFU, 8).add(Aspect.COLD, 8).add(Aspect.MAGIC, 1), 2, -1, 1, ItemTofuIngot.mithrilTofu.getStack()))
                .setPages(new ResearchPage(MithrilK, "tc.research_page." + MithrilK),
                        new ResearchPage(MithrilR)).setParents("TofuAspect").setSiblings(SolK, LunaK).setRound());

        ResearchCategories.addResearch((new ResearchItem(SolK, PluginTC.RESEARCH_TOFU,
                (new AspectList()).add(PluginTC.TOFU, 16).add(Aspect.LIGHT, 3), 4, 0, 1, ItemTofuIngot.sol.getStack(4)))
                .setPages(new ResearchPage(SolK, "tc.research_page." + SolK),
                        new ResearchPage(SolR)).setParents(MithrilK));

        ResearchCategories.addResearch((new ResearchItem(LunaK, PluginTC.RESEARCH_TOFU,
                (new AspectList()).add(PluginTC.TOFU, 16).add(Aspect.WEATHER, 3), 4, -2, 1, ItemTofuIngot.luna.getStack(4)))
                .setPages(new ResearchPage(LunaK, "tc.research_page." + LunaK),
                        new ResearchPage(LunaR))
                .setParents(MithrilK));

        ResearchCategories.addResearch((new ResearchItem(MagicCircuitK, PluginTC.RESEARCH_TOFU,
                (new AspectList()).add(PluginTC.TOFU, 24).add(Aspect.ENERGY, 4).add(Aspect.MECHANISM, 4), 2, 2, 1, ItemTFMaterial.magicCircuit.getStack(4)))
                .setPages(new ResearchPage(MagicCircuitK, "tc.research_page." + MagicCircuitK),
                        new ResearchPage(MagicCircuitR)).setSpecial());
    }
}
