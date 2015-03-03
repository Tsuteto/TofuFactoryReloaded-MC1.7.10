package tsuteto.tofufactory.ffm.farming.farmlogic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestry.api.farming.Farmables;
import forestry.api.farming.IFarmHousing;
import forestry.api.farming.IFarmable;
import forestry.core.config.ForestryBlock;
import forestry.farming.logic.FarmLogicArboreal;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import tsuteto.tofu.block.TcBlocks;

public class FarmLogicApricot extends FarmLogicArboreal
{
    public FarmLogicApricot(IFarmHousing housing)
    {
        super(housing, new ItemStack[]{new ItemStack(Blocks.dirt)}, ForestryBlock.soil.getItemStack(1, 0),
                Farmables.farmables.get("farmApricot").toArray(new IFarmable[0]));
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon()
    {
        return TcBlocks.tcSapling.getBlockTextureFromSide(0);
    }

    public String getName()
    {
        return "Apricot";
    }
}
