package tsuteto.tofufactory.ffm.farming.farmlogic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestry.api.farming.Farmables;
import forestry.api.farming.IFarmHousing;
import forestry.api.farming.IFarmable;
import forestry.farming.logic.FarmLogicArboreal;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;
import tsuteto.tofu.block.TcBlocks;

public class FarmLogicSprouts extends FarmLogicArboreal
{
    public FarmLogicSprouts(IFarmHousing housing)
    {
        super(housing, new ItemStack[]{new ItemStack(Blocks.wool, 1, OreDictionary.WILDCARD_VALUE)}, new ItemStack(Blocks.wool, 1, OreDictionary.WILDCARD_VALUE),
                Farmables.farmables.get("farmSprouts").toArray(new IFarmable[0]));
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon()
    {
        return TcBlocks.sprouts.getIcon(0, 7);
    }

    public String getName()
    {
        return "Sprouts";
    }
}
