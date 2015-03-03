package tsuteto.tofufactory.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import tsuteto.tofu.api.tileentity.ContainerTfMachine;
import tsuteto.tofu.api.tileentity.SlotTfMachine;
import tsuteto.tofu.gui.guiparts.TfMachineGuiParts;
import tsuteto.tofu.network.packet.PacketTfMachineData;
import tsuteto.tofu.tileentity.SlotTfOvenAccelerator;
import tsuteto.tofu.tileentity.SlotTfOvenResult;
import tsuteto.tofufactory.api.recipes.IMachineRecipe;
import tsuteto.tofufactory.tileentity.TileEntityFactoryTfMachine;

public abstract class ContainerFactoryMachine extends ContainerTfMachine<TileEntityFactoryTfMachine>
{
    private int lastCookTime;
    private double lastTfPooled = 0;
    private int lastWholeCookTime;
    private boolean lastWorking = false;

    public ContainerFactoryMachine(InventoryPlayer invPlayer, TileEntityFactoryTfMachine machine)
    {
        super(machine);

        this.addSlotToContainer(new SlotTfMachine(machine, TileEntityFactoryTfMachine.SLOT_ITEM_INPUT, 51, 30, TfMachineGuiParts.itemSlotL1));
        this.addSlotToContainer(new SlotTfOvenResult(invPlayer.player, machine, TileEntityFactoryTfMachine.SLOT_ITEM_RESULT, 105, 28, TfMachineGuiParts.itemSlotL2));
        this.addSlotToContainer(new SlotTfOvenAccelerator(invPlayer.player, machine, TileEntityFactoryTfMachine.SLOT_ACCELERATION, 80, 53, TfMachineGuiParts.itemSlot));

        this.preparePlayerInventory(invPlayer);
    }

    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.machine.procTime);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.machine.wholeProcTime);
        this.sendTfMachineData(par1ICrafting, 0, new PacketTfMachineData.DataHandler() {

            @Override
            public void addData(ByteBuf buffer)
            {
                buffer.writeDouble(machine.tfPooled);
            }
        });
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookTime != this.machine.procTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.machine.procTime);
            }

            if (this.lastWholeCookTime != this.machine.wholeProcTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.machine.wholeProcTime);
            }
            if (this.lastTfPooled != this.machine.tfPooled)
            {
                this.sendTfMachineData(icrafting, 0, new PacketTfMachineData.DataHandler() {

                    @Override
                    public void addData(ByteBuf buffer)
                    {
                        buffer.writeDouble(machine.tfPooled);
                    }
                });
            }
            if (this.lastWorking != this.machine.isWorking)
            {
                this.sendTfMachineData(icrafting, 1, new PacketTfMachineData.DataHandler() {

                    @Override
                    public void addData(ByteBuf buffer)
                    {
                        buffer.writeBoolean(machine.isWorking);
                    }
                });
            }
        }

        this.lastCookTime = this.machine.procTime;
        this.lastWholeCookTime = this.machine.wholeProcTime;
        this.lastTfPooled = this.machine.tfPooled;
        this.lastWorking = this.machine.isWorking;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.machine.procTime = par2;
        }

        if (par1 == 1)
        {
            this.machine.wholeProcTime = par2;
        }
    }

    @Override
    public void updateTfMachineData(int id, ByteBuf data)
    {

        if (id == 0)
        {
            this.machine.tfPooled = data.readDouble();
        }
        if (id == 1)
        {
            this.machine.isWorking = data.readBoolean();
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.machine.isUseableByPlayer(par1EntityPlayer);
    }

    public ContainerTfMachine.TransferResult transferStackInMachineSlot(EntityPlayer player, int slotId, ItemStack itemStack)
    {
        if (this.getMachineRecipe().getProcessingResult(itemStack) != null)
        {
            if (!this.mergeToSingleItemStack(itemStack, TileEntityFactoryTfMachine.SLOT_ITEM_INPUT))
            {
                return ContainerTfMachine.TransferResult.MISMATCHED;
            }
            return ContainerTfMachine.TransferResult.MATCHED;
        }
        return ContainerTfMachine.TransferResult.SKIPPING;
    }

    public abstract IMachineRecipe getMachineRecipe();

    public abstract SlotTofuMachine getMachineSlot(EntityPlayer var1, TileEntityFactoryTfMachine var2, int var3, int var4, int var5);
}
