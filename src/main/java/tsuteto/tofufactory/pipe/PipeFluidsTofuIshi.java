package tsuteto.tofufactory.pipe;

import buildcraft.api.core.IIconProvider;
import buildcraft.transport.Pipe;
import buildcraft.transport.PipeTransportFluids;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.ForgeDirection;

public class PipeFluidsTofuIshi extends Pipe<PipeTransportFluids>
{
    public PipeFluidsTofuIshi(Item item)
    {
        super(new PipeTransportFluids(), item);
        this.transport.flowRate = 20;
        this.transport.travelDelay = 2;
    }

    @Override
    public IIconProvider getIconProvider()
    {
        return TFPipeIconProvider.INSTANCE;
    }

    @Override
    public int getIconIndex(ForgeDirection direction)
    {
        return TFPipeIconProvider.TYPE.PipeFluidsTofuIshi.ordinal();
    }
}
