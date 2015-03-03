package tsuteto.tofufactory.item;

import com.google.common.collect.Multimap;
import forestry.api.arboriculture.IToolGrafter;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class ItemTFGrafter extends ItemTofuFactory implements IToolGrafter
{
    private float efficiencyOnProperMaterial;

    public ItemTFGrafter(int maxDamage)
    {
        super();
        this.setMaxStackSize(1);
        this.efficiencyOnProperMaterial = 4.0F;
        this.setMaxDamage(maxDamage);
    }

    @Override
    public float func_150893_a(ItemStack itemstack, Block block)
    {
        return 1.0F;
    }

    @Override
    public float getDigSpeed(ItemStack itemstack, Block block, int md)
    {
        return ForgeHooks.isToolEffective(itemstack, block, md) ? this.efficiencyOnProperMaterial : this.func_150893_a(itemstack, block);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack itemstack, World world, Block i, int j, int k, int l, EntityLivingBase entityliving)
    {
        return true;
    }

    @Override
    public Multimap getAttributeModifiers(ItemStack stack)
    {
        Multimap multimap = super.getAttributeModifiers(stack);
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Tool modifier", (double)1.0F, 0));
        return multimap;
    }

    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    @Override
    public boolean isFull3D()
    {
        return true;
    }

    @Override
    public float getSaplingModifier(ItemStack stack, World world, EntityPlayer player, int x, int y, int z)
    {
        return 100.0F;
    }
}
