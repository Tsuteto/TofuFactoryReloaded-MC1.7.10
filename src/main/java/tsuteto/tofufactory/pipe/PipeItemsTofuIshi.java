package tsuteto.tofufactory.pipe;

import buildcraft.api.core.IIconProvider;
import buildcraft.transport.Pipe;
import buildcraft.transport.PipeTransportItems;
import buildcraft.transport.TravelingItem;
import buildcraft.transport.pipes.events.PipeEventItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.ForgeDirection;
import tsuteto.tofufactory.integration.plugins.PluginBC;
import tsuteto.tofufactory.integration.plugins.PluginBC;

public class PipeItemsTofuIshi extends Pipe<PipeTransportItems> //implements IPipeTransportItemsHook
{
    private static final float pipeNormalSpeed = 0.01F;

    public PipeItemsTofuIshi(Item item)
    {
        super(new PipeTransportItems(), item);
    }

    @Override
    public void initialize()
    {
        super.initialize();
        this.container.pipe.eventBus.registerHandler(this);
    }

    @Override
    public IIconProvider getIconProvider()
    {
        return PluginBC.pipeIconProvider;
    }

    @Override
    public int getIconIndex(ForgeDirection direction)
    {
        return TFPipeIconProvider.TYPE.PipeItemsTofuIshi.ordinal();
    }

    public void eventHandler(PipeEventItem.AdjustSpeed event) {
        event.handled = true;
        TravelingItem item = event.item;
        item.setSpeed(Math.min(Math.max(pipeNormalSpeed, item.getSpeed()) * 1.2F, pipeNormalSpeed * 15.0F));
    }
}
