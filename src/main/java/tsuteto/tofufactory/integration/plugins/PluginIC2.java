package tsuteto.tofufactory.integration.plugins;

import ic2.api.item.IC2Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import tsuteto.tofufactory.core.TFItems;
import tsuteto.tofufactory.core.TofuFactory;
import tsuteto.tofufactory.integration.ITFPlugin;
import tsuteto.tofufactory.item.ItemTFCell;
import tsuteto.tofufactory.item.ItemZundaBattery;
import tsuteto.tofufactory.item.ItemZundaSwordBow;
import tsuteto.tofufactory.registry.ItemRegister;

public class PluginIC2 implements ITFPlugin
{
    public static Item zundaBattery;
    public static Item zundaSwordBow;
    public static Item zundaSwordBowEmpty;
    public static Item tfCell;

    public static ItemStack diamondCuttingBlade;
    public static ItemStack ic2Cell;

    public void init()
    {
        initIc2Items();
        initOriginalItems();
        initCells();
    }

    private void initIc2Items()
    {
        ic2Cell = IC2Items.getItem("cell");
        diamondCuttingBlade = IC2Items.getItem("diamondblockcuttingblade");
    }

    private void initOriginalItems()
    {
        zundaBattery = ItemRegister.of("zundaBattery", new ItemZundaBattery())
                .register()
                .setCreativeTab(TofuFactory.tabsTofuFactory);
        zundaSwordBow = ItemRegister.of("zunda_swordBow", new ItemZundaSwordBow(TFItems.ZUNDA))
                .register();
        zundaSwordBowEmpty = ItemRegister.of("zunda_swordBowE", new Item())
                .withResource("zunda_swordBow")
                .register();
    }

    private void initCells()
    {
        tfCell = ItemRegister.of("TFCell", new ItemTFCell()).register()
                .setCreativeTab(TofuFactory.tabsTofuFactory);

        for (ItemTFCell.Info info : ItemTFCell.cells)
        {
            FluidContainerRegistry.registerFluidContainer(new FluidContainerData(
                    info.getFluid(), info.getStack(), ic2Cell.copy()));
        }
    }
}
