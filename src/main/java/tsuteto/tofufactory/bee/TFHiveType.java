package tsuteto.tofufactory.bee;

import com.google.common.collect.Lists;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestry.api.apiculture.IHiveDrop;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary.Type;
import tsuteto.tofu.init.TcItems;

import java.util.ArrayList;
import java.util.Iterator;

public enum TFHiveType
{
    KINU("kinu", 12, true);

    public final String name;
    public final boolean show;
    private final int lightLevel;
    private ArrayList<IHiveDrop> drops;
    private ArrayList<Type> validBiomes;
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    static void registerBeehiveDrops()
    {
        KINU.addDrop((new HiveDrop(TFBeeGenomeManager.getTemplateKinu(), new ItemStack[] {new ItemStack(TcItems.tofuKinu, 4)}, 80)).setIgnobleShare(0.7F));
    }

    private TFHiveType(String hiveName, int light, boolean visible)
    {
        this.name = hiveName;
        this.lightLevel = light;
        this.show = visible;
        this.drops = Lists.newArrayList();
        this.validBiomes = Lists.newArrayList();
    }

    public static TFHiveType getHiveFromMeta(int meta)
    {
        TFHiveType type = KINU;

        if (meta > 0 && meta < values().length)
        {
            type = values()[meta];
        }

        return type;
    }

    public void addDrop(IHiveDrop drop)
    {
        this.drops.add(drop);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconForSide(int side)
    {
        IIcon i = this.icons[0];

        if (side != 0 && side != 1)
        {
            i = this.icons[1];
        }

        return i;
    }

    public int getLightValue()
    {
        return this.lightLevel;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int fortune)
    {
        ArrayList hiveDrops = new ArrayList();
        int throttle = 0;
        int dart;
        Iterator i$;
        IHiveDrop drop;

        while (hiveDrops.size() <= 0 && throttle < 10)
        {
            ++throttle;
            dart = world.rand.nextInt(100);
            i$ = this.drops.iterator();

            while (i$.hasNext())
            {
                drop = (IHiveDrop)i$.next();

                if (dart <= drop.getChance(world, x, y, z))
                {
                    hiveDrops.add(drop.getPrincess(world, x, y, z, fortune));
                    break;
                }
            }
        }

        dart = world.rand.nextInt(100);
        i$ = this.drops.iterator();

        while (i$.hasNext())
        {
            drop = (IHiveDrop)i$.next();

            if (dart <= drop.getChance(world, x, y, z))
            {
                hiveDrops.addAll(drop.getDrones(world, x, y, z, fortune));
                break;
            }
        }

        dart = world.rand.nextInt(100);
        i$ = this.drops.iterator();

        while (i$.hasNext())
        {
            drop = (IHiveDrop)i$.next();

            if (dart <= drop.getChance(world, x, y, z))
            {
                hiveDrops.addAll(drop.getAdditional(world, x, y, z, fortune));
            }
        }

        return hiveDrops;
    }

    @SideOnly(Side.CLIENT)
    public static void registerIcons(IIconRegister register)
    {
        TFHiveType[] arr$ = values();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$)
        {
            TFHiveType type = arr$[i$];
            type.icons = new IIcon[2];
            type.icons[0] = register.registerIcon("tofufactory".toLowerCase() + ":beehive." + type.ordinal() + ".top");
            type.icons[1] = register.registerIcon("tofufactory".toLowerCase() + ":beehive." + type.ordinal() + ".side");
        }
    }
}
