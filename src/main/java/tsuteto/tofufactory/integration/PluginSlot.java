package tsuteto.tofufactory.integration;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import net.minecraftforge.common.config.Configuration;
import tsuteto.tofufactory.core.TofuFactory;

import java.util.List;

public class PluginSlot
{
    public final String modId;
    public final String name;

    public final String pluginName;
    public ITFPlugin plugin = null;

    protected boolean hasRecipe = false;
    protected boolean configFlag;
    protected IntegrationPhase phase = IntegrationPhase.UNLOADED;
    protected Throwable exception = null;

    public PluginSlot(String name, String modId, String pluginName)
    {
        this.name = name;
        this.modId = modId;
        this.pluginName = pluginName;
    }

    public PluginSlot withRecipes()
    {
        this.hasRecipe = true;
        return this;
    }

    public boolean canLoadMod()
    {
        return configFlag && Loader.isModLoaded(modId);
    }

    public void callPlugin()
    {
        if (!canLoadMod()) return;

        try
        {
            this.plugin = (ITFPlugin)Class.forName("tsuteto.tofufactory.integration.plugins.Plugin" + pluginName).newInstance();
            this.phase = IntegrationPhase.LOADED;
        }
        catch (Exception e)
        {
            this.exception = e;
        }
    }

    public void callPreInit()
    {
        if (this.phase != IntegrationPhase.LOADED) return;
        try
        {
            this.plugin.preInit();
            this.phase = IntegrationPhase.PRE_INITIALIZED;
        }
        catch (Exception e)
        {
            this.exception = e;
        }
    }

    public void callInit()
    {
        if (this.phase != IntegrationPhase.PRE_INITIALIZED) return;
        try
        {
            this.plugin.init();
            this.phase = IntegrationPhase.INITIALIZED;
        }
        catch (Exception e)
        {
            this.exception = e;
        }
    }

    public void registerRecipes()
    {
        if (this.phase != IntegrationPhase.INITIALIZED) return;

        if (this.hasRecipe)
        {
            try
            {
                ITFRecipeModule recipe = (ITFRecipeModule)Class.forName("tsuteto.tofufactory.integration.recipes.Recipe" + pluginName).newInstance();
                recipe.register();
                this.phase = IntegrationPhase.RECIPE_LOADED;
            }
            catch (Exception e)
            {
                this.exception = e;
            }
        }
        else
        {
            this.phase = IntegrationPhase.RECIPE_LOADED;
        }
    }

    public void assertPluginLoaded()
    {
        if (this.phase == IntegrationPhase.RECIPE_LOADED)
        {
            this.phase = IntegrationPhase.READY;
            TofuFactory.log.info("{} integration finished successfully.", this.name);
        }
        else
        {
            if (this.exception != null)
            {
                String where;
                switch (this.phase)
                {
                    case UNLOADED: where = "in instantiation"; break;
                    case LOADED: where = "in pre-initializing"; break;
                    case PRE_INITIALIZED: where = "in initializing"; break;
                    case INITIALIZED: where = "in recipe registration"; break;
                    default: where = "somewhere";
                }
                TofuFactory.log.warn(this.exception,
                        "{} integration failed {}" +
                        " - please let the author of this mod know the API may have changed.",
                        this.name, where);
            }
            this.phase = IntegrationPhase.FAILED;
        }
    }

    public Throwable getException()
    {
        return exception;
    }

    public boolean isAvailable()
    {
        return this.phase != IntegrationPhase.UNLOADED && this.phase != IntegrationPhase.FAILED;
    }

    public boolean isReady()
    {
        return this.phase == IntegrationPhase.READY;
    }

    public void loadConfig(Configuration cfg)
    {
        configFlag = cfg.get("integration", this.name, true, "Integration with " + this.name).getBoolean(true);
    }

    protected ModContainer getModContainer(String modId)
    {
        List<ModContainer> mods = Loader.instance().getModList();
        for (ModContainer mod : mods)
        {
            if (modId.equals(mod.getModId()))
            {
                return mod;
            }
        }
        return null;
    }
}
