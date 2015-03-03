package tsuteto.tofufactory.ffm.farming.farmable;

import forestry.farming.logic.FarmableGenericCrop;
import net.minecraft.item.ItemStack;
import tsuteto.tofu.block.TcBlocks;
import tsuteto.tofu.item.TcItems;

public class FarmableSesame extends FarmableGenericCrop
{
    public FarmableSesame()
    {
        super(new ItemStack(TcItems.sesame), TcBlocks.sesame, 7);
    }

}
