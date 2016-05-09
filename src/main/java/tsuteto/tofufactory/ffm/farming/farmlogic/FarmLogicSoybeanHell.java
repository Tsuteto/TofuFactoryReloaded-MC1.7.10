package tsuteto.tofufactory.ffm.farming.farmlogic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestry.api.farming.Farmables;
import forestry.api.farming.IFarmHousing;
import forestry.farming.logic.FarmLogicArboreal;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import tsuteto.tofu.block.TcBlocks;

public class FarmLogicSoybeanHell extends FarmLogicArboreal
{
    public FarmLogicSoybeanHell(IFarmHousing housing)
    {
        super(housing, new ItemStack(Blocks.soul_sand), new ItemStack(Blocks.soul_sand),
                Farmables.farmables.get("farmSoybeanHell"));
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon()
    {
        return TcBlocks.soybeanHell.getIcon(0, 7);
    }

    public String getName()
    {
        return "SoybeanHell";
    }
}
