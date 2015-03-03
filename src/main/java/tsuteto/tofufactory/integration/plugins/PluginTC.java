package tsuteto.tofufactory.integration.plugins;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import tsuteto.tofu.item.*;
import tsuteto.tofufactory.core.TFItems;
import tsuteto.tofufactory.core.TofuFactory;
import tsuteto.tofufactory.integration.ITFPlugin;
import tsuteto.tofufactory.item.ItemTFDust;
import tsuteto.tofufactory.item.ItemTFFocus;
import tsuteto.tofufactory.item.ItemTFFoodSet;
import tsuteto.tofufactory.item.ItemTofuIngot;
import tsuteto.tofufactory.registry.ItemRegister;
import tsuteto.tofu.block.TcBlocks;

public class PluginTC implements ITFPlugin
{
    public static Aspect TOFU;
    public static String RESEARCH_TOFU = "Tofu";
    public static Item tofuFocus;

    public void init()
    {
        initAspect();
        registerAspectEntity();
        registerAspectBlock();
        registerAspectItem();

        ResearchCategories.registerCategory(RESEARCH_TOFU,
                new ResourceLocation("tofufactory", "textures/items/smokeTofu.png"),
                new ResourceLocation("tofufactory", "textures/gui/guiTofuKinu.png"));

        tofuFocus = ItemRegister.of("tofuFocus", new ItemTFFocus()).register()
                .setCreativeTab(TofuFactory.tabsTofuFactory);
    }

    private void initAspect()
    {
        TOFU = new Aspect("tofu", 16777200,
                new Aspect[] {Aspect.WATER, Aspect.CROP},
                new ResourceLocation("thaumcraft", "textures/aspects/terra.png"), 1);
    }

    private void registerAspectEntity()
    {
        $("TofuSlime")
                .add(TOFU, 5).add(Aspect.SLIME, 2).add(Aspect.WATER, 2)
                .register();
        $("Tofunian")
                .add(TOFU, 15).add(Aspect.WATER, 2)
                .register();
        $("TofuCreeper")
                .add(TOFU, 15).add(Aspect.WATER, 2)
                .register();
    }

