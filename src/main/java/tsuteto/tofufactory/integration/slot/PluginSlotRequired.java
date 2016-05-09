package tsuteto.tofufactory.integration.slot;

import net.minecraftforge.common.config.Configuration;

public class PluginSlotRequired extends PluginSlot
{
    public PluginSlotRequired(String name, String modId, String pluginName)
    {
        super(name, modId, pluginName);
    }

    @Override
    public boolean canLoadMod()
    {
        return true;
    }

    @Override
    public void loadConfig(Configuration cfg)
    {
        this.configFlag = true;
    }
}
