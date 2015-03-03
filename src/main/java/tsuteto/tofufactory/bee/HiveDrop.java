package tsuteto.tofufactory.bee;

import com.google.common.collect.Lists;
import forestry.api.apiculture.EnumBeeType;
import forestry.api.apiculture.IBee;
import forestry.api.apiculture.IHiveDrop;
import forestry.api.genetics.IAllele;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class HiveDrop implements IHiveDrop
{
    private IAllele[] template;
    private ArrayList<ItemStack> additional = Lists.newArrayList();
    private int chance;
    private float ignobleShare = 0.0F;

    public HiveDrop(IAllele[] template, ItemStack[] bonus, int chance)
    {
        this.template = template;
        this.chance = chance;
        ItemStack[] arr$ = bonus;
        int len$ = bonus.length;

        for (int i$ = 0; i$ < len$; ++i$)
        {
            ItemStack stack = arr$[i$];
            this.additional.add(stack);
        }
    }

    public HiveDrop setIgnobleShare(float share)
    {
        this.ignobleShare = share;
        return this;
    }

    private IBee createBee(World world)
    {
        IBee bee = BeeManager.beeInterface.getBee(world, BeeManager.beeInterface.templateAsGenome(this.template));

        if (world.rand.nextFloat() < this.ignobleShare)
        {
            bee.setIsNatural(false);
        }

        return bee;
    }

    public ItemStack getPrincess(World world, int x, int y, int z, int fortune)
    {
        return BeeManager.beeInterface.getMemberStack(this.createBee(world), EnumBeeType.PRINCESS.ordinal());
    }

    public ArrayList<ItemStack> getDrones(World world, int x, int y, int z, int fortune)
    {
        ArrayList ret = new ArrayList();
        ret.add(BeeManager.beeInterface.getMemberStack(this.createBee(world), EnumBeeType.DRONE.ordinal()));
        return ret;
    }

    public ArrayList<ItemStack> getAdditional(World world, int x, int y, int z, int fortune)
    {
        ArrayList ret = new ArrayList();
        Iterator i$ = this.additional.iterator();

        while (i$.hasNext())
        {
            ItemStack stack = (ItemStack)i$.next();
            ret.add(stack.copy());
        }

        return ret;
    }

    public int getChance(World world, int x, int y, int z)
    {
        return this.chance;
    }
}
