package tsuteto.tofufactory.achievement;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import tsuteto.tofufactory.core.TFAchievementManager;

public class TFAchievement extends Achievement
{
    private AchievementTrigger trigger;
    private final String name;

    public TFAchievement(String par1, int par3, int par4, ItemStack par5ItemStack, Achievement par6Achievement)
    {
        super(par1, par1, par3, par4, par5ItemStack, par6Achievement);
        this.name = par1;
    }

    public TFAchievement registerStat()
    {
        super.registerStat();
        TFAchievementManager.achievementList.put(this.name, this);
        return this;
    }

    public TFAchievement setTriggerItemPickup(ItemStack item)
    {
        this.trigger = new AchievementTrigger(item);
        TFTriggerManager.itemPickupMap.add(this);
        return this;
    }

    public TFAchievement setTriggerItemCrafting(ItemStack item)
    {
        this.trigger = new AchievementTrigger(item);
        TFTriggerManager.itemCraftingMap.add(this);
        return this;
    }

    public AchievementTrigger getTrigger()
    {
        return this.trigger;
    }
}
