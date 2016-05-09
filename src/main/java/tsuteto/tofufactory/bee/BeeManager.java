package tsuteto.tofufactory.bee;

import forestry.api.apiculture.FlowerManager;
import forestry.api.apiculture.IBeeRoot;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IFlowerRegistry;
import forestry.core.genetics.alleles.EnumAllele;
import net.minecraftforge.common.util.EnumHelper;
import tsuteto.tofu.block.TcBlocks;

public class BeeManager
{
    public static IBeeRoot beeInterface;

    public static String flowerTypeTofu = "flowersTofu";

    public static EnumAllele.Flowers FLOWERS_TOFU;

    public static void preInit()
    {
        FLOWERS_TOFU = EnumHelper.addEnum(
                EnumAllele.Flowers.class, "TOFU", new Class[]{String.class}, new Object[]{BeeManager.flowerTypeTofu});

    }

    public static void initBees()
    {
        beeInterface = (IBeeRoot)AlleleManager.alleleRegistry.getSpeciesRoot("rootBees");
        TFBeeDefinition.setupBeeSpecies();
        TFHiveType.registerBeehiveDrops();

        IFlowerRegistry flowerRegistry = FlowerManager.flowerRegistry;

        // Register acceptable plants
        flowerRegistry.registerAcceptableFlower(TcBlocks.tcLeaves, 2, flowerTypeTofu);

    }
}
