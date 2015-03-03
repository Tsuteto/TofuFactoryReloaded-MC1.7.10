package tsuteto.tofufactory.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestry.api.core.Tabs;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tsuteto.tofufactory.bee.MaterialTFBeehive;
import tsuteto.tofufactory.bee.TFHiveType;
import tsuteto.tofufactory.bee.MaterialTFBeehive;
import tsuteto.tofufactory.bee.TFHiveType;

public class BlockTFHive extends Block
{
    public BlockTFHive()
    {
        super(new MaterialTFBeehive(true));
        this.setLightLevel(0.8F);
        this.setHardness(1.0F);
        this.setCreativeTab(Tabs.tabApiculture);
    }

    @Override
    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity)
    {
        return !(entity instanceof EntityDragon);
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if (meta < 0 || meta > TFHiveType.values().length)
        {
            meta = 0;
        }

        return TFHiveType.getHiveFromMeta(meta).getLightValue();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item id, CreativeTabs tab, List itemsList)
    {
        for (TFHiveType type : TFHiveType.values())
        {
            if (type.show)
            {
                itemsList.add(new ItemStack(this, 1, type.ordinal()));
            }
        }
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        return TFHiveType.getHiveFromMeta(metadata).getDrops(world, x, y, z, fortune);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        TFHiveType.registerIcons(register);
    }


    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (meta < 0 || meta > TFHiveType.values().length)
        {
            meta = 0;
        }

        return TFHiveType.getHiveFromMeta(meta).getIconForSide(side);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        Block block = world.getBlock(x, y, z);
        return new ItemStack(block, 1, this.getDamageValue(world, x, y, z) & 7);
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }
}
