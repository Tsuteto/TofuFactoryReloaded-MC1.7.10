package tsuteto.tofufactory.pipe;

import buildcraft.api.core.IIconProvider;
import buildcraft.transport.TravelingItem;
import buildcraft.transport.pipes.PipeItemsWood;
import buildcraft.transport.pipes.events.PipeEventItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.ForgeDirection;

public class PipeItemsZunda extends PipeItemsWood //implements IPipeTransportItemsHook
{
    private static final float pipeNormalSpeed = 0.01F;

    protected int standardIconIndex;
    protected int solidIconIndex;

    public PipeItemsZunda(Item item)
    {
        super(item);
        this.standardIconIndex = TFPipeIconProvider.TYPE.PipeItemsZunda_Standard.ordinal();
        this.solidIconIndex = TFPipeIconProvider.TYPE.PipeAllZunda_Solid.ordinal();
    }

    @Override
    public void initialize()
    {
        super.initialize();
        this.container.pipe.eventBus.registerHandler(this);
    }

    public IIconProvider getIconProvider()
    {
        return TFPipeIconProvider.INSTANCE;
    }

    public int getIconIndex(ForgeDirection direction)
    {
        if (direction == ForgeDirection.UNKNOWN)
        {
            return this.standardIconIndex;
        }
        else
        {
            int metadata = this.container.getBlockMetadata();
            return metadata == direction.ordinal() ? this.solidIconIndex : this.standardIconIndex;
        }
    }

    public void eventHandler(PipeEventItem.AdjustSpeed event) {
        event.handled = true;
        TravelingItem item = event.item;
        item.setSpeed(Math.min(Math.max(pipeNormalSpeed, item.getSpeed()) * 1.2F, pipeNormalSpeed * 15.0F));
    }

}
