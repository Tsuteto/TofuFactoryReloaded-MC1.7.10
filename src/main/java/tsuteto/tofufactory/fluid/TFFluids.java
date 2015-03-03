package tsuteto.tofufactory.fluid;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.TextureStitchEvent.Pre;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import tsuteto.tofufactory.core.TofuFactory;
import tsuteto.tofu.fluids.TcFluids;
import tsuteto.tofu.item.TcItems;

public class TFFluids
{
    public static Fluid soupStock;
    public static Fluid noodleSoup;
    public static Fluid zunda;
    public static Fluid drinkingWater;
    public static Fluid bittern;
    public static Fluid moltenZundaFluid;

    public static void registerFluids()
    {
        soupStock = TcFluids.SOUP_STOCK;

        noodleSoup = new Fluid("NoodleSoup");
        FluidRegistry.registerFluid(noodleSoup);

        zunda = new Fluid("Zunda");
        FluidRegistry.registerFluid(zunda);

        drinkingWater = new Fluid("DrinkingWater");
        FluidRegistry.registerFluid(drinkingWater);

        bittern = TcFluids.NIGARI;

        moltenZundaFluid = new Fluid("MoltenZunda");
        FluidRegistry.registerFluid(moltenZundaFluid);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void textureHook(Pre event)
    {
        if (event.map.getTextureType() == 0)
        {
            String n = "fluid/";
            IIcon NoodleSoupIcon = event.map.registerIcon(TofuFactory.resourceDomain + n + "NoodleSoup");
            noodleSoup.setIcons(NoodleSoupIcon);
            IIcon ZundaIcon = event.map.registerIcon(TofuFactory.resourceDomain + n + "zunda_water");
            zunda.setIcons(ZundaIcon);
            IIcon DrinkingWaterIcon = event.map.registerIcon(TofuFactory.resourceDomain + n + "Drinking_water");
            drinkingWater.setIcons(DrinkingWaterIcon);
            IIcon moltenZundaFluidIcon = event.map.registerIcon(TofuFactory.resourceDomain + n + "moltenZundaFluid");
            moltenZundaFluid.setIcons(moltenZundaFluidIcon);
        }
    }
}
