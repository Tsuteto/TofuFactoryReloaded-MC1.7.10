package tsuteto.tofufactory.bee;

import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAlleleSpecies;
import forestry.api.genetics.IClassification;

import java.util.ArrayList;

public enum TFBeeClassification implements IClassification
{
    TOFU("Tofu"),
    VEGETABLE("Vegetable");
    private String uID;
    private String latin;
    private ArrayList<IAlleleSpecies> species;
    private IClassification parent;
    private EnumClassLevel level;

    TFBeeClassification(String scientific)
    {
        this.uID = "classification." + this.name().toLowerCase();
        this.latin = scientific;
        this.level = EnumClassLevel.GENUS;
        this.species = new ArrayList<IAlleleSpecies>();
        this.parent = AlleleManager.alleleRegistry.getClassification("family.apidae");
        AlleleManager.alleleRegistry.registerClassification(this);
    }

    public EnumClassLevel getLevel()
    {
        return this.level;
    }

    public String getUID()
    {
        return this.uID;
    }

    public String getName()
    {
        return this.getUID();
    }

    public String getScientific()
    {
        return this.latin;
    }

    public String getDescription()
    {
        return this.getUID() + ".description";
    }

    public IClassification[] getMemberGroups()
    {
        return null;
    }

    public void addMemberGroup(IClassification group) {}

    public IAlleleSpecies[] getMemberSpecies()
    {
        return this.species.toArray(new IAlleleSpecies[this.species.size()]);
    }

    public void addMemberSpecies(IAlleleSpecies species)
    {
        if (!this.species.contains(species))
        {
            this.species.add(species);
        }
    }

    public IClassification getParent()
    {
        return this.parent;
    }

    public void setParent(IClassification parent)
    {
        this.parent = parent;
    }
}
