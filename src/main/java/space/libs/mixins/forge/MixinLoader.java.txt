package space.libs.mixins.forge;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModClassLoader;
import net.minecraftforge.fml.common.registry.GameData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@Mixin(Loader.class)
public abstract class MixinLoader {

    @Shadow
    private ModClassLoader modClassLoader;

    @Shadow
    public void fireRemapEvent(Map<ResourceLocation, Integer[]> remapBlocks, Map<ResourceLocation, Integer[]> remapItems, boolean isFreezing) {}

    public ClassLoader getModClassLoader() {
        return this.modClassLoader;
    }

    public void fireRemapEvent(Map<String, Integer[]> remapBlocks, Map<String, Integer[]> remapItems) {
        throw new UnsupportedOperationException("Please load this save in 1.8 first !");
    }

    public List<String> fireMissingMappingEvent(LinkedHashMap<String, Integer> missingBlocks, LinkedHashMap<String, Integer> missingItems, boolean isLocalWorld, GameData gameData, Map<String, Integer[]> remapBlocks, Map<String, Integer[]> remapItems) {
        throw new UnsupportedOperationException("Please load this save in 1.8 first !");
    }
}
