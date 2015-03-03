package tsuteto.tofufactory.bee;

import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.genetics.IAllele;

public class BeeGenomeManager
{
    private static IAllele[] getTemplateModBase()
    {
        IAllele[] genome = new IAllele[EnumBeeChromosome.values().length];
        genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.KINU;
        genome[EnumBeeChromosome.SPEED.ordinal()] = Allele.getBaseAllele("speedSlowest");
        genome[EnumBeeChromosome.LIFESPAN.ordinal()] = Allele.getBaseAllele("lifespanShorter");
        genome[EnumBeeChromosome.FERTILITY.ordinal()] = Allele.getBaseAllele("fertilityNormal");
        genome[EnumBeeChromosome.TEMPERATURE_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceNone");
        genome[EnumBeeChromosome.NOCTURNAL.ordinal()] = Allele.getBaseAllele("boolFalse");
        genome[EnumBeeChromosome.HUMIDITY_TOLERANCE.ordinal()] = Allele.getBaseAllele("toleranceNone");
        genome[EnumBeeChromosome.TOLERANT_FLYER.ordinal()] = Allele.getBaseAllele("boolFalse");
        genome[EnumBeeChromosome.CAVE_DWELLING.ordinal()] = Allele.getBaseAllele("boolFalse");
        genome[EnumBeeChromosome.FLOWER_PROVIDER.ordinal()] = Allele.getBaseAllele("flowersVanilla");
        genome[EnumBeeChromosome.FLOWERING.ordinal()] = Allele.getBaseAllele("floweringSlowest");
        genome[EnumBeeChromosome.TERRITORY.ordinal()] = Allele.getBaseAllele("territoryDefault");
        genome[EnumBeeChromosome.EFFECT.ordinal()] = Allele.getBaseAllele("effectNone");
        return genome;
    }

    public static IAllele[] getTemplateKINU()
    {
        IAllele[] genome = getTemplateModBase();
        genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.KINU;
        return genome;
    }

    public static IAllele[] getTemplateMomen()
    {
        IAllele[] genome = getTemplateModBase();
        genome[EnumBeeChromosome.SPECIES.ordinal()] = BeeSpecies.MOMEN;
        return genome;
    }
}
