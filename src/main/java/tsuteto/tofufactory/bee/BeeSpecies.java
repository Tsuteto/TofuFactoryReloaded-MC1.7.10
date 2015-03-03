package tsuteto.tofufactory.bee;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestry.api.apiculture.EnumBeeChromosome;
import forestry.api.apiculture.EnumBeeType;
import forestry.api.apiculture.IAlleleBeeSpecies;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.apiculture.IBeeRoot;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.core.IIconProvider;
import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IClassification;
import forestry.api.genetics.IIndividual;
import forestry.api.genetics.IMutation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public enum BeeSpecies implements IAlleleBeeSpecies, IIconProvider
{
    KINU("Kinu", "kinunu", BeeClassification.TOFU, 16775885, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false, true),
    MOMEN("Monen", "momenmen", BeeClassification.TOFU, 15787660, EnumTemperature.NORMAL, EnumHumidity.NORMAL, false, false, true);
    private String binomial;
    private String authority;
    private int primaryColour;
    private int secondaryColour;
    private EnumTemperature temperature;
    private EnumHumidity humidity;
    private boolean hasEffect;
    private boolean isSecret;
    private boolean isCounted;
    private boolean isActive;
    private boolean isNocturnal;
    private IClassification branch;
    private HashMap<ItemStack, Integer> products;
    private HashMap<ItemStack, Integer> specialties;
    private IAllele[] genomeTemplate;
    private String uid;
    private String uid2;
    private boolean dominant;
    private int complexity;
    @SideOnly(Side.CLIENT)
    private IIcon[][] icons;
    private static final int defaultBodyColour = 15792383;

    public static void setupBeeSpecies()
    {
        KINU.addProduct(new ItemStack(Items.stick, 1), 15).setGenome(BeeGenomeManager.getTemplateKINU()).register();
        MOMEN.addProduct(new ItemStack(Items.stick, 1), 15).setGenome(BeeGenomeManager.getTemplateMomen()).register();
    }

    private BeeSpecies(String speciesName, String genusName, IClassification classification, int firstColour, EnumTemperature preferredTemp, EnumHumidity preferredHumidity, boolean hasGlowEffect, boolean isSpeciesDominant)
    {
        this(speciesName, genusName, classification, firstColour, 15792383, preferredTemp, preferredHumidity, hasGlowEffect, true, true, isSpeciesDominant);
    }

    private BeeSpecies(String speciesName, String genusName, IClassification classification, int firstColour, int secondColour, EnumTemperature preferredTemp, EnumHumidity preferredHumidity, boolean hasGlowEffect, boolean isSpeciesDominant)
    {
        this(speciesName, genusName, classification, firstColour, secondColour, preferredTemp, preferredHumidity, hasGlowEffect, true, true, isSpeciesDominant);
    }

    private BeeSpecies(String speciesName, String genusName, IClassification classification, int firstColour, int secondColour, EnumTemperature preferredTemp, EnumHumidity preferredHumidity, boolean isSecret, boolean hasGlowEffect, boolean isSpeciesDominant)
    {
        this(speciesName, genusName, classification, firstColour, secondColour, preferredTemp, preferredHumidity, hasGlowEffect, isSecret, true, isSpeciesDominant);
    }

    private BeeSpecies(String speciesName, String genusName, IClassification classification, int firstColour, EnumTemperature preferredTemp, EnumHumidity preferredHumidity, boolean isSecret, boolean hasGlowEffect, boolean isSpeciesDominant)
    {
        this(speciesName, genusName, classification, firstColour, 15792383, preferredTemp, preferredHumidity, hasGlowEffect, isSecret, true, isSpeciesDominant);
    }

    private BeeSpecies(String speciesName, String genusName, IClassification classification, int firstColour, int secondColour, EnumTemperature preferredTemp, EnumHumidity preferredHumidity, boolean hasGlowEffect, boolean isSpeciesSecret, boolean isSpeciesCounted, boolean isSpeciesDominant)
    {
        this.uid = "tofufactory.species" + speciesName;
        this.uid2 = speciesName;
        this.dominant = isSpeciesDominant;
        AlleleManager.alleleRegistry.registerAllele(this);
        this.binomial = genusName;
        this.authority = "DawnOfTofu";
        this.primaryColour = firstColour;
        this.secondaryColour = secondColour;
        this.temperature = preferredTemp;
        this.humidity = preferredHumidity;
        this.hasEffect = hasGlowEffect;
        this.isSecret = isSpeciesSecret;
        this.isCounted = isSpeciesCounted;
        this.products = new HashMap();
        this.specialties = new HashMap();
        this.branch = classification;
        this.branch.addMemberSpecies(this);
        this.isActive = true;
        this.isNocturnal = false;
    }

    @Override
    public String getUnlocalizedName()
    {
        return this.uid2;
    }

    public BeeSpecies setGenome(IAllele[] genome)
    {
        this.genomeTemplate = genome;
        return this;
    }

    public IAllele[] getGenome()
    {
        return this.genomeTemplate;
    }

    public BeeSpecies addProduct(ItemStack produce, int percentChance)
    {
        this.products.put(produce, Integer.valueOf(percentChance));
        return this;
    }

    public BeeSpecies addSpecialty(ItemStack produce, int percentChance)
    {
        this.specialties.put(produce, Integer.valueOf(percentChance));
        return this;
    }

    public ItemStack getBeeItem(EnumBeeType beeType)
    {
        return BeeManager.beeInterface.getMemberStack(BeeManager.beeInterface.getBee((World) null, BeeManager.beeInterface.templateAsGenome(this.genomeTemplate)), beeType.ordinal());
    }

    public String getName()
    {
        return StatCollector.translateToLocal(this.getUID() + ".name").trim();
    }

    public String getDescription()
    {
        return this.getUID() + ".description";
    }

    public EnumTemperature getTemperature()
    {
        return this.temperature;
    }

    public EnumHumidity getHumidity()
    {
        return this.humidity;
    }

    public boolean hasEffect()
    {
        return this.hasEffect;
    }

    public BeeSpecies setInactive()
    {
        this.isActive = false;
        return this;
    }

    public boolean isActive()
    {
        return this.isActive;
    }

    public boolean isSecret()
    {
        return this.isSecret;
    }

    public boolean isCounted()
    {
        return this.isCounted;
    }

    public String getBinomial()
    {
        return this.binomial;
    }

    public String getAuthority()
    {
        return this.authority;
    }

    public IClassification getBranch()
    {
        return this.branch;
    }

    public HashMap getProducts()
    {
        return this.products;
    }

    public HashMap getSpecialty()
    {
        return this.specialties;
    }

    public String getUID()
    {
        return this.uid;
    }

    public String getUID2()
    {
        return this.uid2;
    }

    public boolean isDominant()
    {
        return this.dominant;
    }

    public IBeeRoot getRoot()
    {
        return BeeManager.beeInterface;
    }

    public boolean isNocturnal()
    {
        return this.isNocturnal;
    }

    public boolean isJubilant(IBeeGenome genome, IBeeHousing housing)
    {
        return true;
    }

    private BeeSpecies register()
    {
        BeeManager.beeInterface.registerTemplate(this.getGenome());
        LanguageRegistry.instance().addStringLocalization(this.getUID() + ".name", this.getUID2());

        if (!this.isActive)
        {
            AlleleManager.alleleRegistry.blacklistAllele(this.getUID());
        }

        return this;
    }

    public int getIconColour(int renderPass)
    {
        int value = 16448210;

        if (renderPass == 0)
        {
            value = this.primaryColour;
        }
        else if (renderPass == 1)
        {
            value = this.secondaryColour;
        }

        return value;
    }

    @SideOnly(Side.CLIENT)
    public IIconProvider getIconProvider()
    {
        return this;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(EnumBeeType type, int renderPass)
    {
        return this.icons[type.ordinal()][Math.min(renderPass, 2)];
    }

    public int getComplexity()
    {
        return 1 + this.getMutationPathLength(this, new ArrayList());
    }

    private int getMutationPathLength(IAllele species, ArrayList<IAllele> excludeSpecies)
    {
        byte own = 1;
        int highest = 0;
        excludeSpecies.add(species);
        Iterator i$ = this.getRoot().getPaths(species, EnumBeeChromosome.SPECIES.ordinal()).iterator();

        while (i$.hasNext())
        {
            IMutation mutation = (IMutation)i$.next();
            int otherAdvance;

            if (!excludeSpecies.contains(mutation.getAllele0()))
            {
                otherAdvance = this.getMutationPathLength(mutation.getAllele0(), excludeSpecies);

                if (otherAdvance > highest)
                {
                    highest = otherAdvance;
                }
            }

            if (!excludeSpecies.contains(mutation.getAllele1()))
            {
                otherAdvance = this.getMutationPathLength(mutation.getAllele1(), excludeSpecies);

                if (otherAdvance > highest)
                {
                    highest = otherAdvance;
                }
            }
        }

        return own + (highest > 0 ? highest : 0);
    }

    public float getResearchSuitability(ItemStack itemStack)
    {
        float value = 0.0F;

        if (itemStack != null)
        {
            Iterator i$ = this.products.keySet().iterator();
            ItemStack specialty;

            while (i$.hasNext())
            {
                specialty = (ItemStack)i$.next();

                if (itemStack.isItemEqual(specialty))
                {
                    value = 1.0F;
                    break;
                }
            }

            if (value <= 0.0F)
            {
                i$ = this.specialties.keySet().iterator();

                while (i$.hasNext())
                {
                    specialty = (ItemStack)i$.next();

                    if (specialty.isItemEqual(itemStack))
                    {
                        value = 1.0F;
                        break;
                    }
                }
            }
        }

        return value;
    }

    public ItemStack[] getResearchBounty(World world, GameProfile researcher, IIndividual individual, int bountyLevel)
    {
        System.out.println("Bounty level: " + bountyLevel);
        ArrayList bounty = new ArrayList();

        if (world.rand.nextFloat() < 10.0F / (float)bountyLevel)
        {
            Collection i$ = this.getRoot().getCombinations(this);

            if (i$.size() > 0)
            {
                IMutation[] specialty = (IMutation[])((IMutation[])i$.toArray(new IMutation[i$.size()]));
                bounty.add(AlleleManager.alleleRegistry.getMutationNoteStack(researcher, specialty[world.rand.nextInt(specialty.length)]));
            }
        }

        Iterator i$1 = this.products.keySet().iterator();
        ItemStack copy;
        ItemStack specialty1;

        while (i$1.hasNext())
        {
            specialty1 = (ItemStack)i$1.next();
            copy = specialty1.copy();
            copy.animationsToGo = 1 + world.rand.nextInt(bountyLevel / 2);
            bounty.add(copy);
        }

        i$1 = this.specialties.keySet().iterator();

        while (i$1.hasNext())
        {
            specialty1 = (ItemStack)i$1.next();
            copy = specialty1.copy();
            copy.animationsToGo = world.rand.nextInt(bountyLevel / 3);

            if (copy.animationsToGo > 0)
            {
                bounty.add(copy);
            }
        }

        return (ItemStack[])((ItemStack[])bounty.toArray(new ItemStack[bounty.size()]));
    }

    public String getEntityTexture()
    {
        return "/gfx/forestry/entities/bees/honeyBee.png";
    }

    public void registerIcons(IIconRegister itemMap)
    {
        this.icons = new IIcon[EnumBeeType.values().length][3];
        String root = "Forestry".toLowerCase() + ":bees/default/";
        IIcon body1 = itemMap.registerIcon(root + "body1");

        for (int i = 0; i < EnumBeeType.values().length; ++i)
        {
            if (EnumBeeType.values()[i] != EnumBeeType.NONE)
            {
                this.icons[i][0] = itemMap.registerIcon(root + EnumBeeType.values()[i].toString().toLowerCase(Locale.ENGLISH) + ".outline");
                this.icons[i][1] = EnumBeeType.values()[i] != EnumBeeType.LARVAE ? body1 : itemMap.registerIcon(root + EnumBeeType.values()[i].toString().toLowerCase(Locale.ENGLISH) + ".body");
                this.icons[i][2] = itemMap.registerIcon(root + EnumBeeType.values()[i].toString().toLowerCase(Locale.ENGLISH) + ".body2");
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(short texUID)
    {
        return this.icons[0][0];
    }
}
