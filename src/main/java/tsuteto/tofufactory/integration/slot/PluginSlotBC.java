package tsuteto.tofufactory.integration.slot;

import cpw.mods.fml.common.ModMetadata;

public class PluginSlotBC extends PluginSlot
{
    public PluginSlotBC(String name, String modId, String pluginName)
    {
        super(name, modId, pluginName);
    }

    @Override
    public boolean canLoadMod()
    {
        if (super.canLoadMod())
        {
            ModMetadata metadata = this.getModContainer(this.modId).getMetadata();
            return metadata.version.startsWith("7.");
        }
        else
        {
            return false;
        }
    }
}
