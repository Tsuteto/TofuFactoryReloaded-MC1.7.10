package tsuteto.tofufactory.world;

import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import tsuteto.tofufactory.bee.TFHiveType;
import tsuteto.tofufactory.integration.plugins.PluginFFM;
import tsuteto.tofufactory.core.TFItems;
import tsuteto.tofu.Settings;
import tsuteto.tofu.block.TcBlocks;
import tsuteto.tofu.world.biome.TcBiomes;

public class WorldGeneratorHandler implements IWorldGenerator
{
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        int Did = world.provider.dimensionId;
        int tofuID = Settings.tofuDimNo;

        if (Did == -1)
        {
            this.generateNether(world, random, chunkX << 4, chunkZ << 4);
        }
        else if (Did == 0)
        {
            this.generateSurface(world, random, chunkX << 4, chunkZ << 4);
        }
        else if (Did == tofuID)
        {
            this.generateTofu(world, random, chunkX << 4, chunkZ << 4);
        }
    }

    private void generateSurface(World world, Random random, int chunkX, int chunkZ)
    {
        for (int i = 0; i < 4 + random.nextInt(5); ++i)
        {
            int randomPosX = chunkX + random.nextInt(16);
            int randomPosY = 30 + random.nextInt(15) - random.nextInt(15);
            int randomPosZ = chunkZ + random.nextInt(16);
            BiomeGenBase biome = world.getBiomeGenForCoords(randomPosX, randomPosZ);
            if (biome.getEnableSnow())
            {
                (new WorldGenMinable(TFItems.oreMithrilTofu, 4)).generate(world, random, randomPosX, randomPosY, randomPosZ);
            }
        }
    }

    private void generateNether(World world, Random random, int chunkX, int chunkZ) {}

    private void generateTofu(World world, Random random, int chunkX, int chunkZ)
    {
        if (random.nextInt(12) == 0)
        {
            int x = chunkX + random.nextInt(16);
            int z = chunkZ + random.nextInt(16);
            int y = world.getHeightValue(x, z);

            if (world.getBiomeGenForCoords(x, z) == TcBiomes.tofuForest
                    && world.isAirBlock(x, y, z) && world.getBlock(x, y - 1, z) == TcBlocks.tofuTerrain)
            {
                world.setBlock(x, y, z, PluginFFM.tofuHive, TFHiveType.KINU.ordinal(), 3);
            }
        }
    }
}
