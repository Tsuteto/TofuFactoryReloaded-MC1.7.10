package tsuteto.tofufactory.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.Constants;
import tsuteto.tofu.block.BlockTofuBase;
import tsuteto.tofu.item.ItemTcFood;
import tsuteto.tofufactory.api.recipes.IMachineRecipe;
import tsuteto.tofufactory.block.BlockTofuMachine;

public abstract class TileEntityLegacyMachine extends TileEntity implements ISidedInventory
{
    private static final int[] slots_top = new int[] {0};
    private static final int[] slots_bottom = new int[] {2, 1};
    private static final int[] slots_sides = new int[] {1};
    protected ItemStack[] TofuMachineItemStacks = new ItemStack[3];
    public int TofuMachineRunningTime;
    public int currentItemBurnTime;
    public int TofuMachineProcessingTime;
    private String field_94130_e;

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.TofuMachineItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int par1)
    {
        return this.TofuMachineItemStacks[par1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.TofuMachineItemStacks[par1] != null)
        {
            ItemStack itemstack;

            if (this.TofuMachineItemStacks[par1].stackSize <= par2)
            {
                itemstack = this.TofuMachineItemStacks[par1];
                this.TofuMachineItemStacks[par1] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.TofuMachineItemStacks[par1].splitStack(par2);

                if (this.TofuMachineItemStacks[par1].stackSize == 0)
                {
                    this.TofuMachineItemStacks[par1] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.TofuMachineItemStacks[par1] != null)
        {
            ItemStack itemstack = this.TofuMachineItemStacks[par1];
            this.TofuMachineItemStacks[par1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.TofuMachineItemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return this.field_94130_e != null;
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInventoryName()
    {
        return this.isInvNameLocalized() ? this.field_94130_e : StatCollector.translateToLocal(this.getMachineName());
    }

    /**
     * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
     * language. Otherwise it will be used directly.
     */
    public boolean isInvNameLocalized()
    {
        return this.field_94130_e != null && this.field_94130_e.length() > 0;
    }

    public void setGuiDisplayName(String par1Str)
    {
        this.field_94130_e = par1Str;
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        this.TofuMachineItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.TofuMachineItemStacks.length)
            {
                this.TofuMachineItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.TofuMachineRunningTime = par1NBTTagCompound.getShort("BurnTime");
        this.TofuMachineProcessingTime = par1NBTTagCompound.getShort("CookTime");
        this.currentItemBurnTime = getItemBurnTime(this.TofuMachineItemStacks[1]);

        if (par1NBTTagCompound.hasKey("CustomName"))
        {
            this.field_94130_e = par1NBTTagCompound.getString("CustomName");
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("BurnTime", (short)this.TofuMachineRunningTime);
        par1NBTTagCompound.setShort("CookTime", (short)this.TofuMachineProcessingTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.TofuMachineItemStacks.length; ++i)
        {
            if (this.TofuMachineItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.TofuMachineItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);

        if (this.isInvNameLocalized())
        {
            par1NBTTagCompound.setString("CustomName", this.field_94130_e);
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int par1)
    {
        return this.TofuMachineProcessingTime * par1 / 200;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int par1)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 200;
        }

        return this.TofuMachineRunningTime * par1 / this.currentItemBurnTime;
    }

    public boolean isBurning()
    {
        return this.TofuMachineRunningTime > 0;
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
                this.currentItemBurnTime = this.TofuMachineRunningTime = getItemBurnTime(this.TofuMachineItemStacks[1]);

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

            if (flag != this.TofuMachineRunningTime > 0)
            {
                flag1 = true;
                this.getMachineBlock().updateMachineState(this.TofuMachineRunningTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
    }

    protected boolean canSmelt()
    {
        if (this.TofuMachineItemStacks[0] != null && this.worldObj.isAirBlock(this.xCoord, this.yCoord + 1, this.zCoord))
        {
            ItemStack itemstack = this.getMachineRecipe().getProcessingResult(this.TofuMachineItemStacks[0]);

            if (itemstack == null)
            {
                return false;
            }
            else if (this.TofuMachineItemStacks[2] == null)
            {
                return true;
            }
            else if (!this.TofuMachineItemStacks[2].isItemEqual(itemstack))
            {
                return false;
            }
            else
            {
                int result = this.TofuMachineItemStacks[2].stackSize + itemstack.stackSize;
                return result <= this.getInventoryStackLimit() && result <= itemstack.getMaxStackSize();
            }
        }
        else
        {
            return false;
        }
    }

    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = this.getMachineRecipe().getProcessingResult(this.TofuMachineItemStacks[0]);

            if (this.TofuMachineItemStacks[2] == null)
            {
                this.TofuMachineItemStacks[2] = itemstack.copy();
            }
            else if (this.TofuMachineItemStacks[2].isItemEqual(itemstack))
            {
                this.TofuMachineItemStacks[2].stackSize += itemstack.stackSize;
            }

            --this.TofuMachineItemStacks[0].stackSize;

            if (this.TofuMachineItemStacks[0].stackSize <= 0)
            {
                this.TofuMachineItemStacks[0] = null;
            }
        }
    }

    public static int getItemBurnTime(ItemStack par0ItemStack)
    {
        if (par0ItemStack == null)
        {
            return 0;
        }
        else
        {
            Item item = par0ItemStack.getItem();

            if (item instanceof ItemBlock)
            {
                Block block = Block.getBlockFromItem(item);

                if (block instanceof BlockTofuBase)
                {
                    return 2000;
                }
            }

            return item instanceof ItemTcFood ? 200 : 0;
        }
    }

    public static boolean isItemFuel(ItemStack par0ItemStack)
    {
        return getItemBurnTime(par0ItemStack) > 0;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this
                && par1EntityPlayer.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory()
    {

    }

    @Override
    public void closeInventory()
    {

    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
    {
        return par1 == 2 ? false : (par1 == 1 ? isItemFuel(par2ItemStack) : true);
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
     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
    {
        return this.isItemValidForSlot(par1, par2ItemStack);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
    {
        return par3 != 0 || par1 != 1 || par2ItemStack.getItem() == Items.bucket;
    }

    public abstract String getMachineName();

    public abstract BlockTofuMachine getMachineBlock();

    public abstract IMachineRecipe getMachineRecipe();
}
