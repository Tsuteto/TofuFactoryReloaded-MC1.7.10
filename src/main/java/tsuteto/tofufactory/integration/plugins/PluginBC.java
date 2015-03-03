package tsuteto.tofufactory.integration.plugins;

import buildcraft.BuildCraftTransport;
import buildcraft.api.core.IIconProvider;
import buildcraft.core.CreativeTabBuildCraft;
import buildcraft.transport.BlockGenericPipe;
import buildcraft.transport.PipeTransportFluids;
import buildcraft.transport.PipeTransportPower;
import net.minecraft.item.Item;
import tsuteto.tofufactory.pipe.TFPipeIconProvider;
import tsuteto.tofufactory.core.TofuFactory;
import tsuteto.tofufactory.integration.ITFPlugin;
import tsuteto.tofufactory.pipe.PipeFluidsTofuIshi;
import tsuteto.tofufactory.pipe.PipeFluidsZunda;
import tsuteto.tofufactory.pipe.PipeItemsTofuIshi;
import tsuteto.tofufactory.pipe.PipeItemsZunda;
import tsuteto.tofufactory.pipe.PipePowerTofuIshi;
import tsuteto.tofufactory.pipe.PipePowerZunda;

public class PluginBC implements ITFPlugin
{
    public static Item pipeItemsTofuIshi;
    public static Item pipeFluidsTofuIshi;
    public static Item pipePowerTofuIshi;
    public static Item pipeItemsZunda;
    public static Item pipeFluidsZunda;
    public static Item pipePowerZunda;
    public static IIconProvider pipeIconProvider = new TFPipeIconProvider();

    public void init()
    {
        // Pipe Items
        pipeItemsTofuIshi = BlockGenericPipe.registerPipe(PipeItemsTofuIshi.class, CreativeTabBuildCraft.PIPES);
        pipeItemsTofuIshi.setUnlocalizedName(TofuFactory.resourceDomain + "pipe.item.tofuIshi").setCreativeTab(TofuFactory.tabsTofuFactory);

        PipeTransportFluids.fluidCapacities.put(PipeFluidsTofuIshi.class, 2 * BuildCraftTransport.pipeFluidsBaseFlowRate);
        pipeFluidsTofuIshi = BlockGenericPipe.registerPipe(PipeFluidsTofuIshi.class, CreativeTabBuildCraft.PIPES);
        pipeFluidsTofuIshi.setUnlocalizedName(TofuFactory.resourceDomain + "pipe.fluid.tofuIshi").setCreativeTab(TofuFactory.tabsTofuFactory);

        PipeTransportPower.powerCapacities.put(PipePowerTofuIshi.class, 128);
        pipePowerTofuIshi = BlockGenericPipe.registerPipe(PipePowerTofuIshi.class, CreativeTabBuildCraft.PIPES);
        pipePowerTofuIshi.setUnlocalizedName(TofuFactory.resourceDomain + "pipe.power.tofuIshi").setCreativeTab(TofuFactory.tabsTofuFactory);

        pipeItemsZunda = BlockGenericPipe.registerPipe(PipeItemsZunda.class, CreativeTabBuildCraft.PIPES);
        pipeItemsZunda.setUnlocalizedName(TofuFactory.resourceDomain + "pipe.item.zunda").setCreativeTab(TofuFactory.tabsTofuFactory);

        PipeTransportFluids.fluidCapacities.put(PipeFluidsZunda.class, 2 * BuildCraftTransport.pipeFluidsBaseFlowRate);
        pipeFluidsZunda = BlockGenericPipe.registerPipe(PipeFluidsZunda.class, CreativeTabBuildCraft.PIPES);
        pipeFluidsZunda.setUnlocalizedName(TofuFactory.resourceDomain + "pipe.fluid.zunda").setCreativeTab(TofuFactory.tabsTofuFactory);

        PipeTransportPower.powerCapacities.put(PipePowerZunda.class, 128);
        pipePowerZunda = BlockGenericPipe.registerPipe(PipePowerZunda.class, CreativeTabBuildCraft.PIPES);
        pipePowerZunda.setUnlocalizedName(TofuFactory.resourceDomain + "pipe.power.zunda").setCreativeTab(TofuFactory.tabsTofuFactory);

        TofuFactory.proxy.registerRenderersBC();
    }
}
