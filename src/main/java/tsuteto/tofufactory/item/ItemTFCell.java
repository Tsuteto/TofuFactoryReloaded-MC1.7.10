package tsuteto.tofufactory.item;

import net.minecraft.item.Item;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import tsuteto.tofu.fluids.TcFluids;
import tsuteto.tofufactory.fluid.TFFluids;
import tsuteto.tofufactory.integration.plugins.PluginIC2;

public class ItemTFCell extends ItemSetBase
{
    public static final Info bittern = new Info(0, "bittern", TFFluids.bittern, 10);
    public static final Info drinkingWater = new Info(1, "drinkingWater", TFFluids.drinkingWater);
    public static final Info noodleSoup = new Info(2, "noodleSoup", TFFluids.noodleSoup);
    public static final Info soupStock = new Info(3, "soupStock", TFFluids.soupStock, 500);
    public static final Info zundaWater = new Info(4, "zundaWater", TFFluids.zunda);
    public static final Info soymilk = new Info(5, "soymilk", TcFluids.SOYMILK);
    public static final Info soymilkHell = new Info(6, "soymilkHell", TcFluids.SOYMILK_HELL);
    public static final Info soySauce = new Info(7, "soySauce", TcFluids.SOY_SAUCE);

    public static final Info[] cells = new Info[] {bittern, drinkingWater, noodleSoup, soupStock, zundaWater, soymilk, soymilkHell, soySauce};

    public ItemTFCell()
    {
        super(cells);
    }

    @Override
    public boolean isDamageable()
    {
        return false;
    }

    @Override
    public boolean isRepairable()
    {
        return false;
    }

    @Override
    public String getItemSetName()
    {
        return "cell";
    }

    public static class Info extends ItemSetInfo
    {
        private final FluidStack fluid;

        public Info(int id, String name, Fluid fluid)
        {
            this(id, name, fluid, 1000);
        }

        public Info(int id, String name, Fluid fluid, int amount)
        {
            super(id, name);
            this.fluid = new FluidStack(fluid, amount);
        }

        public FluidStack getFluid()
        {
            return fluid.copy();
        }

        @Override
        protected Item getItemInstance()
        {
            return PluginIC2.tfCell;
        }
    }
}
