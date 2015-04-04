package tsuteto.tofufactory.integration.plugins;

import appeng.api.AEApi;
import appeng.api.definitions.Materials;
import net.minecraftforge.oredict.OreDictionary;
import tsuteto.tofufactory.integration.ITFPlugin;

public class PluginAppeng implements ITFPlugin
{
    @Override
    public void preInit() throws Exception {}

    public void init() throws Exception
    {
        registerOreDictionary();
    }

    public void registerOreDictionary()
    {
        Materials materials = AEApi.instance().materials();
        OreDictionary.registerOre("flour", materials.materialFlour.stack(1));
        OreDictionary.registerOre("cookingFlour", materials.materialFlour.stack(1));
        OreDictionary.registerOre("craftingCircuitTier04", materials.materialBasicCard.stack(1)); // materialProcessorBasic
        OreDictionary.registerOre("craftingCircuitTier05", materials.materialAdvCard.stack(1)); // materialProcessorAdvanced
    }
}
