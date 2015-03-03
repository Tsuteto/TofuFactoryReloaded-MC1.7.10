package tsuteto.tofufactory.ffm.farming.farmlogic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestry.api.farming.Farmables;
import forestry.api.farming.IFarmHousing;
import forestry.api.farming.IFarmable;
import forestry.farming.logic.FarmLogicArboreal;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import tsuteto.tofu.block.TcBlocks;

public class FarmLogicTofuTree extends FarmLogicArboreal
{
    public FarmLogicTofuTree(IFarmHousing housing)
    {
        super(housing, new ItemStack[]{new ItemStack(TcBlocks.tofuMomen)}, new ItemStack(TcBlocks.tofuMomen),
                Farmables.farmables.get("farmTofuTree").toArray(new IFarmable[0]));
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon()
    {
        return TcBlocks.tcSapling.getIcon(0, 1);
    }

    public String getName()
    {
        return "TofuTree";
    }
}
