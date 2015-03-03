package tsuteto.tofufactory.wand;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.wands.IWandTriggerManager;

public class TFWandTriggerManager implements IWandTriggerManager
{
    public boolean performTrigger(World world, ItemStack wand, EntityPlayer player, int x, int y, int z, int side, int event)
    {
        world.setBlock(x, y, z, Blocks.stone, 0, 2);
        return false;
    }
}
