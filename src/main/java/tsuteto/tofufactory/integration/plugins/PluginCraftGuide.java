package tsuteto.tofufactory.integration.plugins;

import tsuteto.tofufactory.integration.ITFPlugin;
import tsuteto.tofufactory.integration.craftguide.CraftGuidePlugin;

public class PluginCraftGuide implements ITFPlugin
{
    @Override
    public void init() throws Exception
    {
        new CraftGuidePlugin().load();
    }
}
