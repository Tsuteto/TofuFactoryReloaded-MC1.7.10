package tsuteto.tofufactory.integration.recipes;

import appeng.api.AEApi;
import appeng.api.util.AEColor;
import gregtech.api.GregTech_API;
import gregtech.api.enums.ConfigCategories;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Recipe;
import ic2.neiIntegration.core.CentrifugeRecipeHandler;
import net.minecraft.item.ItemStack;
import tsuteto.tofu.item.ItemTcMaterials;
import tsuteto.tofu.item.TcItems;
import tsuteto.tofufactory.config.TFConfig;
import tsuteto.tofufactory.core.TFItems;
import tsuteto.tofufactory.integration.ITFRecipeModule;
import tsuteto.tofufactory.integration.ITFRecipeModule;
import tsuteto.tofufactory.integration.TFIntegrationManager;
import tsuteto.tofufactory.integration.plugins.PluginIC2;
import tsuteto.tofufactory.item.ItemTFCell;
import tsuteto.tofufactory.item.ItemTFDust;

public class RecipeGreg implements ITFRecipeModule
{
    public void register()
    {
        registerGrinder();
        registerCentrifuge();
    }

    private void registerGrinder()
    {
        //tofuDiamondNugget + Water cell -> 2x tofu steel dust + some greg items
        GregTech_API.sRecipeAdder.addGrinderRecipe(
                ItemTcMaterials.tofuDiamondNugget.getStack(), GT_ModHandler.getWaterCell(0),
                new ItemStack(TFItems.tofuDust, 2, 0), null, null, null);
    }

    private void registerCentrifuge()
    {
        GregTech_API.sRecipeAdder.addCentrifugeRecipe(
                ItemTFDust.tofuSteel.getStack(), 2,
                ItemTFCell.bittern.getStack(), new ItemStack(TFItems.bakingSoda, 1), ItemTFCell.soymilk.getStack(), null, null, null,  50);

        GregTech_API.sRecipeAdder.addCentrifugeRecipe(
                ItemTcMaterials.tofuDiamondNugget.getStack(), 2,
                ItemTFCell.bittern.getStack(2), new ItemStack(TFItems.bakingSoda, 2), null, null, null, null, 50);

        if (TFIntegrationManager.modAppeng.isAvailable())
        {
            ItemStack meCable = AEApi.instance().parts().partCableCovered.stack(AEColor.White, 1);
            meCable.stackSize = 4;
            GregTech_API.sRecipeAdder.addWiremillRecipe(AEApi.instance().materials().materialCertusQuartzCrystal.stack(1), meCable, 10, 500);
        }
    }
}
