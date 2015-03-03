package tsuteto.tofufactory.tileentity;

import buildcraft.api.tiles.IHasWork;
import buildcraft.core.RFBattery;
import cofh.api.energy.IEnergyHandler;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileEntityMJMachine extends TileEntityLegacyMachine implements IHasWork, IEnergyHandler
{
    private RFBattery battery;
    public int MJTime;

    public TileEntityMJMachine()
    {
        battery = new RFBattery(1500, 80, 20);
    }

    public RFBattery getBattery(ForgeDirection side)
    {
        return this.battery;
    }

    public boolean hasWork()
    {
        if (this.TofuMachineRunningTime <= 2000 && this.MJTime <= 2000)
        {
            float i = battery.useEnergy(20, 80, true);
            this.MJTime = (int)((float)this.MJTime + i * 3.0F);
            //System.out.println(i + "aaaaaaaaaaaaaaaaaa");
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean canConnectEnergy(ForgeDirection from) {
        return this.battery != null;
    }
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return this.battery != null && this.canConnectEnergy(from)?this.battery.receiveEnergy(maxReceive, simulate):0;
    }

    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return this.battery != null && this.canConnectEnergy(from)?this.battery.extractEnergy(maxExtract, simulate):0;
    }

    public int getEnergyStored(ForgeDirection from) {
        return this.battery != null && this.canConnectEnergy(from)?this.battery.getEnergyStored():0;
    }

    public int getMaxEnergyStored(ForgeDirection from) {
        return this.battery != null && this.canConnectEnergy(from)?this.battery.getMaxEnergyStored():0;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        boolean flag = this.TofuMachineRunningTime > 0;
        boolean flag1 = false;

        if (this.TofuMachineRunningTime > 0)
        {
            --this.TofuMachineRunningTime;
        }

        if (!this.worldObj.isAirBlock(this.xCoord, this.yCoord + 1, this.zCoord))
        {
            this.TofuMachineRunningTime = 0;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.TofuMachineRunningTime == 0 && this.canSmelt())
            {
                this.currentItemBurnTime = this.TofuMachineRunningTime;

                if (this.TofuMachineRunningTime <= 2000 && this.MJTime >= 200 && this.worldObj.isAirBlock(this.xCoord, this.yCoord + 1, this.zCoord))
                {
                    this.currentItemBurnTime = this.TofuMachineRunningTime = this.MJTime;
                    this.MJTime = 0;
                }

                if (this.TofuMachineRunningTime > 0)
                {
                    flag1 = true;

                    if (this.TofuMachineItemStacks[1] != null)
                    {
                        --this.TofuMachineItemStacks[1].stackSize;

                        if (this.TofuMachineItemStacks[1].stackSize == 0)
                        {
                            this.TofuMachineItemStacks[1] = this.TofuMachineItemStacks[1].getItem().getContainerItem(this.TofuMachineItemStacks[1]);
                        }
                    }
                }
            }

            if (this.isBurning() && this.canSmelt())
            {
                ++this.TofuMachineProcessingTime;

                if (this.TofuMachineProcessingTime == 200)
                {
                    this.TofuMachineProcessingTime = 0;
                    this.smeltItem();
                    flag1 = true;
                }
            }
            else
            {
                this.TofuMachineProcessingTime = 0;
            }

            flag1 = true;
            this.getMachineBlock().updateMachineState(this.TofuMachineRunningTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
        }

        if (flag1)
        {
            this.markDirty();
        }
    }

    public World getWorld()
    {
        return this.worldObj;
    }
}
