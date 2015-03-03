package tsuteto.tofufactory.ffm.farming.farmlogic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestry.api.farming.Farmables;
import forestry.api.farming.IFarmHousing;
import forestry.api.farming.IFarmable;
import forestry.farming.logic.FarmLogicArboreal;
import forestry.farming.logic.FarmLogicCrops;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import tsuteto.tofu.block.TcBlocks;
import tsuteto.tofu.item.TcItems;
import tsuteto.tofufactory.proxy.TFIconTexture;

public class FarmLogicSoybeanHell extends FarmLogicArboreal
{
    public FarmLogicSoybeanHell(IFarmHousing housing)
    {
        super(housing, new ItemStack[]{new ItemStack(Blocks.soul_sand)}, new ItemStack(Blocks.soul_sand),
                Farmables.farmables.get("farmSoybeanHell").toArray(new IFarmable[0]));
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
