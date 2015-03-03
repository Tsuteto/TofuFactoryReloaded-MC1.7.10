package tsuteto.tofufactory.ffm.farming.farmable;

import forestry.farming.logic.FarmableGenericSapling;
import net.minecraft.item.ItemStack;
import tsuteto.tofu.block.TcBlocks;
import tsuteto.tofu.item.TcItems;

public class FarmableApricot extends FarmableGenericSapling
{
    public FarmableApricot()
    {
        super(TcBlocks.tcSapling, 0, new ItemStack(TcItems.apricot));
    }
}
