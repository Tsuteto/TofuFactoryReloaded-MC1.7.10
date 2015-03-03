package tsuteto.tofufactory.bee;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MaterialTFBeehive extends Material
{
    public MaterialTFBeehive(boolean noHarvest)
    {
        super(MapColor.stoneColor);

        if (noHarvest)
        {
            this.setRequiresTool();
            this.setImmovableMobility();
        }
    }
}
