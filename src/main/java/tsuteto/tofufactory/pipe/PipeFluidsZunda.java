package tsuteto.tofufactory.pipe;

import buildcraft.api.core.IIconProvider;
import buildcraft.transport.pipes.PipeFluidsWood;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.ForgeDirection;

public class PipeFluidsZunda extends PipeFluidsWood
{
    protected int standardIconIndex;
    protected int solidIconIndex;

    public PipeFluidsZunda(Item item)
    {
        super(item);
        this.standardIconIndex = TFPipeIconProvider.TYPE.PipeFluidsZunda_Standard.ordinal();
        this.solidIconIndex = TFPipeIconProvider.TYPE.PipeAllZunda_Solid.ordinal();
    }

    @Override
    public IIconProvider getIconProvider()
    {
        return TFPipeIconProvider.INSTANCE;
    }

    @Override
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
}
