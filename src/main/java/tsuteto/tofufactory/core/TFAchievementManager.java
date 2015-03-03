package tsuteto.tofufactory.core;

import com.google.common.collect.Maps;
import cpw.mods.fml.common.FMLCommonHandler;

import java.util.Map;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import tsuteto.tofufactory.achievement.TFAchievement;
import tsuteto.tofufactory.achievement.TFTriggerManager;
import tsuteto.tofufactory.item.ItemTFDust;

public class TFAchievementManager
{
    public static final String ACHIEVEMENT_PAGE_NAME = "Tofu Factory";
    public static Map<String, TFAchievement> achievementList = Maps.newHashMap();

    public static void initAchievement()
    {
        FMLCommonHandler.instance().bus().register(new TFTriggerManager());

        (new TFAchievement(TofuFactory.resourceDomain + "mithril", 3, 2,
                new ItemStack(TFItems.oreMithrilTofu, 1), null))
                .registerStat()
                .setTriggerItemPickup(new ItemStack(TFItems.oreMithrilTofu, 1));

        (new TFAchievement(TofuFactory.resourceDomain + "magicPowder", 5, 1,
                ItemTFDust.mithrilTofu.getStack(), achievementList.get("Mithril")))
                .registerStat()
                .setTriggerItemPickup(ItemTFDust.mithrilTofu.getStack());

        Achievement[] array = achievementList.values().toArray(new Achievement[achievementList.size()]);
        AchievementPage.registerAchievementPage(new AchievementPage(ACHIEVEMENT_PAGE_NAME, array));
    }
}
