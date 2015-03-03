package tsuteto.tofufactory.achievement;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

import java.util.ArrayList;

public class TFTriggerManager
{
    public static ArrayList<TFAchievement> itemPickupMap = Lists.newArrayList();
    public static ArrayList<TFAchievement> itemCraftingMap = Lists.newArrayList();

    public static void achieveCreatingItem(ItemStack itemstack, EntityPlayer player)
    {
        for (TFAchievement ach : itemCraftingMap)
        {
            AchievementTrigger trigger = ach.getTrigger();

            if (trigger.equals(itemstack))
            {
                player.triggerAchievement(ach);
            }
        }
    }

    public static void achieveItemPickup(EntityPlayer entityPlayer, ItemStack itemStack)
    {
        for (TFAchievement ach : itemPickupMap)
        {
            AchievementTrigger trigger = ach.getTrigger();

            if (trigger.equals(itemStack))
            {
                entityPlayer.triggerAchievement(ach);
            }
        }
    }

    @SubscribeEvent
    public void onCrafting(PlayerEvent.ItemCraftedEvent event)
    {
        EntityPlayer player = event.player;
        ItemStack itemStack = event.crafting;
        achieveCreatingItem(itemStack, player);
    }

    @SubscribeEvent
    public void onEntityItemPickupEvent(EntityItemPickupEvent e)
    {
        if (e.entityPlayer != null && e.item.getEntityItem() != null)
        {
            achieveItemPickup(e.entityPlayer, e.item.getEntityItem());
        }
    }
}
