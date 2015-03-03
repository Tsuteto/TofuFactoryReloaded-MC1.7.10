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
import tsuteto.tofufactory.integration.plugins.PluginTC;
import tsuteto.tofufactory.integration.ITFRecipeModule;
import tsuteto.tofu.block.TcBlocks;
import tsuteto.tofu.item.TcItems;
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

    public void register()
    {
        registerArcaneCrafting();
        initResearch();
    }

    private void registerArcaneCrafting()
    {
        // Tofu study
        tofuKinuR = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                tofuKinuK,
                new ItemStack(TcBlocks.tofuKinu, 1),
                (new AspectList()).add(Aspect.WATER, 2),
                new ItemStack(TcItems.bucketSoymilk, 1));

        // Tofu Focus
        tofuFocusR = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                tofuKinuK,
                new ItemStack(PluginTC.tofuFocus, 1),
                (new AspectList()).add(Aspect.WATER, 2),
                new ItemStack(TcBlocks.tofuKinu, 1));

        // Magic Circuit
        MagicCircuitR = ThaumcraftApi.addShapelessArcaneCraftingRecipe(
                MagicCircuitK,
                ItemTFMaterial.magicCircuit.getStack(4),
                (new AspectList()).add(Aspect.EARTH, 3),
                ItemTofuIngot.mithrilTofu.getStack(),
                new ItemStack(Items.redstone, 1));

        // Mithril
        MithrilR = ThaumcraftApi.addCrucibleRecipe(
                MithrilK,
                ItemTofuIngot.mithrilTofu.getStack(2), "ingotIron",
                (new AspectList()).add(PluginTC.TOFU, 2));

        // Sol
        SolR = ThaumcraftApi.addCrucibleRecipe(
                SolK,
                ItemTofuIngot.sol.getStack(2), "ingotMithrilTofu",
                (new AspectList()).add(PluginTC.TOFU, 1).add(Aspect.LIGHT, 1));

        // Luna
        LunaR = ThaumcraftApi.addCrucibleRecipe(
                LunaK,
                ItemTofuIngot.luna.getStack(2), "ingotMithrilTofu",
                (new AspectList()).add(PluginTC.TOFU, 1).add(Aspect.WEATHER, 1));
    }

    public void initResearch()
    {
        ResearchCategories.addResearch((new ResearchItem("TofuAspect", PluginTC.RESEARCH_TOFU, null, 0, 0, 1,
                new ResourceLocation("thaumcraft", "textures/aspects/terra.png")))
                .setPages(new ResearchPage("TofuAspect", "tc.research_page.TofuAspect"),
                        new ResearchPage((new AspectList()).add(PluginTC.TOFU, 1)))
                .setAutoUnlock().setRound());

        ResearchCategories.addResearch((new ResearchItem(tofuKinuK, PluginTC.RESEARCH_TOFU,
                (new AspectList()).add(PluginTC.TOFU, 1), -2, 0, 1, new ItemStack(TcBlocks.tofuKinu, 1)))
                .setPages(new ResearchPage(tofuKinuK, "tc.research_page." + tofuKinuK),
                        new ResearchPage(tofuKinuR)).setParents("TofuAspect"));

        ResearchCategories.addResearch((new ResearchItem(tofuFocusK, PluginTC.RESEARCH_TOFU,
                (new AspectList()).add(PluginTC.TOFU, 1), -1, 2, 1, new ItemStack(PluginTC.tofuFocus, 1)))
                .setPages(new ResearchPage(tofuFocusK, "tc.research_page." + tofuFocusK),
                        new ResearchPage(tofuFocusR)).setParents(new String[] {"TofuAspect"}).setSpecial());

        ResearchCategories.addResearch((new ResearchItem(MithrilK, PluginTC.RESEARCH_TOFU,
                (new AspectList()).add(PluginTC.TOFU, 3), 2, -1, 1, ItemTofuIngot.mithrilTofu.getStack()))
                .setPages(new ResearchPage(MithrilK, "tc.research_page." + MithrilK),
                        new ResearchPage(MithrilR)).setParents("TofuAspect").setSiblings(SolK, LunaK).setRound());

        ResearchCategories.addResearch((new ResearchItem(SolK, PluginTC.RESEARCH_TOFU,
                (new AspectList()).add(PluginTC.TOFU, 6), 4, 0, 1, ItemTofuIngot.sol.getStack(4)))
                .setPages(new ResearchPage(SolK, "tc.research_page." + SolK),
                        new ResearchPage(SolR)).setParents(MithrilK));

        ResearchCategories.addResearch((new ResearchItem(LunaK, PluginTC.RESEARCH_TOFU,
                (new AspectList()).add(PluginTC.TOFU, 6), 4, -2, 1, ItemTofuIngot.luna.getStack(4)))
                .setPages(new ResearchPage(LunaK, "tc.research_page." + LunaK),
                        new ResearchPage(LunaR))
                .setParents(MithrilK));

        ResearchCategories.addResearch((new ResearchItem(MagicCircuitK, PluginTC.RESEARCH_TOFU,
                (new AspectList()).add(PluginTC.TOFU, 10), 2, 2, 1, ItemTFMaterial.magicCircuit.getStack(4)))
                .setPages(new ResearchPage(MagicCircuitK, "tc.research_page." + MagicCircuitK),
                        new ResearchPage(MagicCircuitR)).setSpecial());
    }
}
