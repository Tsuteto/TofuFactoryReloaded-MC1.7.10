package tsuteto.tofufactory.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import tsuteto.tofufactory.core.TFItems;
import tsuteto.tofufactory.core.TofuFactory;
import tsuteto.tofufactory.core.TofuFactory;

public class ItemTFMaterial extends ItemSetBase
{
    public static final Info magicCircuit = new Info(0, "magicCircuit");
    public static final Info magicCircuitAdv = new Info(1, "magicCircuitAdv");
    public static final Info ringSun = new Info(2, "ringSun");
    public static final Info ringMoon = new Info(3, "ringMoon");
    public static final Info tofuBlade = new Info(4, "tofuBlade");

    public static final Info[] tofuMaterials = new Info[] {magicCircuit, magicCircuitAdv, ringSun, ringMoon, tofuBlade};

    public ItemTFMaterial()
    {
        super(tofuMaterials);
    }

    @Override
    public boolean isDamageable()
    {
        return false;
    }

    @Override
    public boolean isRepairable()
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register)
    {
        this.icons = new IIcon[this.info.length];

        for (int i = 0; i < this.info.length; ++i)
        {
            String name = this.info[i].name;
            this.icons[i] = register.registerIcon(TofuFactory.resourceDomain + "material/" + name);
        }
    }

    public static class Info extends ItemSetInfo
    {
        public Info(int id, String name)
        {
            super(id, name);
        }

        @Override
        protected Item getItemInstance()
        {
            return TFItems.tofuMaterial;
        }
    }

}
