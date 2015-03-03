package tsuteto.tofufactory.block;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import tsuteto.tofufactory.core.TFItems;
import tsuteto.tofufactory.item.ItemMaterialInfo;

public class BlockTofuMetal extends BlockSetColored
{
    public static final Info tofuSteel = new Info(TFItems.tofuSteel, 0x7BB2D0);
    public static final Info mithrilTofu = new Info(TFItems.mithrilTofu, 0xF4F4F4);
    public static final Info sol = new Info(TFItems.sol, 0xE58557);
    public static final Info luna = new Info(TFItems.luna, 0xCCAD0D);
    public static final Info[] blocks = new Info[]{tofuSteel, mithrilTofu, sol, luna};

    public BlockTofuMetal(Material p_i45394_1_)
    {
        super(p_i45394_1_, blocks);
    }


    public static class Info extends ItemMaterialInfo implements IBlockColorInfo
    {
        private int color;

        public Info(TFItems.CommonMaterial m, int color)
        {
            super(m);
            this.color = color;
        }

        @Override
        protected Item getItemInstance()
        {
            return Item.getItemFromBlock(TFItems.tofuMetalBlock);
        }

        @Override
        public int getRenderColor()
        {
            return color;
        }
    }
}
