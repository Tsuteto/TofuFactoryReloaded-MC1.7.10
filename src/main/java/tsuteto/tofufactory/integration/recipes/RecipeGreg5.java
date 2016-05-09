package tsuteto.tofufactory.integration.recipes;

import tsuteto.tofufactory.integration.ITFRecipeModule;

public class RecipeGreg5 implements ITFRecipeModule
{
    public void register() throws Exception
    {
        registerGrinder();
        registerCentrifuge();
    }

    private void registerGrinder()
    {
        //tofuDiamondNugget + Water cell -> 2x tofu steel dust + some greg items
//        GregTech_API.sRecipeAdder.addGrinderRecipe(
//                ItemTcMaterials.tofuDiamondNugget.getStack(), GT_ModHandler.getWaterCell(0),
//                new ItemStack(TFItems.tofuDust, 2, 0), null, null, null);
    }

    private void registerCentrifuge()
    {
//        GregTech_API.sRecipeAdder.addCentrifugeRecipe(
//                ItemTFDust.tofuSteel.getStack(), 2,
//                ItemTFCell.bittern.getStack(), new ItemStack(TFItems.bakingSoda, 1), ItemTFCell.soymilk.getStack(), null, null, null,  50);
//
//        GregTech_API.sRecipeAdder.addCentrifugeRecipe(
//                ItemTcMaterials.tofuDiamondNugget.getStack(), 2,
//                ItemTFCell.bittern.getStack(2), new ItemStack(TFItems.bakingSoda, 2), null, null, null, null, 50);
//
//        if (TFIntegrationManager.modAE.isAvailable())
//        {
//            ItemStack meCable = AEApi.instance().parts().partCableCovered.stack(AEColor.White, 1);
//            meCable.stackSize = 4;
//            GregTech_API.sRecipeAdder.addWiremillRecipe(AEApi.instance().materials().materialCertusQuartzCrystal.stack(1), meCable, 10, 500);
//        }
    }
}
