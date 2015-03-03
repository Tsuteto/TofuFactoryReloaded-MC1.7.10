package tsuteto.tofufactory.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import tsuteto.tofu.api.tileentity.ITfConsumer;
import tsuteto.tofu.api.tileentity.TileEntityTfMachineSidedInventoryBase;
import tsuteto.tofu.item.ItemTcMaterials;
import tsuteto.tofufactory.api.recipes.IMachineRecipe;
import tsuteto.tofufactory.block.BlockTofuMachine;
import tsuteto.tofufactory.core.TofuFactory;

public abstract class TileEntityFactoryTfMachine extends TileEntityTfMachineSidedInventoryBase implements ITfConsumer
{
    public static final int SLOT_ITEM_INPUT = 0;
    public static final int SLOT_ITEM_RESULT = 1;
    public static final int SLOT_ACCELERATION = 2;
    public static final int SLOT_BATTERY = 3;

    private static final int[] slots_top = new int[] {0};
    private static final int[] slots_bottom = new int[] {1};
    private static final int[] slots_sides = new int[] {1};

    public static final double COST_TF_PER_TICK = 0.025D;
    public static final double TF_CAPACITY = 20.0D;
    public static final int WHOLE_COOK_TIME_BASE = 200;

    public int procTime;
    public int wholeProcTime;
    public double tfPooled;
    private ItemStack lastInputItem = null;
    private boolean isTfNeeded;
    public boolean isWorking;
    private boolean prevWorking;

    public TileEntityFactoryTfMachine()
    {
        this.itemStacks = new ItemStack[3];
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        this.procTime = nbtTagCompound.getShort("ProcTime");
        this.wholeProcTime = nbtTagCompound.getShort("ProcTimeW");
        this.tfPooled = nbtTagCompound.getFloat("TfP");
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setShort("ProcTime", (short) this.procTime);
        nbtTagCompound.setShort("ProcTimeW", (short) this.wholeProcTime);
        nbtTagCompound.setFloat("TfP", (float) this.tfPooled);
    }

    @SideOnly(Side.CLIENT)
    public double getCookProgressScaled()
    {
        return (double)this.procTime / (double) wholeProcTime;
    }

    @SideOnly(Side.CLIENT)
    public double getPowerScaled()
    {
        return this.tfPooled / TF_CAPACITY;
    }

    public boolean isWorking()
    {
        return this.isWorking;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        boolean updated = false;
        this.isWorking = false;

        if (!this.worldObj.isRemote)
        {
            if (this.canSmelt())
            {
                this.wholeProcTime = this.getWholeProcTime();

                // Cooking
                ++this.procTime;
                this.tfPooled -= this.getTfAmountNeeded();
                this.isWorking = true;

                if (this.procTime >= wholeProcTime)
                {
                    // Finish
                    this.procTime = 0;
                    this.smeltItem();
                    updated = true;
                }
            }
            else
            {
                // Stop cooking
                if (lastInputItem != this.itemStacks[SLOT_ITEM_INPUT])
                {
                    this.procTime = 0;
                }
            }

            if (this.isWorking != this.prevWorking)
            {
                updated = true;
                this.getMachineBlock().updateMachineState(isWorking(), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }

        }

        if (updated)
        {
            this.markDirty();
        }

        this.lastInputItem = itemStacks[SLOT_ITEM_INPUT];
        this.prevWorking = this.isWorking;
    }

    public boolean isAccelerated()
    {
        ItemStack itemStack = this.itemStacks[SLOT_ACCELERATION];
        return ItemTcMaterials.activatedHellTofu.isItemEqual(itemStack);
    }

    private int getWholeProcTime()
    {
        if (this.isAccelerated())
        {
            ItemStack itemStack = this.itemStacks[SLOT_ACCELERATION];
            return WHOLE_COOK_TIME_BASE / itemStack.stackSize;
        }
        return WHOLE_COOK_TIME_BASE;
    }

    private double getTfAmountNeeded()
    {
        if (this.isAccelerated())
        {
            ItemStack itemStack = this.itemStacks[SLOT_ACCELERATION];
            return 5.0D / (double) this.getWholeProcTime() + COST_TF_PER_TICK / 10.0D * Math.pow(1.1, itemStack.stackSize);
        }
        return COST_TF_PER_TICK;
    }

    protected boolean canSmelt()
    {
        isTfNeeded = false;
        if (this.itemStacks[SLOT_ITEM_INPUT] != null && this.worldObj.isAirBlock(this.xCoord, this.yCoord + 1, this.zCoord))
        {
            ItemStack itemstack = this.getMachineRecipe().getProcessingResult(this.itemStacks[0]);

            if (itemstack != null)
            {
                if (this.itemStacks[SLOT_ITEM_RESULT] != null)
                {
                    if (!this.itemStacks[SLOT_ITEM_RESULT].isItemEqual(itemstack)) return false;

                    int result = itemStacks[SLOT_ITEM_RESULT].stackSize + itemstack.stackSize;
                    if (result > getInventoryStackLimit() || result > this.itemStacks[SLOT_ITEM_RESULT].getMaxStackSize())
                    {
                        return false;
                    }

                }
                isTfNeeded = true;
                return tfPooled >= this.getTfAmountNeeded();
            }
        }
        return false;
    }

    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = this.getMachineRecipe().getProcessingResult(this.itemStacks[0]);

            if (this.itemStacks[SLOT_ITEM_RESULT] == null)
            {
                this.itemStacks[SLOT_ITEM_RESULT] = itemstack.copy();
            }
            else if (this.itemStacks[SLOT_ITEM_RESULT].getItem() == itemstack.getItem())
            {
                this.itemStacks[SLOT_ITEM_RESULT].stackSize += itemstack.stackSize;
            }

            --this.itemStacks[SLOT_ITEM_INPUT].stackSize;

            if (this.itemStacks[SLOT_ITEM_INPUT].stackSize <= 0)
            {
                this.itemStacks[SLOT_ITEM_INPUT] = null;
            }
        }
    }

    @Override
    public double getMaxTfCapacity()
    {
        return TF_CAPACITY - this.tfPooled;
    }

    @Override
    public double getCurrentTfConsumed()
    {
        return isTfNeeded ? this.getTfAmountNeeded() : 0;
    }

    @Override
    public void chargeTf(double amount)
    {
        this.tfPooled += amount;
    }

    @Override
    public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
    {
        return par1 != SLOT_ITEM_RESULT;
    }

    /**
     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     * block.
     */
    public int[] getAccessibleSlotsFromSide(int par1)
    {
        return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canExtractItem(int slot, ItemStack item, int side)
    {
        return slot == SLOT_ITEM_RESULT;
    }

    public abstract BlockTofuMachine getMachineBlock();

    public abstract IMachineRecipe getMachineRecipe();
}
