package tsuteto.tofufactory.integration;

import net.minecraftforge.common.config.Configuration;

public class PluginSlotRequired extends PluginSlot
{
    public PluginSlotRequired(String name, String modId, Class<? extends ITFPlugin> pluginClass)
    {
        super(name, modId, pluginClass);
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
