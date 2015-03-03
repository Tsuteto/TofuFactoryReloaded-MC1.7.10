package tsuteto.tofufactory.gui;

import tsuteto.tofu.gui.guiparts.TfMachineGuiParts;

public class FactoryMachineGuiParts
{
    private static int counter = 0;
    public static final TfMachineGuiParts progressPulverizer = guiPart(0, 0, 24, 17);
    public static final TfMachineGuiParts progressPulverizerBg = guiPart(24, 0, 24, 17);
    public static final TfMachineGuiParts progressCuttingMachine = guiPart(0, 17, 24, 17);
    public static final TfMachineGuiParts progressCuttingMachineBg = guiPart(24, 17, 24, 17);
    public static final TfMachineGuiParts progressCompactor = guiPart(0, 34, 24, 17);
    public static final TfMachineGuiParts progressCompactorBg = guiPart(24, 34, 24, 17);

    private static TfMachineGuiParts guiPart(int ox, int oy, int xSize, int ySize)
    {
        return TfMachineGuiParts.addPart("tofufactory" + (counter++), ox, oy, xSize, ySize);
    }
}
