package tsuteto.tofufactory.integration;

import cpw.mods.fml.common.Loader;
import net.minecraftforge.common.config.Configuration;
import tsuteto.tofufactory.core.TofuFactory;

public class PluginSlot
{
    public final String modId;
    public final String name;
    public final Class<? extends ITFPlugin> pluginClass;

    protected Class<? extends ITFRecipeModule> recipeClass = null;
    protected boolean configFlag;
    protected IntegrationPhase phase = IntegrationPhase.UNLOADED;
    protected Throwable exception = null;

    public PluginSlot(String name, String modId, Class<? extends ITFPlugin> pluginClass)
    {
        this.name = name;
        this.modId = modId;
        this.pluginClass = pluginClass;
    }

    public PluginSlot addRecipes(Class<? extends ITFRecipeModule> recipeClass)
    {
        this.recipeClass = recipeClass;
        return this;
    }

    public boolean canLoadMod()
    {
        return configFlag && Loader.isModLoaded(modId);
    }

    public void callPlugin()
    {
        if (!canLoadMod()) return;

        ITFPlugin plugin;
        try
        {
            plugin = pluginClass.newInstance();
            plugin.init();
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

        if (this.recipeClass != null)
        {
            try
            {
                ITFRecipeModule recipe = recipeClass.newInstance();
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
                    case UNLOADED: where = "in initializing"; break;
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
}
