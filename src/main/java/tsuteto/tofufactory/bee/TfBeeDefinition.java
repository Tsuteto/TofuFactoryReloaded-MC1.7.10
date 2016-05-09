package tsuteto.tofufactory.bee;

import forestry.api.apiculture.IAlleleBeeSpeciesCustom;
import forestry.api.core.EnumHumidity;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.text.WordUtils;
import tsuteto.tofu.TofuCraftCore;
import tsuteto.tofu.init.TcItems;
import tsuteto.tofu.item.ItemTcMaterials;

import java.awt.*;

public class TFBeeDefinition
{
    public static IAlleleBeeSpeciesCustom KINU;
    public static IAlleleBeeSpeciesCustom MOMEN;
    public static IAlleleBeeSpeciesCustom KONGO;

    public static void setupBeeSpecies()
    {
        KINU = create("kinu", TFBeeClassification.TOFU, "kinu", true, new Color(0xfffacd), new Color(15792383));
        MOMEN = create("momen", TFBeeClassification.TOFU, "momen", false, new Color(0xf0e68c), new Color(15792383));
        KONGO = create("kongo", TFBeeClassification.TOFU, "kongo", false, new Color(0x6cbeeb), new Color(0xd0efff));

        KINU
                .addProduct(new ItemStack(TcItems.tofuKinu, 4), 0.8F)
                .addProduct(new ItemStack(TcItems.tofuIshi, 2), 0.5F)
                .setHumidity(EnumHumidity.DAMP);
        BeeManager.beeInterface.registerTemplate(TFBeeGenomeManager.getTemplateKinu());

        MOMEN
                .addProduct(new ItemStack(TcItems.tofuMomen, 4), 0.8F)
                .addProduct(new ItemStack(TcItems.tofuIshi, 2), 0.5F)
                .setHumidity(EnumHumidity.DAMP)
                .setIsSecret();
        BeeManager.beeInterface.registerTemplate(TFBeeGenomeManager.getTemplateMomen());

        KONGO
                .addProduct(new ItemStack(TcItems.tofuMetal, 2), 0.5F)
                .addSpecialty(ItemTcMaterials.tofuDiamondNugget.getStack(), 0.5F)
                .setHumidity(EnumHumidity.DAMP)
                .setIsSecret()
                .setHasEffect();
        BeeManager.beeInterface.registerTemplate(TFBeeGenomeManager.getTemplateKongo());

        forestry.api.apiculture.BeeManager.beeMutationFactory.createMutation(
                KINU, KINU, TFBeeGenomeManager.getTemplateMomen(), 12);
        forestry.api.apiculture.BeeManager.beeMutationFactory.createMutation(
                KINU, MOMEN, TFBeeGenomeManager.getTemplateKongo(), 5)
                .restrictBiomeType(TofuCraftCore.BIOME_TYPE_TOFU);
    }

    public static IAlleleBeeSpeciesCustom create(String name, TFBeeClassification classification, String binomial, boolean dominant, Color primary, Color secondary) {
        String species = "species" + WordUtils.capitalize(name);

        String uid = "tofufactory." + species;
        String description = "tofufactory.description." + species;
        String unlocalizedName = "tofufactory.bees.species." + name;

        return forestry.api.apiculture.BeeManager.beeFactory.createSpecies(uid, dominant, "Tofunian", unlocalizedName, description, classification, binomial, primary.getRGB(), secondary.getRGB());
    }
}
