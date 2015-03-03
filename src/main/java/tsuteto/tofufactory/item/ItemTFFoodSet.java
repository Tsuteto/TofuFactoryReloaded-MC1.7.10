package tsuteto.tofufactory.item;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tsuteto.tofufactory.core.TFItems;

public class ItemTFFoodSet extends ItemBasicFoodSetBase<ItemTFFoodSet.Info>
{
    public static final Info kamaboko = new Info(0, 4, 0.3F, false, "kamaboko");
    public static final Info soySauceUdon = (Info)new Info(1, 7, 0.3F, false, "soySauceUdon").setContainerItem(new ItemStack(Items.bowl));
    public static final Info misoUdonStew = (Info)new Info(2, 12, 0.5F, false, "misoUdonStew").setContainerItem(new ItemStack(Items.bowl));
    public static final Info soySauceRamen = (Info)new Info(3, 8, 0.3F, false, "soySauceRamen").setContainerItem(new ItemStack(Items.bowl));

    public static final Info[] foods = new Info[]{kamaboko, soySauceUdon, misoUdonStew, soySauceRamen};

    public ItemTFFoodSet()
    {
        super(foods);
    }

    public static class Info extends FoodSetInfoBase
    {

        Info(int id, int healAmount, float saturationModifier, boolean alwaysEdible, String name)
        {
            super(id, healAmount, saturationModifier, alwaysEdible, name);
        }

        @Override
        public Item getItemInstance()
        {
            return TFItems.foodSet;
        }
    }


}
