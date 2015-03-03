package tsuteto.tofufactory.gui;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import tsuteto.tofu.gui.GuiTfMachineBase;
import tsuteto.tofu.gui.guiparts.GuiPartGaugeH;
import tsuteto.tofu.gui.guiparts.GuiPartTfCharge;
import tsuteto.tofu.gui.guiparts.TfMachineGuiParts;
import tsuteto.tofufactory.tileentity.TileEntityFactoryTfMachine;

public abstract class GuiFactoryMachine extends GuiTfMachineBase
{
    public static final ResourceLocation factoryMachineTexture = new ResourceLocation("tofufactory", "textures/gui/machine.png");
    private final TileEntityFactoryTfMachine machineInventory;

    private GuiPartGaugeH progress;
    private GuiPartTfCharge tfCharge;

//    private GuiPartGaugeH heaterLeft  = new GuiPartGaugeH(31, 32, TfMachineGuiParts.heaterBgLeft, TfMachineGuiParts.heaterLeft);
//    private GuiPartGaugeRevH heaterRight = new GuiPartGaugeRevH(67, 32, TfMachineGuiParts.heaterBgRight, TfMachineGuiParts.heaterRight);

    public GuiFactoryMachine(ContainerFactoryMachine par1ContainerFactoryMachine, TileEntityFactoryTfMachine par2TileEntityTofuMachine,
                             TfMachineGuiParts progressBg, TfMachineGuiParts progressFg)
    {
        super(par1ContainerFactoryMachine);
        this.ySize = 180;
        this.machineInventory = par2TileEntityTofuMachine;
        this.progress = new GuiPartGaugeH(77, 31, progressBg, progressFg);
        this.tfCharge = new GuiPartTfCharge(54, 53, TileEntityFactoryTfMachine.COST_TF_PER_TICK / TileEntityFactoryTfMachine.TF_CAPACITY);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String s = this.machineInventory.hasCustomInventoryName() ? this.machineInventory.getInventoryName() : I18n.format(this.machineInventory.getInventoryName());
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);

        this.tfCharge.showInfoTip(this, par1, par2);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.bindStandardTexture();

        this.drawStandardBasePanel();
        this.drawTfMachineSlot();
        this.tfCharge
                .setPercentage(machineInventory.tfPooled / TileEntityFactoryTfMachine.TF_CAPACITY)
                .draw(this);

        this.bindTexture(factoryMachineTexture);

        this.progress
                .setPercentage(machineInventory.getCookProgressScaled())
                .draw(this);
    }
}
