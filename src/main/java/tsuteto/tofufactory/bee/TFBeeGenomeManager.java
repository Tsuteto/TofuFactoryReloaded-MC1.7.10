package tsuteto.tofufactory.bee;

import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.genetics.IAllele;
import forestry.apiculture.genetics.alleles.AlleleEffect;
import forestry.core.genetics.alleles.AlleleHelper;
import forestry.core.genetics.alleles.EnumAllele;

import java.util.Arrays;

public class TFBeeGenomeManager
{
    private static IAllele[] defaultTemplate;

    private static IAllele[] getDefaultTemplate() {
        if (defaultTemplate == null) {
            defaultTemplate = new IAllele[EnumBeeChromosome.values().length];

            AlleleHelper.instance.set(defaultTemplate, EnumBeeChromosome.SPEED, EnumAllele.Speed.NORMAL);
            AlleleHelper.instance.set(defaultTemplate, EnumBeeChromosome.LIFESPAN, EnumAllele.Lifespan.SHORTER);
            AlleleHelper.instance.set(defaultTemplate, EnumBeeChromosome.FERTILITY, EnumAllele.Fertility.NORMAL);
            AlleleHelper.instance.set(defaultTemplate, EnumBeeChromosome.TEMPERATURE_TOLERANCE, EnumAllele.Tolerance.NONE);
            AlleleHelper.instance.set(defaultTemplate, EnumBeeChromosome.NOCTURNAL, false);
            AlleleHelper.instance.set(defaultTemplate, EnumBeeChromosome.HUMIDITY_TOLERANCE, EnumAllele.Tolerance.UP_1);
            AlleleHelper.instance.set(defaultTemplate, EnumBeeChromosome.TOLERANT_FLYER, true);
            AlleleHelper.instance.set(defaultTemplate, EnumBeeChromosome.CAVE_DWELLING, false);
            AlleleHelper.instance.set(defaultTemplate, EnumBeeChromosome.FLOWER_PROVIDER, BeeManager.FLOWERS_TOFU);
            AlleleHelper.instance.set(defaultTemplate, EnumBeeChromosome.FLOWERING, EnumAllele.Flowering.SLOWEST);
            AlleleHelper.instance.set(defaultTemplate, EnumBeeChromosome.TERRITORY, EnumAllele.Territory.AVERAGE);
            AlleleHelper.instance.set(defaultTemplate, EnumBeeChromosome.EFFECT, AlleleEffect.effectNone);

        }
        return Arrays.copyOf(defaultTemplate, defaultTemplate.length);
    }

    public static IAllele[] getTemplateKinu()
    {
        IAllele[] genome = getDefaultTemplate();
        AlleleHelper.instance.set(genome, EnumBeeChromosome.SPECIES, TFBeeDefinition.KINU);

        return genome;
    }

    public static IAllele[] getTemplateMomen()
    {
        IAllele[] genome = getDefaultTemplate();
        AlleleHelper.instance.set(genome, EnumBeeChromosome.SPECIES, TFBeeDefinition.MOMEN);
        return genome;
    }

    public static IAllele[] getTemplateKongo()
    {
        IAllele[] genome = getDefaultTemplate();
        AlleleHelper.instance.set(genome, EnumBeeChromosome.SPECIES, TFBeeDefinition.KONGO);
        return genome;
    }
}
