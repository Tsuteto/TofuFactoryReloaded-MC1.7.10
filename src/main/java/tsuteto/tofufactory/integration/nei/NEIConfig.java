package tsuteto.tofufactory.integration.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import tsuteto.tofufactory.core.TofuFactory;
import tsuteto.tofufactory.gui.GuiTofuPulverizer;

public class NEIConfig implements IConfigureNEI
{
    public void loadConfig()
    {
        API.registerRecipeHandler(new PulverizerRecipeHandler());
        API.registerUsageHandler(new PulverizerRecipeHandler());
        API.registerGuiOverlay(GuiTofuPulverizer.class, "pulverizer", 5, 11);

        API.registerRecipeHandler(new CompactorRecipeHandler());
        API.registerUsageHandler(new CompactorRecipeHandler());
        API.registerGuiOverlay(GuiTofuPulverizer.class, "compactor", 5, 11);

        API.registerRecipeHandler(new CuttingMachineRecipeHandler());
        API.registerUsageHandler(new CuttingMachineRecipeHandler());
        API.registerGuiOverlay(GuiTofuPulverizer.class, "cuttingMachine", 5, 11);
    }

    public String getName()
    {
        return "TofuFactory Reloaded";
    }

    public String getVersion()
    {
        return TofuFactory.version;
    }
}
