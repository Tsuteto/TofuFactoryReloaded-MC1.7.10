package tsuteto.tofufactory.integration.recipes;

import appeng.api.AEApi;
import appeng.api.util.AEColor;
import gregapi.data.CS;
import gregapi.recipes.GT_ModHandler;
import net.minecraft.item.ItemStack;
import tsuteto.tofu.item.ItemTcMaterials;
import tsuteto.tofufactory.core.TFItems;
import tsuteto.tofufactory.integration.ITFRecipeModule;
import tsuteto.tofufactory.integration.TFIntegrationManager;
import tsuteto.tofufactory.item.ItemTFCell;
import tsuteto.tofufactory.item.ItemTFDust;

public class RecipeGreg6 implements ITFRecipeModule
{
    public void register() throws Exception
    {
        registerGrinder();
        registerCentrifuge();
    }

    private void registerGrinder()
    {
        //tofuDiamondNugget + Water cell -> 2x tofu steel dust + some greg items
        CS.RA.addGrinderRecipe(
                ItemTcMaterials.tofuDiamondNugget.getStack(), GT_ModHandler.getWaterCell(0),
                new ItemStack(TFItems.tofuDust, 2, 0), null, null, null);
    }

    private void registerCentrifuge()
    {
        CS.RA.addCentrifugeRecipe(
                ItemTFDust.tofuSteel.getStack(), 2,
                ItemTFCell.bittern.getStack(), new ItemStack(TFItems.bakingSoda, 1), ItemTFCell.soymilk.getStack(), null, null, null, 50);

        CS.RA.addCentrifugeRecipe(
                ItemTcMaterials.tofuDiamondNugget.getStack(), 2,
                ItemTFCell.bittern.getStack(2), new ItemStack(TFItems.bakingSoda, 2), null, null, null, null, 50);

        if (TFIntegrationManager.modAE.isAvailable())
        {
            ItemStack meCable = AEApi.instance().parts().partCableCovered.stack(AEColor.White, 1);
            meCable.stackSize = 4;
            CS.RA.addWiremillRecipe(AEApi.instance().materials().materialCertusQuartzCrystal.stack(1), meCable, 10, 500);
        }
    }
}
