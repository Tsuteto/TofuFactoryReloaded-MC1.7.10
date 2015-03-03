package tsuteto.tofufactory.bee;

import cpw.mods.fml.common.registry.GameRegistry;
import forestry.api.apiculture.IBeeRoot;
import forestry.api.genetics.AlleleManager;
import tsuteto.tofufactory.core.TofuFactory;
import tsuteto.tofufactory.world.WorldGeneratorHandler;

public class BeeManager
{
    public static IBeeRoot beeInterface;

    public static void initBees()
    {
        beeInterface = (IBeeRoot)AlleleManager.alleleRegistry.getSpeciesRoot("rootBees");
        Allele.setupAdditionalAlleles();
        BeeSpecies.setupBeeSpecies();
        Allele.registerDeprecatedAlleleReplacements();
        TofuFactory.log.debug("initializing bees");
        TFHiveType.registerBeehiveDrops();
        GameRegistry.registerWorldGenerator(new WorldGeneratorHandler(), 2);
    }
}
