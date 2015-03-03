package tsuteto.tofufactory.item;

public abstract class FoodSetInfoBase extends ItemSetInfo
{
    public int healAmount;
    public float saturationModifier;
    public boolean alwaysEdible;

    public FoodSetInfoBase(int id, int healAmount, float saturationModifier, boolean alwaysEdible, String name)
    {
        super(id, name);
        this.itemUseDuration = 32;
        this.healAmount = healAmount;
        this.saturationModifier = saturationModifier;
        this.alwaysEdible = alwaysEdible;
    }
}
