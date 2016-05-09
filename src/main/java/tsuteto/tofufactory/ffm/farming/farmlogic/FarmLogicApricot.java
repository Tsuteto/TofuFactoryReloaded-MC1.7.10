package tsuteto.tofufactory.ffm.farming.farmlogic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestry.api.farming.Farmables;
import forestry.api.farming.IFarmHousing;
import forestry.core.blocks.BlockSoil;
import forestry.farming.logic.FarmLogicArboreal;
import forestry.plugins.PluginCore;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import tsuteto.tofu.block.TcBlocks;

public class FarmLogicApricot extends FarmLogicArboreal
{
    public FarmLogicApricot(IFarmHousing housing)
    {
        super(housing, new ItemStack(Blocks.dirt), PluginCore.blocks.soil.get(BlockSoil.SoilType.HUMUS, 1),
                Farmables.farmables.get("farmApricot"));
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
