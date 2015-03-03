package tsuteto.tofufactory.item;

import net.minecraft.item.Item;
import tsuteto.tofufactory.integration.plugins.PluginFFM;

public class ItemTubeOverlayInfo extends ItemSetInfo
{
    public int primaryColor;
    public int secondaryColor;
    public boolean isSecret;

    public ItemTubeOverlayInfo(int id, String name, int primaryColor, int secondaryColor)
    {
        super(id, name);
        this.primaryColor = 0;
        this.secondaryColor = 0;
        this.isSecret = false;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
    }

    public ItemTubeOverlayInfo(int id, String name, int primaryColor)
    {
        this(id, name, primaryColor, 0);
    }

    public ItemTubeOverlayInfo setIsSecret()
    {
        this.isSecret = true;
        return this;
    }

    @Override
    protected Item getItemInstance()
    {
        return PluginFFM.TFTube;
    }
}
