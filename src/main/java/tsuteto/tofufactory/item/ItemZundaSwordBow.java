package tsuteto.tofufactory.item;

import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import tsuteto.tofu.data.DataType;
import tsuteto.tofu.data.EntityInfo;
import tsuteto.tofu.item.ItemZundaBow;
import tsuteto.tofu.item.TcItems;
import tsuteto.tofu.network.PacketDispatcher;
import tsuteto.tofu.network.packet.PacketZundaArrowType;
import tsuteto.tofufactory.core.TofuFactory;
import tsuteto.tofufactory.integration.plugins.PluginIC2;

public class ItemZundaSwordBow extends ItemZundaBow implements IElectricItem
{
    private final float weaponDamage;
    private final ToolMaterial toolMaterial;

    public ItemZundaSwordBow(ToolMaterial par2EnumToolMaterial)
    {
        super();
        this.toolMaterial = par2EnumToolMaterial;
        this.maxStackSize = 1;
        this.setMaxDamage(par2EnumToolMaterial.getMaxUses());
        this.setCreativeTab(TofuFactory.tabsTofuFactory);
        this.weaponDamage = 4.0F + par2EnumToolMaterial.getDamageVsEntity();
    }

    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
        if (ElectricItem.manager.canUse(par1ItemStack, 1))
        {
            par1ItemStack.damageItem(-1, par3EntityPlayer);
            super.onPlayerStoppedUsing(par1ItemStack, par2World, par3EntityPlayer, par4);
            ElectricItem.manager.use(par1ItemStack, 1, par3EntityPlayer);
        }
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
    {
        ElectricItem.manager.use(par1ItemStack, 1, par3EntityLivingBase);
        return true;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }

    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
        if (usingItem != null)
        {
            EnumArrowType arrowType = EntityInfo.instance().get(player.getEntityId(), DataType.ZundaArrowType);
            if (arrowType == null) arrowType = EnumArrowType.NORMAL;

            int k = usingItem.getMaxItemUseDuration() - useRemaining;
            if (k >= 18)
            {
                return arrowType.icons[2];
            }

            if (k > 13)
            {
                return arrowType.icons[1];
            }

            if (k > 0)
            {
                return arrowType.icons[0];
            }
        }

        return this.getIconIndex(stack);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(this.getIconString());

        for (EnumArrowType type : EnumArrowType.values())
        {
            type.icons = new IIcon[iconNameSuffix.length];

            for (int i = 0; i < type.icons.length; ++i)
            {
                type.icons[i] = par1IconRegister.registerIcon(
                        String.format(TofuFactory.resourceDomain + "zunda_swordBow_%s_%s", type.toString().toLowerCase(), iconNameSuffix[i]));
            }
        }
    }

    @Override
    public Multimap getAttributeModifiers(ItemStack itemStack)
    {
        Multimap multimap = super.getAttributeModifiers(itemStack);
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.weaponDamage, 0));
        return multimap;
    }

    public CreativeTabs[] getCreativeTabs()
    {
        return new CreativeTabs[] {this.getCreativeTab()};
    }

    public boolean canProvideEnergy(ItemStack itemStack)
    {
        return false;
    }

    public Item getChargedItem(ItemStack itemStack)
    {
        return PluginIC2.zundaSwordBow;
    }

    public Item getEmptyItem(ItemStack itemStack)
    {
        return PluginIC2.zundaSwordBowEmpty;
    }

    public double getMaxCharge(ItemStack itemStack)
    {
        return this.getMaxDamage(itemStack);
    }

    public int getTier(ItemStack itemStack)
    {
        return 1;
    }

    public double getTransferLimit(ItemStack itemStack)
    {
        return 300;
    }
}