    private void registerAspectBlock()
    {
        // Tofu
        $(TcBlocks.tofuTerrain)
                .add(TOFU, 3).add(Aspect.WATER, 3)
                .register();
        $(TcBlocks.tofuFarmland)
                .add(TOFU, 6).add(Aspect.WATER, 3).add(Aspect.EARTH, 1).add(Aspect.HARVEST, 2)
                .register();
        $(TcBlocks.tofuKinu)
                .add(TOFU, 6).add(Aspect.WATER, 6)
                .register();
        $(TcBlocks.tofuMomen)
                .add(TOFU, 4).add(Aspect.WATER, 4)
                .register();
        $(TcBlocks.tofuIshi)
                .add(TOFU, 4).add(Aspect.WATER, 2).add(Aspect.EARTH, 4)
                .register();
        $(TcBlocks.tofuMetal)
                .add(TOFU, 6).add(Aspect.METAL, 6).add(Aspect.EARTH, 2)
                .register();
        $(TcBlocks.tofuGrilled)
                .add(TOFU, 4).add(Aspect.WATER, 2).add(Aspect.FIRE, 6)
                .register();
        $(TcBlocks.tofuDried)
                .add(TOFU, 4).add(Aspect.EARTH, 2).add(Aspect.AIR, 6)
                .register();
        $(TcBlocks.tofuFriedPouch)
                .add(TOFU, 4).add(Aspect.WATER, 4).add(Aspect.FIRE, 4)
                .register();
        $(TcBlocks.tofuFried)
                .add(TOFU, 4).add(Aspect.WATER, 2).add(Aspect.FIRE, 2)
                .register();
        $(TcBlocks.tofuEgg)
                .add(TOFU, 2).add(Aspect.WATER, 3).add(Aspect.LIFE, 2)
                .register();
        $(TcBlocks.tofuAnnin)
                .add(TOFU, 2).add(Aspect.WATER, 4).add(Aspect.HUNGER, 3)
                .register();
        $(TcBlocks.tofuSesame)
                .add(TOFU, 2).add(Aspect.WATER, 4).add(Aspect.MIND, 3)
                .register();
        $(TcBlocks.tofuZunda)
                .add(TOFU, 2).add(Aspect.WATER, 6).add(Aspect.HEAL, 4)
                .register();
        $(TcBlocks.tofuStrawberry)
                .add(TOFU, 3).add(Aspect.WATER, 8).add(Aspect.HUNGER, 5)
                .register();
        $(TcBlocks.tofuHell)
                .add(TOFU, 12).add(Aspect.FIRE, 10).add(Aspect.DEATH, 5)
                .register();
        $(TcBlocks.tofuDiamond)
                .add(TOFU, 50).add(Aspect.CRYSTAL, 24)
                .register();
        $(TcBlocks.tofuMiso)
                .add(TOFU, 6).add(Aspect.HUNGER, 8).add(Aspect.ENTROPY, 2)
                .register();
        $(TcBlocks.tofuGlow)
                .add(TOFU, 6).add(Aspect.LIGHT, 8).add(Aspect.ORDER, 2)
                .register();

        // Materials
        $(TcBlocks.advTofuGem)
                .add(TOFU, 10).add(Aspect.CRYSTAL, 3).add(Aspect.AURA, 3)
                .register();
        $(TcBlocks.yuba)
                .add(TOFU, 2).add(Aspect.WATER, 3)
                .register();
        $(TcBlocks.chikuwaPlatformPlain)
                .add(Aspect.HUNGER, 4).add(Aspect.FLIGHT, 6)
                .register();
        $(TcBlocks.chikuwaPlatformTofu)
                .add(TOFU, 3).add(Aspect.HUNGER, 4).add(Aspect.FLIGHT, 6)
                .register();

        // Plant
        $(TcBlocks.tcLeaves).withMetadata(2, 10) // Tofu Leaves
                .add(TOFU, 6).add(Aspect.PLANT, 1)
                .register();
        $(TcBlocks.tcLeaves).withMetadata(0, 1, 8, 9) // Apricot Leaves
                .add(Aspect.PLANT, 1)
                .register();
        $(TcBlocks.tcLog).withMetadata(0) // Apricot Log
                .add(Aspect.TREE, 4)
                .register();
        $(TcBlocks.soybean)
                .add(TOFU, 1).add(Aspect.CROP, 3)
                .register();
        $(TcBlocks.soybeanHell)
                .add(TOFU, 3).add(Aspect.CROP, 3).add(Aspect.FIRE, 3).add(Aspect.SOUL, 6).add(Aspect.TRAVEL, 3)
                .register();
        $(TcBlocks.leek)
                .add(Aspect.PLANT, 3).add(Aspect.CROP, 3)
                .register();
        $(TcBlocks.sprouts)
                .add(Aspect.CROP, 2).add(Aspect.WATER, 2).add(Aspect.HUNGER, 2).add(Aspect.DARKNESS, 2)
                .register();
        $(TcBlocks.tcSapling).withMetadata(0)
                .add(Aspect.TREE, 1).add(Aspect.PLANT, 2)
                .register();
        $(TcBlocks.tcSapling).withMetadata(1)
                .add(TOFU, 2).add(Aspect.TREE, 1).add(Aspect.PLANT, 2)
                .register();
        $(TcBlocks.sesame)
                .add(Aspect.CROP, 5)
                .register();

        // Crafting
        $(TcBlocks.saltFurnaceIdle)
                .add(Aspect.FIRE, 2).add(Aspect.WATER, 2)
                .register();
        $(TcBlocks.nattoBed)
                .add(Aspect.AIR, 5).add(Aspect.SLIME, 2).add(Aspect.WEATHER, 1)
                .register();
        $(TcBlocks.natto)
                .add(Aspect.SLIME, 6).add(Aspect.TRAP, 3).add(Aspect.HUNGER, 2)
                .register();
        $(TcBlocks.salt)
                .add(Aspect.ORDER, 2).add(Aspect.EARTH, 2).add(Aspect.HUNGER, 1)
                .register();
        $(TcBlocks.barrelMiso)
                .add(Aspect.EARTH, 2).add(Aspect.SENSES, 2).add(Aspect.TREE, 4)
                .register();
        $(TcBlocks.barrelMisoTofu)
                .add(TOFU, 6).add(Aspect.EARTH, 2).add(Aspect.SENSES, 3).add(Aspect.TREE, 4)
                .register();
        $(TcBlocks.barrelAdvTofuGem)
                .add(TOFU, 9).add(Aspect.EARTH, 2).add(Aspect.SENSES, 4).add(Aspect.TREE, 4)
                .register();
        $(TcBlocks.barrelGlowtofu)
                .add(TOFU, 6).add(Aspect.EARTH, 2).add(Aspect.SENSES, 3).add(Aspect.LIGHT, 1).add(Aspect.TREE, 4)
                .register();

        // Ores
        $(TcBlocks.oreTofu)
                .add(TOFU, 2).add(Aspect.EARTH, 1)
                .register();
        $(TcBlocks.oreTofuDiamond)
                .add(TOFU, 6).add(Aspect.EARTH, 1).add(Aspect.GREED, 3).add(Aspect.CRYSTAL, 3)
                .register();

        // TF Machine
        $(TcBlocks.tfAntennaMedium)
                .add(TOFU, 5).add(Aspect.AIR, 10).add(Aspect.ENERGY, 5)
                .register();
        $(TcBlocks.tfAntennaUltra)
                .add(TOFU, 5).add(Aspect.AIR, 15).add(Aspect.ENERGY, 8)
                .register();
        $(TcBlocks.tfAntennaSuper)
                .add(TOFU, 5).add(Aspect.AIR, 20).add(Aspect.ENERGY, 10)
                .register();

        $(TcBlocks.tfMachineCase)
                .add(TOFU, 8).add(Aspect.MECHANISM, 1)
                .register();

        $(TcBlocks.tfCollector)
                .add(TOFU, 15).add(Aspect.AURA, 3).add(Aspect.AIR, 5).add(Aspect.GREED, 3)
                .register();

        $(TcBlocks.tfCondenserIdle)
                .add(TOFU, 15).add(Aspect.CRAFT, 3).add(Aspect.MECHANISM, 5).add(Aspect.WATER, 3)
                .register();

        $(TcBlocks.tfOvenIdle)
                .add(TOFU, 15).add(Aspect.FIRE, 4).add(Aspect.MECHANISM, 5)
                .register();

        $(TcBlocks.tfReformerIdle)
                .add(TOFU, 15).add(Aspect.CRAFT, 2).add(Aspect.MECHANISM, 3)
                .register();

        $(TcBlocks.tfSaturatorIdle)
                .add(TOFU, 15).add(Aspect.AURA, 4).add(Aspect.MECHANISM, 2).add(Aspect.GREED, 2)
                .register();

        $(TcBlocks.tfStorageIdle)
                .add(TOFU, 15).add(Aspect.MECHANISM, 2).add(Aspect.ENTROPY, 2)
                .register();

        $(TFItems.tofuCompactorIdle)
                .add(TOFU, 15).add(Aspect.CRAFT, 2).add(Aspect.MECHANISM, 4).add(Aspect.ORDER, 2)
                .register();

        $(TFItems.tofuCuttingMachineIdle)
                .add(TOFU, 15).add(Aspect.CRAFT, 2).add(Aspect.MECHANISM, 4).add(Aspect.ENTROPY, 1)
                .register();

        $(TFItems.tofuPulverizerIdle)
                .add(TOFU, 15).add(Aspect.CRAFT, 2).add(Aspect.MECHANISM, 4).add(Aspect.ENTROPY, 4)
                .register();
    }

