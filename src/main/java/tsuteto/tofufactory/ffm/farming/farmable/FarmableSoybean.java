package tsuteto.tofufactory.ffm.farming.farmable;

import forestry.farming.logic.FarmableGenericCrop;
import net.minecraft.item.ItemStack;
import tsuteto.tofu.block.TcBlocks;
import tsuteto.tofu.item.TcItems;

public class FarmableSoybean extends FarmableGenericCrop
{
    public FarmableSoybean()
    {
        super(new ItemStack(TcItems.soybeans), TcBlocks.soybean, 7);
    }
}
