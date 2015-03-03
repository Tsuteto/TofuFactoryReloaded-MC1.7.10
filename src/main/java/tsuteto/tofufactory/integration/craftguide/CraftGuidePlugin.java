package tsuteto.tofufactory.integration.craftguide;

import uristqwerty.CraftGuide.api.CraftGuideAPIObject;

import java.lang.reflect.Method;

public class CraftGuidePlugin
{
    public void load() throws Exception
    {
        new PulverizerCG();
        new CompactorCG();
        new CuttingMachineCG();
    }
}