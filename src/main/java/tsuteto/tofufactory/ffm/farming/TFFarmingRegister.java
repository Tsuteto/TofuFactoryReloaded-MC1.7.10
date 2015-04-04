package tsuteto.tofufactory.ffm.farming;

import forestry.api.circuits.ChipsetManager;
import forestry.api.circuits.ICircuitLayout;
import forestry.api.farming.Farmables;
import forestry.api.farming.IFarmable;
import forestry.farming.circuits.CircuitFarmLogic;
import net.minecraft.item.ItemStack;
import tsuteto.tofufactory.ffm.farming.farmable.*;
import tsuteto.tofufactory.ffm.farming.farmlogic.*;
import tsuteto.tofufactory.integration.plugins.PluginFFM;

import java.util.Arrays;

public class TFFarmingRegister
{
    public static void registerFarming()
    {

        Farmables.farmables.put("farmApricot", Arrays.<IFarmable>asList(new FarmableApricot()));
        TFCircuit.farmApricot = (new CircuitFarmLogic("Apricot", FarmLogicApricot.class));

        Farmables.farmables.put("farmTofuTree", Arrays.<IFarmable>asList(new FarmableTofuTree()));
        TFCircuit.farmTofuTree = (new CircuitFarmLogic("TofuTree", FarmLogicTofuTree.class));

        Farmables.farmables.put("farmEdamame", Arrays.<IFarmable>asList(new FarmableEdamame()));
        TFCircuit.farmZundaManual = (new CircuitFarmLogic("Edamame", FarmLogicEdamame.class)).setManual();

        Farmables.farmables.put("farmSoybean", Arrays.<IFarmable>asList(new FarmableSoybean()));
        TFCircuit.farmSoybeanManual = (new CircuitFarmLogic("Soybean", FarmLogicSoybean.class)).setManual();

        Farmables.farmables.put("farmSesame", Arrays.<IFarmable>asList(new FarmableSesame()));
        TFCircuit.farmSesameManual = (new CircuitFarmLogic("Sesame", FarmLogicSesame.class)).setManual();

        Farmables.farmables.put("farmSoybeanHell", Arrays.<IFarmable>asList(new FarmableSoybeanHell()));
        TFCircuit.farmSoybeanHell = (new CircuitFarmLogic("SoybeanHell", FarmLogicSoybeanHell.class));

        Farmables.farmables.put("farmSprouts", Arrays.<IFarmable>asList(new FarmableSprouts()));
        TFCircuit.farmSprouts = (new CircuitFarmLogic("Sprouts", FarmLogicSprouts.class));

        ICircuitLayout layoutManaged = ChipsetManager.circuitRegistry.getLayout("forestry.farms.managed");
        ICircuitLayout layoutManual = ChipsetManager.circuitRegistry.getLayout("forestry.farms.manual");

        ChipsetManager.solderManager.addRecipe(layoutManual, new ItemStack(PluginFFM.TFTube, 1, 0), TFCircuit.farmZundaManual);
        ChipsetManager.solderManager.addRecipe(layoutManual, new ItemStack(PluginFFM.TFTube, 1, 1), TFCircuit.farmSoybeanManual);
        ChipsetManager.solderManager.addRecipe(layoutManual, new ItemStack(PluginFFM.TFTube, 1, 2), TFCircuit.farmSesameManual);
        ChipsetManager.solderManager.addRecipe(layoutManaged, new ItemStack(PluginFFM.TFTube, 1, 2), TFCircuit.farmApricot);
        ChipsetManager.solderManager.addRecipe(layoutManual, new ItemStack(PluginFFM.TFTube, 1, 3), TFCircuit.farmSprouts);
        ChipsetManager.solderManager.addRecipe(layoutManaged, new ItemStack(PluginFFM.TFTube, 1, 3), TFCircuit.farmTofuTree);
        ChipsetManager.solderManager.addRecipe(layoutManual, new ItemStack(PluginFFM.TFTube, 1, 4), TFCircuit.farmSoybeanHell);
        ChipsetManager.solderManager.addRecipe(layoutManaged, new ItemStack(PluginFFM.TFTube, 1, 4), TFCircuit.farmSoybeanHell);
    }
}
