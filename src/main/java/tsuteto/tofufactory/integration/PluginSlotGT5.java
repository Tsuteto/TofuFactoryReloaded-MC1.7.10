package tsuteto.tofufactory.integration;

import cpw.mods.fml.common.Loader;

public class PluginSlotGT5 extends PluginSlot
{
    public PluginSlotGT5(String name, String modId, String pluginName)
    {
        super(name, modId, pluginName);
    }

    @Override
    public boolean canLoadMod()
    {
        return super.canLoadMod() && !Loader.isModLoaded("gregapi");
    }
}
