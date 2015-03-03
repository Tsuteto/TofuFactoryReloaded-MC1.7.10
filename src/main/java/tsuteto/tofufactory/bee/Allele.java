package tsuteto.tofufactory.bee;

import forestry.api.apiculture.IAlleleBeeEffect;
import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IAlleleRegistry;

public class Allele implements IAllele
{
    public static IAlleleBeeEffect forestryBaseEffect;
    private String uid;
    private boolean dominant;

    public static void setupAdditionalAlleles()
    {
        forestryBaseEffect = (IAlleleBeeEffect)getBaseAllele("effectNone");
    }

    public static void registerDeprecatedAlleleReplacements()
    {
        IAlleleRegistry registry = AlleleManager.alleleRegistry;
    }

    public static IAlleleBeeSpecies getBaseSpecies(String name)
    {
        return (IAlleleBeeSpecies)AlleleManager.alleleRegistry.getAllele("forestry.species" + name);
    }

    public static IAlleleBeeSpecies getExtraSpecies(String name)
    {
        return (IAlleleBeeSpecies)AlleleManager.alleleRegistry.getAllele("extrabees.species." + name);
    }

    public static IAllele getBaseAllele(String name)
    {
        return AlleleManager.alleleRegistry.getAllele("forestry." + name);
    }

    public static IAllele getAllele(String name)
    {
        return AlleleManager.alleleRegistry.getAllele(name);
    }

    public Allele(String id, boolean isDominant)
    {
        this.uid = "magicbees." + id;
        this.dominant = isDominant;
        AlleleManager.alleleRegistry.registerAllele(this);
    }

    public String getUID()
    {
        return this.uid;
    }

    public boolean isDominant()
    {
        return this.dominant;
    }

    public String getName()
    {
        return this.getUID();
    }

    @Override
    public String getUnlocalizedName()
    {
        return this.uid;
    }
}
