package tsuteto.tofufactory.integration.plugins;

import tsuteto.tofufactory.integration.ITFPlugin;

public class PluginTcon implements ITFPlugin
{
    @Override
    public void preInit() throws Exception {}

    public void init() throws Exception
    {
        registerOreDictionary();
    }

    public void registerOreDictionary() {}
}