    private void registerAspectItem()
    {
        // === TofuFactory ===
        $(TFItems.oreMithrilTofu)
                .add(TOFU, 2).add(Aspect.MAGIC, 2).add(Aspect.METAL, 1).add(Aspect.EARTH, 1)
                .register();

        $(TFItems.tofuIngot).withMetadata(ItemTofuIngot.tofuSteel.id)
                .add(TOFU, 3).add(Aspect.METAL, 4)
                .register();
        $(TFItems.tofuIngot).withMetadata(ItemTofuIngot.mithrilTofu.id)
                .add(TOFU, 3).add(Aspect.MAGIC, 3).add(Aspect.METAL, 1)
                .register();
        $(TFItems.tofuIngot).withMetadata(ItemTofuIngot.sol.id)
                .add(TOFU, 3).add(Aspect.MAGIC, 3).add(Aspect.METAL, 1).add(Aspect.LIGHT, 2)
                .register();
        $(TFItems.tofuIngot).withMetadata(ItemTofuIngot.luna.id)
                .add(TOFU, 3).add(Aspect.MAGIC, 3).add(Aspect.METAL, 1).add(Aspect.WEATHER, 2)
                .register();

        $(TFItems.tofuDust).withMetadata(ItemTFDust.tofuSteel.id)
                .add(TOFU, 2).add(Aspect.METAL, 3).add(Aspect.ENTROPY, 1)
                .register();
        $(TFItems.tofuDust).withMetadata(ItemTFDust.mithrilTofu.id)
                .add(TOFU, 2).add(Aspect.MAGIC, 2).add(Aspect.METAL, 1).add(Aspect.ENTROPY, 1)
                .register();
        $(TFItems.tofuDust).withMetadata(ItemTFDust.sol.id)
                .add(TOFU, 2).add(Aspect.MAGIC, 2).add(Aspect.METAL, 1).add(Aspect.ENTROPY, 1).add(Aspect.LIGHT, 2)
                .register();
        $(TFItems.tofuDust).withMetadata(ItemTFDust.luna.id)
                .add(TOFU, 2).add(Aspect.MAGIC, 2).add(Aspect.METAL, 1).add(Aspect.ENTROPY, 1).add(Aspect.WEATHER, 2)
                .register();
        $(TFItems.tofuDust).withMetadata(ItemTFDust.diamondTofu.id)
                .add(TOFU, 2).add(Aspect.CRYSTAL, 1).add(Aspect.ENTROPY, 1)
                .register();

        $(TFItems.zundaIngot)
                .add(Aspect.METAL, 4).add(Aspect.SENSES, 2)
                .register();

        $(TFItems.flour)
                .add(Aspect.CROP, 2).add(Aspect.HUNGER, 2).add(Aspect.ENTROPY, 1)
                .register();
        $(TFItems.bakingSoda)
                .add(Aspect.EARTH, 1).add(Aspect.ENTROPY, 1)
                .register();
        $(TFItems.ramenNoodles)
                .add(Aspect.HUNGER, 2).add(Aspect.ENTROPY, 1)
                .register();
        $(TFItems.udonNoodles)
                .add(Aspect.HUNGER, 2).add(Aspect.ENTROPY, 1)
                .register();
        $(ItemTFFoodSet.kamaboko.getStack())
                .add(Aspect.HUNGER, 2).add(Aspect.TREE, 1).add(Aspect.FLESH, 1)
                .register();
        $(ItemTFFoodSet.soySauceUdon.getStack())
                .add(Aspect.HUNGER, 4).add(Aspect.FIRE, 1).add(Aspect.ENTROPY, 1)
                .register();
        $(ItemTFFoodSet.misoUdonStew.getStack())
                .add(Aspect.HUNGER, 6).add(Aspect.FIRE, 4).add(Aspect.ENTROPY, 2)
                .register();
        $(ItemTFFoodSet.soySauceRamen.getStack())
                .add(Aspect.HUNGER, 4).add(Aspect.FIRE, 1).add(Aspect.ENTROPY, 1)
                .register();

        // === TofuCraft ===
        $(TcItems.soybeans)
                .add(TOFU, 1).add(Aspect.CROP, 2)
                .register();
        $(TcItems.nigari)
                .add(Aspect.ORDER, 1)
                .register();

        $(TcItems.tofuKinu)
                .add(TOFU, 2).add(Aspect.WATER, 3)
                .register();
        $(TcItems.tofuMomen)
                .add(TOFU, 1).add(Aspect.WATER, 2)
                .register();
        $(TcItems.tofuIshi)
                .add(TOFU, 1).add(Aspect.WATER, 1).add(Aspect.EARTH, 1)
                .register();
        $(TcItems.tofuMetal)
                .add(TOFU, 2).add(Aspect.METAL, 2).add(Aspect.EARTH, 2)
                .register();
        $(TcItems.tofuGrilled)
                .add(TOFU, 2).add(Aspect.WATER, 1).add(Aspect.FIRE, 3)
                .register();
        $(TcItems.tofuDried)
                .add(TOFU, 2).add(Aspect.EARTH, 1).add(Aspect.AIR, 3)
                .register();
        $(TcItems.tofuFriedPouch)
                .add(TOFU, 2).add(Aspect.WATER, 1).add(Aspect.FIRE, 1)
                .register();
        $(TcItems.tofuFried)
                .add(TOFU, 2).add(Aspect.WATER, 1).add(Aspect.FIRE, 1)
                .register();
        $(TcItems.tofuEgg)
                .add(TOFU, 1).add(Aspect.WATER, 2).add(Aspect.LIFE, 1)
                .register();
        $(TcItems.tofuHell)
                .add(TOFU, 3).add(Aspect.FIRE, 4).add(Aspect.SOUL, 2)
                .register();
        $(TcItems.tofuGlow)
                .add(TOFU, 2).add(Aspect.LIGHT, 4)
                .register();
        $(TcItems.tofuDiamond)
                .add(TOFU, 12).add(Aspect.CRYSTAL, 6)
                .register();

        $(TcItems.tofuCake) // Only the item has aspects
                .add(TOFU, 4).add(Aspect.WATER, 4).add(Aspect.HUNGER, 4).add(Aspect.LIFE, 4)
                .register();
        $(TcItems.tofuStick)
                .add(TOFU, 8).add(Aspect.MAGIC, 4).add(Aspect.TOOL, 2)
                .register();

        $(TcItems.koujiBase)
                .add(Aspect.ENTROPY, 2)
                .register();
        $(TcItems.kouji)
                .add(Aspect.ENTROPY, 3)
                .register();
        $(TcItems.miso)
                .add(Aspect.HUNGER, 2).add(Aspect.EARTH, 1)
                .register();
        $(TcItems.yudofu)
                .add(TOFU, 2).add(Aspect.FIRE, 1).add(Aspect.WATER, 2)
                .register();
        $(TcItems.tttBurger)
                .add(TOFU, 2).add(Aspect.SENSES, 1)
                .register();
        $(TcItems.morijio) // Only the item has aspects
                .add(Aspect.AURA, 2).add(Aspect.ORDER, 2).add(Aspect.AIR, 4)
                .register();
        $(TcItems.bugle)
                .add(TOFU, 1).add(Aspect.SENSES, 1).add(Aspect.METAL, 2)
                .register();
        $(TcItems.misoSoup)
                .add(TOFU, 2).add(Aspect.SENSES, 2).add(Aspect.HUNGER, 2)
                .register();
        $(TcItems.misoDengaku)
                .add(TOFU, 2).add(Aspect.HUNGER, 2)
                .register();
        $(TcItems.edamame)
                .add(Aspect.CROP, 2)
                .register();
        $(TcItems.zunda)
                .add(Aspect.ENTROPY, 1).add(Aspect.WATER, 1)
                .register();
        $(TcItems.bucketSoymilk)
                .add(TOFU, 1).add(Aspect.WATER, 2)
                .register();
        $(TcItems.edamameBoiled)
                .add(Aspect.ENTROPY, 2).add(Aspect.HUNGER, 1)
                .register();
        $(TcItems.bottleSoymilk)
                .add(TOFU, 1).add(Aspect.WATER, 1).add(Aspect.HEAL, 2)
                .register();
        $(TcItems.barrelEmpty)
                .add(Aspect.VOID, 1)
                .register();
        $(TcItems.zundaManju)
                .add(Aspect.HUNGER, 1).add(Aspect.HEAL, 2)
                .register();
        $(TcItems.bucketSoySauce)
                .add(Aspect.WATER, 2)
                .register();
//        $(TcItems.phialEmpty)
//                .register();
        $(TcItems.bottleSoySauce)
                .add(Aspect.WATER, 1).add(Aspect.TOOL, 2)
                .register();
        $(TcItems.soybeansParched)
                .add(Aspect.ENTROPY, 1).add(Aspect.FIRE, 1)
                .register();
        $(TcItems.kinako)
                .add(Aspect.ENTROPY, 2)
                .register();
        $(TcItems.nikujaga)
                .add(Aspect.HUNGER, 6).add(Aspect.SENSES, 3)
                .register();
        $(TcItems.defattingPotion)
                .add(Aspect.TOOL, 2)
                .register();
        $(TcItems.dashi)
                .add(Aspect.HUNGER, 1).add(Aspect.WATER, 2).add(Aspect.SENSES, 2)
                .register();
        $(TcItems.soyOil)
                .add(Aspect.TOOL, 2).add(Aspect.FIRE, 2)
                .register();
        $(TcItems.agedashiTofu)
                .add(Aspect.HUNGER, 4).add(Aspect.SENSES, 2)
                .register();
        $(TcItems.kinakoManju)
                .add(Aspect.HUNGER, 1)
                .register();
        $(TcItems.fukumeni)
                .add(Aspect.HUNGER, 2).add(Aspect.SENSES, 2)
                .register();
        $(TcItems.koyadofuStew)
                .add(Aspect.HUNGER, 4).add(Aspect.SENSES, 3)
                .register();
        $(TcItems.natto)
                .add(Aspect.HUNGER, 1).add(Aspect.SLIME, 2)
                        .register();
        $(TcItems.nattoHiyayakko)
                .add(Aspect.HUNGER, 4).add(Aspect.SENSES, 2)
                .register();
        $(TcItems.salt)
                .add(Aspect.ENTROPY, 1)
                .register();
        $(TcItems.saltyMelon)
                .add(Aspect.HUNGER, 2).add(Aspect.SENSES, 2)
                .register();
        $(TcItems.tastyStew)
                .add(Aspect.HUNGER, 10).add(Aspect.SENSES, 4)
                .register();
        $(TcItems.tastyBeefStew)
                .add(Aspect.HUNGER, 10).add(Aspect.SENSES, 4)
                .register();
        $(TcItems.goldenSalt)
                .add(Aspect.AURA, 4)
                .register();
        $(TcItems.zundaArrow)
                .add(Aspect.SENSES, 1).add(Aspect.HEAL, 1).add(Aspect.WEAPON, 1)
                .register();
        $(TcItems.zundaBow)
                .add(Aspect.SENSES, 4).add(Aspect.HEAL, 6).add(Aspect.WEAPON, 3).add(Aspect.FLIGHT, 1).add(Aspect.CLOTH, 2)
                .register();
        $(TcItems.apricot)
                .add(Aspect.HUNGER, 2).add(Aspect.CROP, 2)
                .register();
        $(TcItems.apricotSeed)
                .add(Aspect.PLANT, 1)
                .register();
        $(TcItems.filterCloth)
                .add(Aspect.CLOTH, 1)
                .register();
        $(TcItems.okara)
                .add(Aspect.ENTROPY, 1).add(Aspect.CROP, 1)
                .register();
        $(TcItems.mincedPotato)
                .add(Aspect.ENTROPY, 1).add(Aspect.CROP, 1)
                .register();
        $(TcItems.starchRaw)
                .add(Aspect.WATER, 1).add(Aspect.ORDER, 1)
                .register();
        $(TcItems.starch)
                .add(Aspect.ENTROPY, 1).add(Aspect.FIRE, 1)
                .register();
        $(TcItems.tofuAnnin)
                .add(TOFU, 1).add(Aspect.SENSES, 4).add(Aspect.HUNGER, 4)
                .register();
        $(TcItems.tofuSesame)
                .add(TOFU, 1).add(Aspect.SENSES, 4).add(Aspect.HUNGER, 2).add(Aspect.MIND, 2)
                .register();
        $(TcItems.tofuZunda)
                .add(TOFU, 1).add(Aspect.SENSES, 4).add(Aspect.HUNGER, 2).add(Aspect.HEAL, 1)
                .register();
        $(TcItems.kyoninso)
                .add(Aspect.ENTROPY, 1)
                .register();
        $(TcItems.leek)
                .add(Aspect.CROP, 2).add(Aspect.EARTH, 1)
                .register();
        $(TcItems.sesame)
                .add(Aspect.CROP, 2)
                .register();
        $(TcItems.okaraStick)
                .add(Aspect.HUNGER, 3).add(Aspect.SENSES, 1)
                .register();
        $(TcItems.tofuStrawberry)
                .add(TOFU, 1).add(Aspect.SENSES, 4).add(Aspect.HUNGER, 4).add(Aspect.GREED, 2)
                .register();
        $(TcItems.gelatin).withMetadata(0)
                .add(Aspect.ENTROPY, 1).add(Aspect.DEATH, 1)
                .register();
        $(TcItems.gelatin).withMetadata(1)
                .add(Aspect.ORDER, 1)
                .register();
        $(TcItems.riceNatto)
                .add(Aspect.SENSES, 1).add(Aspect.HUNGER, 5).add(Aspect.CROP, 2)
                .register();
        $(TcItems.riceNattoLeek)
                .add(Aspect.SENSES, 1).add(Aspect.HUNGER, 6).add(Aspect.CROP, 3)
                .register();
        $(TcItems.zundama)
                .add(Aspect.SENSES, 2).add(Aspect.LIGHT, 1).add(Aspect.CROP, 2).add(Aspect.AURA, 1)
                .register();
        $(TcItems.fukumame)
                .add(Aspect.WEAPON, 3).add(Aspect.SENSES, 1).add(Aspect.ENTROPY, 4).add(Aspect.CROP, 1)
                .register();
        $(TcItems.bucketSoymilkHell)
                .add(TOFU, 1).add(Aspect.FIRE, 4).add(Aspect.SOUL, 2)
                .register();
        $(TcItems.soybeansHell)
                .add(TOFU, 1).add(Aspect.FIRE, 4).add(Aspect.SOUL, 1).add(Aspect.CROP, 2)
                .register();
        $(TcItems.tofuScoop)
                .add(Aspect.METAL, 1).add(Aspect.TOOL, 3)
                .register();

        $(TcItems.materials).withMetadata(ItemTcMaterials.tofuGem.id)
                .add(TOFU, 4).add(Aspect.ENERGY, 1).add(Aspect.CRYSTAL, 1)
                .register();
        $(TcItems.materials).withMetadata(ItemTcMaterials.activatedHellTofu.id)
                .add(TOFU, 10).add(Aspect.ENERGY, 10).add(Aspect.SOUL, 10).add(Aspect.FIRE, 10)
                .register();
        $(TcItems.materials).withMetadata(ItemTcMaterials.activatedTofuGem.id)
                .add(TOFU, 10).add(Aspect.ENERGY, 10).add(Aspect.CRYSTAL, 10)
                .register();
        $(TcItems.materials).withMetadata(ItemTcMaterials.advTofuGem.id)
                .add(TOFU, 5).add(Aspect.ENERGY, 6).add(Aspect.CRYSTAL, 2)
                .register();
        $(TcItems.materials).withMetadata(ItemTcMaterials.glassBowl.id)
                .add(Aspect.CRYSTAL, 1).add(Aspect.VOID, 1).add(Aspect.SENSES, 1)
                .register();
        $(TcItems.materials).withMetadata(ItemTcMaterials.mineralSoymilk.id)
                .add(TOFU, 2).add(Aspect.CRYSTAL, 2).add(Aspect.ENERGY, 2)
                .register();
        $(TcItems.materials).withMetadata(ItemTcMaterials.tofuSomen.id)
                .add(TOFU, 2).add(Aspect.ENTROPY, 1).add(Aspect.WATER, 1)
                .register();
        $(TcItems.materials).withMetadata(ItemTcMaterials.tofuDiamondNugget.id)
                .add(TOFU, 3).add(Aspect.CRYSTAL, 2)
                .register();

        $(TcItems.foodSet).withMetadata(ItemFoodSet.oage.id)
                .add(TOFU, 2).add(Aspect.FIRE, 1).add(Aspect.HUNGER, 2)
                .register();
        $(TcItems.foodSet).withMetadata(ItemFoodSet.sprouts.id)
                .add(Aspect.WATER, 1).add(Aspect.DARKNESS, 2).add(Aspect.CROP, 2).add(Aspect.HUNGER, 1)
                .register();
        $(TcItems.foodSet).withMetadata(ItemFoodSet.tofuHamburg.id)
                .add(TOFU, 3).add(Aspect.HUNGER, 8)
                .register();
        $(TcItems.foodSet).withMetadata(ItemFoodSet.sproutSaute.id)
                .add(Aspect.SENSES, 2).add(Aspect.HUNGER, 6).add(Aspect.DARKNESS, 1).add(Aspect.WATER, 1)
                .register();
        $(TcItems.foodSet).withMetadata(ItemFoodSet.tofuCookie.id)
                .add(TOFU, 2).add(Aspect.SENSES, 2).add(Aspect.HUNGER, 3)
                .register();
        $(TcItems.foodSet).withMetadata(ItemFoodSet.hiyayakko.id)
                .add(TOFU, 2).add(Aspect.SENSES, 1).add(Aspect.HUNGER, 2).add(Aspect.WATER, 3).add(Aspect.CROP, 1)
                .register();
        $(TcItems.foodSet).withMetadata(ItemFoodSet.hiyayakkoGl.id)
                .add(TOFU, 2).add(Aspect.SENSES, 3).add(Aspect.HUNGER, 2).add(Aspect.WATER, 3).add(Aspect.CROP, 1)
                .register();
        $(TcItems.foodSet).withMetadata(ItemFoodSet.chikuwa.id)
                .add(Aspect.FLESH, 1).add(Aspect.HUNGER, 2).add(Aspect.WATER, 1)
                .register();
        $(TcItems.foodSet).withMetadata(ItemFoodSet.inari.id)
                .add(TOFU, 1).add(Aspect.HUNGER, 4).add(Aspect.CROP, 1)
                .register();
        $(TcItems.foodSet).withMetadata(ItemFoodSet.onigiri.id)
                .add(Aspect.HUNGER, 4).add(Aspect.CROP, 3)
                .register();
        $(TcItems.foodSet).withMetadata(ItemFoodSet.onigiriSalt.id)
                .add(Aspect.HUNGER, 5).add(Aspect.CROP, 3)
                .register();
        $(TcItems.foodSetStick).withMetadata(ItemFoodSetStick.goheimochi.id)
                .add(Aspect.HUNGER, 6).add(Aspect.CROP, 2)
                .register();

        $(TcItems.doubanjiang)
                .add(Aspect.FIRE, 2).add(Aspect.CROP, 2).add(Aspect.GREED, 1)
                .register();
        $(TcItems.strawberryJam)
                .add(Aspect.CROP, 2).add(Aspect.WATER, 1).add(Aspect.GREED, 1)
                .register();
        $(TcItems.tofuRadar)
                .add(TOFU, 1).add(Aspect.MECHANISM, 3).add(Aspect.SLIME, 1).add(Aspect.AIR, 6)
                .register();
        $(TcItems.yuba)
                .add(TOFU, 2).add(Aspect.WATER, 2).add(Aspect.HUNGER, 1)
                .register();
        $(TcItems.tofuMiso)
                .add(TOFU, 4).add(Aspect.HUNGER, 20).add(Aspect.SENSES, 3)
                .register();
        $(TcItems.somenTsuyuBowl)
                .add(Aspect.WATER, 2).add(Aspect.HUNGER, 1)
                .register();

        $(TcItems.tofuHoe)
                .add(TOFU, 16).add(Aspect.HARVEST, 3)
                .register();
    }
    
