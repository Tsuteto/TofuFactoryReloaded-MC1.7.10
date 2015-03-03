package tsuteto.tofufactory.block;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import tsuteto.tofu.block.BlockTfMachineBase;
import tsuteto.tofufactory.core.TofuFactory;
import tsuteto.tofufactory.entity.EntitySmokeTofuFX;
import tsuteto.tofufactory.proxy.TFIconTexture;
import tsuteto.tofufactory.tileentity.TileEntityFactoryTfMachine;

import java.util.Random;

public abstract class BlockTofuMachine extends BlockTfMachineBase
{
    private static boolean keepTofuMachineInventory = false;

    public BlockTofuMachine(boolean par2)
    {
        super(par2);
        if (!par2)
        {
            this.setCreativeTab(TofuFactory.tabsTofuFactory);
        }
        else
        {
            this.setLightLevel(0.875F);
        }
    }

    @Override
    protected String getFrontIconIdle()
    {
        return TofuFactory.resourceDomain + "machine/" + this.textureName + "_front_off";
    }

    @Override
    protected String getFrontIconActive()
    {
        return TofuFactory.resourceDomain + "machine/" + this.textureName + "_front_on";
    }

//    public boolean isHalloween()
//    {
//        Calendar cal1 = Calendar.getInstance();
//        int month = cal1.get(2) + 1;
//        int day = cal1.get(5);
//        return month == 10 && day == 31;
//    }

//    @SideOnly(Side.CLIENT)
//    @Override
//    public void registerBlockIcons(IIconRegister par1IconRegister)
//    {
//        String d = "";
//        String m = TofuFactory.resourceDomain + "machine/";
//
//        if (this.isHalloween())
//        {
//            d = "Halloween/";
//        }
//
//        IIcon sideIcon = par1IconRegister.registerIcon(TofuCraftCore.resourceDomain + "tfMachine_side");
//        this.blockIcon = sideIcon;
//        this.TofuMachineIconTop = sideIcon;
//        this.TofuMachineIconFront = par1IconRegister.registerIcon(this.isActive ? m + d + this.textureName + "_front_on" : m + d + this.textureName + "_front_off");
//        this.TofuMachineIconUnder = sideIcon;
//    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {
            return true;
        }
        else
        {
            TileEntityFactoryTfMachine tileentityfurnace = (TileEntityFactoryTfMachine)par1World.getTileEntity(par2, par3, par4);

            if (tileentityfurnace != null)
            {
                this.displayGUIBook(par5EntityPlayer, par1World, par2, par3, par4);
            }

            return true;
        }
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (this.isActive)
        {
            for (int i = 0; i < this.machineRand.nextInt(3) + 1; i++)
            {
                double r = 0.8D + this.machineRand.nextDouble();
                double t = this.machineRand.nextDouble() * 2.0D * Math.PI;
                double d0 = (double) par2 + 0.5D;
                double d1 = (double) par3 + 0.3D + this.machineRand.nextDouble();
                double d2 = (double) par4 + 0.5D;
                double d3 = Math.sin(t) / 64.0D;
                double d4 = 0.05D + this.machineRand.nextDouble() * 0.05D;
                double d5 = Math.cos(t) / 64.0D;
                EntitySmokeTofuFX entityFX = new EntitySmokeTofuFX(par1World, d0, d1, d2, d3, d4, d5);
                IIcon smokeIcon = TFIconTexture.smokeTofu;

//            if (this.isHalloween())
//            {
//                smokeIcon = TFIconTexture.smokeTofuGlow;
//            }

                entityFX.setParticleIcon(smokeIcon);
                FMLClientHandler.instance().getClient().effectRenderer.addEffect(entityFX);
            }
        }
    }

    public void updateMachineState(boolean par0, World par1World, int par2, int par3, int par4)
    {
        int l = par1World.getBlockMetadata(par2, par3, par4);
        TileEntity tileentity = par1World.getTileEntity(par2, par3, par4);
        keepTofuMachineInventory = true;

        if (par0)
        {
            par1World.setBlock(par2, par3, par4, this.getBlockActive());
        }
        else
        {
            par1World.setBlock(par2, par3, par4, this.getBlockIdle());
        }

        keepTofuMachineInventory = false;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);

        if (tileentity != null)
        {
            tileentity.validate();
            par1World.setTileEntity(par2, par3, par4, tileentity);
        }
    }

    public abstract void displayGUIBook(EntityPlayer var1, World var2, int var3, int var4, int var5);
}
