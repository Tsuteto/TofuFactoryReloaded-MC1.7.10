package tsuteto.tofufactory.ffm.farming.farmable;

import forestry.farming.logic.FarmableGenericCrop;
import net.minecraft.item.ItemStack;
import tsuteto.tofu.block.TcBlocks;
import tsuteto.tofu.item.TcItems;

public class FarmableSoybeanHell extends FarmableGenericCrop
{
    public FarmableSoybeanHell()
    {
        super(new ItemStack(TcItems.soybeansHell), TcBlocks.soybeanHell, 7);
    }
}