    private AspectRegisterItemStack $(Item item)
    {
        return $(new ItemStack(item));
    }

    private AspectRegisterItemStack $(Block block)
    {
        return $(new ItemStack(block));
    }

    private AspectRegisterItemStack $(ItemStack itemStack)
    {
        return new AspectRegisterItemStack(itemStack);
    }

    private AspectRegisterEntity $(String entityName)
    {
        return new AspectRegisterEntity(entityName);
    }

    private class AspectRegisterBase<T extends AspectRegisterBase>
    {
        AspectList aspectList = new AspectList();

        T add(Aspect aspect, int amount)
        {
            aspectList.add(aspect, amount);
            return (T)this;
        }
    }

    private class AspectRegisterItemStack extends AspectRegisterBase<AspectRegisterItemStack>
    {
        ItemStack itemStack;
        int[] metadata = new int[]{OreDictionary.WILDCARD_VALUE};

        public AspectRegisterItemStack(ItemStack itemStack)
        {
            this.itemStack = itemStack;
        }

        AspectRegisterItemStack withMetadata(int... metadata)
        {
            this.metadata = metadata;
            return this;
        }

        void register()
        {
            ThaumcraftApi.registerObjectTag(itemStack, metadata, aspectList);
        }
    }

    private class AspectRegisterEntity extends AspectRegisterBase<AspectRegisterEntity>
    {
        String name;
        ThaumcraftApi.EntityTagsNBT[] nbtTags = new ThaumcraftApi.EntityTagsNBT[0];

        public AspectRegisterEntity(String nane)
        {
            this.name = nane;
        }

        public AspectRegisterEntity setNBTTags(ThaumcraftApi.EntityTagsNBT[] tags)
        {
            this.nbtTags = tags;
            return this;
        }

        void register()
        {
            ThaumcraftApi.registerEntityTag(name, aspectList, nbtTags);
        }
    }
}
