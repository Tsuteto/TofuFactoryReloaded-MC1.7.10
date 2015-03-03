package tsuteto.tofufactory.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraftforge.common.util.EnumHelper;
import tsuteto.tofufactory.block.*;
import tsuteto.tofufactory.item.*;
import tsuteto.tofufactory.registry.BlockRegister;
import tsuteto.tofufactory.registry.ItemRegister;
import tsuteto.tofufactory.tileentity.TileEntityTofuCompactor;
import tsuteto.tofufactory.tileentity.TileEntityTofuCuttingMachine;
import tsuteto.tofufactory.tileentity.TileEntityTofuPulverizer;

public class TFItems
{
    public static final CommonMaterial tofuSteel = new CommonMaterial(0, "tofuSteel");
    public static final CommonMaterial mithrilTofu = new CommonMaterial(1, "mithrilTofu");
    public static final CommonMaterial sol = new CommonMaterial(2, "sol");
    public static final CommonMaterial luna = new CommonMaterial(3, "luna");
    public static final CommonMaterial[] materials = new CommonMaterial[]{tofuSteel, mithrilTofu, sol, luna};

    public static Item.ToolMaterial ZUNDA;
    public static Item zundaSword;
    public static Item zundaIngot;
    public static Item tofuMaterial;
    public static Item tofuDust;
    public static Item tofuGear;
    public static Item tofuIngot;
    public static Item tofuPlate;
    public static Item tofuGem;
    public static Item flour;
    public static Item bakingSoda;
    public static Item udonNoodles;
    public static Item ramenNoodles;
    public static Item craftingHammer;
    public static Item foodSet;
    public static Block oreMithril;
    public static Block oreMithrilTofu;
    public static Block tofuPulverizerIdle;
    public static Block tofuPulverizerActive;
    public static Block tofuCompactorIdle;
    public static Block tofuCompactorActive;
    public static Block tofuCuttingMachineIdle;
    public static Block tofuCuttingMachineActive;
    public static Block tofuMetalBlock;

    public static void init()
    {
        ZUNDA = EnumHelper.addToolMaterial("ZUNDA", 2, 415, 6.0F, 2.0F, 8);
        zundaSword = ItemRegister.of("zundaSword", new ItemZundaSword(ZUNDA)).register();
        zundaIngot = ItemRegister.of("zundaIngot", new ItemTofuFactory()).register();

        tofuMaterial = ItemRegister.of("TofuMaterial",  new ItemTFMaterial()).register();
        tofuDust = ItemRegister.of("tofuDust",  new ItemTFDust()).register();
        tofuIngot = ItemRegister.of("tofuIngot", new ItemTofuIngot()).register();
        tofuPlate = ItemRegister.of("tofuPlate", new ItemTofuPlate()).register();
        tofuGear = ItemRegister.of("tofuGear", new ItemTofuGear()).register();
        tofuGem = ItemRegister.of("tofuGem",  new ItemTofuGem()).register();

        flour = ItemRegister.of("flour", new ItemTofuFactory()).register();
        bakingSoda = ItemRegister.of("bakingSoda", new ItemTofuFactory()).register();
        udonNoodles = ItemRegister.of("udonNoodles", new ItemTofuFactory()).register();
        ramenNoodles = ItemRegister.of("ramenNoodles", new ItemTofuFactory()).register();
        craftingHammer = ItemRegister.of("craftingHammer", new ItemCraftingHammer()).register();
        foodSet = ItemRegister.of("foodSet", new ItemTFFoodSet()).register();

        oreMithril = BlockRegister.of("oreMithril", new BlockHidden(Material.rock)).register()
                .setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone);

        oreMithrilTofu = BlockRegister.of("oreMithrilTofu", new BlockTofuFactory(Material.rock))
                .setHarvestLevel("pickaxe", 2)
                .register()
                .setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypePiston);

        tofuMetalBlock = BlockRegister.of("blockTofuMetal", new BlockTofuMetal(Material.iron))
                .wrappedBy(ItemTFMultiTextureTile.class)
                .havingArgs(new Object[]{BlockTofuMetal.blocks})
                .register()
                .setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeMetal);

        tofuPulverizerIdle = BlockRegister.of("Pulverizer", new BlockTofuPulverizer(false))
                .withResource("tfPulverizer")
                .setHarvestLevel("pickaxe", 0)
                .register()
                .setBlockTextureName("Pulverizer")
                .setHardness(3.5F).setStepSound(Block.soundTypeMetal);
        tofuPulverizerActive = BlockRegister.of("PulverizerActive", new BlockTofuPulverizer(true))
                .withResource("tfPulverizer")
                .setHarvestLevel("pickaxe", 0)
                .register()
                .setBlockTextureName("Pulverizer")
                .setHardness(3.5F).setStepSound(Block.soundTypeMetal);
        tofuCompactorIdle = BlockRegister.of("Compactor", new BlockTofuCompactor(false))
                .withResource("tfCompactor")
                .setHarvestLevel("pickaxe", 0)
                .register()
                .setBlockTextureName("Compactor")
                .setHardness(3.5F).setStepSound(Block.soundTypeMetal);
        tofuCompactorActive = BlockRegister.of("CompactorActive", new BlockTofuCompactor(true))
                .withResource("tfCompactor")
                .setHarvestLevel("pickaxe", 0)
                .register()
                .setBlockTextureName("Compactor")
                .setHardness(3.5F).setStepSound(Block.soundTypeMetal);
        tofuCuttingMachineIdle = BlockRegister.of("CuttingMachine", new BlockTofuCuttingMachine(false))
                .withResource("tfCuttingMachine")
                .setHarvestLevel("pickaxe", 0)
                .register()
                .setBlockTextureName("CuttingMachine")
                .setHardness(3.5F).setStepSound(Block.soundTypeMetal);
        tofuCuttingMachineActive = BlockRegister.of("CuttingMachineActive", new BlockTofuCuttingMachine(true))
                .withResource("tfCuttingMachine")
                .setHarvestLevel("pickaxe", 0)
                .register()
                .setBlockTextureName("CuttingMachine")
                .setHardness(3.5F).setStepSound(Block.soundTypeMetal);

        GameRegistry.registerTileEntity(TileEntityTofuPulverizer.class, "TfPulverizer");
        GameRegistry.registerTileEntity(TileEntityTofuCompactor.class, "TfCompactor");
        GameRegistry.registerTileEntity(TileEntityTofuCuttingMachine.class, "TfCuttingMachine");

    }

    public static class CommonMaterial
    {
        public final int id;
        public final String name;

        public CommonMaterial(int id, String name)
        {
            this.id = id;
            this.name = name;
        }
    }
}
