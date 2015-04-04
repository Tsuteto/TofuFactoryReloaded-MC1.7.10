package tsuteto.tofufactory.pipe;

import buildcraft.api.core.IIconProvider;
import buildcraft.transport.PipeTransportPower;
import buildcraft.transport.pipes.PipePowerWood;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.ForgeDirection;

public class PipePowerZunda extends PipePowerWood
{
    protected int standardIconIndex;
    protected int solidIconIndex;

    public PipePowerZunda(Item item)
    {
        super(item);
        this.standardIconIndex = TFPipeIconProvider.TYPE.PipePowerZunda_Standard.ordinal();
        this.solidIconIndex = TFPipeIconProvider.TYPE.PipeAllZunda_Solid.ordinal();
        ((PipeTransportPower)this.transport).initFromPipe(this.getClass());
    }

    public IIconProvider getIconProvider()
    {
        return TFPipeIconProvider.INSTANCE;
    }

    public int getIconIndex(ForgeDirection direction)
    {
        return this.standardIconIndex;
    }
}
