package space.libs.mixins.mods.legacy.mochickens;

import net.minecraft.world.biome.BiomeGenBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import space.libs.CompatLib;

@Pseudo
@Mixin(targets = "com.saxon564.mochickens.configs.FileManager", remap = false)
public class MixinFileManager {

    /**
     * @author HowardZHY
     * @reason Fix dumb code NPE
     */
    @Overwrite
    public static int[] generateBlackLists(int[] allowed, String type) {
        int[] ret = new int[256];
        int loop = 0;
        for (int i = 0; i < (BiomeGenBase.getBiomeGenArray()).length; i++) {
            BiomeGenBase biome = BiomeGenBase.getBiome(i);
            if (biome != null && biome.biomeName != null) {
                String name = biome.biomeName.toLowerCase();
                if (!name.equalsIgnoreCase("chicken forest") && !name.equalsIgnoreCase("chicken plains")) {
                    for (int k = 0; k < allowed.length; k++) {
                        if (allowed[k] != i) {
                            ret[loop] = i;
                            loop++;
                        }
                    }
                }
            }
        }
        return ret;
    }

    /**
     * @author HowardZHY
     * @reason Fix dumb code NPE
     */
    @Overwrite
    public static int generateList() {
        int loop = 0;
        for (int i = 0; i < (BiomeGenBase.getBiomeGenArray()).length; i++) {
            BiomeGenBase biome = BiomeGenBase.getBiome(i);
            if (biome != null) {
                if (biome.biomeName == null) {
                    CompatLib.LOGGER.warn("[Mo' Chickens] Biome (id " + i + ") has null name, could not build spawn information.");
                    biome.setBiomeName(biome.getBiomeClass().getName());
                } else {
                    String name = biome.biomeName.toLowerCase();
                    if (!name.equalsIgnoreCase("chicken forest") && !name.equalsIgnoreCase("chicken plains")) {
                        loop++;
                    }
                }
            }
        }
        return loop;
    }
}
