package space.libs.mixins.forge;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLModIdMappingEvent;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.Public;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

import java.util.*;

@SuppressWarnings("unused")
@Mixin(value = FMLModIdMappingEvent.class, remap = false)
public abstract class MixinFMLModIdMappingEvent {

    @ShadowConstructor
    public void FMLModIdMappingEvent(Map<ResourceLocation, Integer[]> blocks, Map<ResourceLocation, Integer[]> items, boolean isFrozen) {}

    @NewConstructor
    public void FMLModIdMappingEvent(Map<?, Integer[]> blocks, Map<?, Integer[]> items) {
        FMLModIdMappingEvent(convertMapKeys(blocks), convertMapKeys(items), false);
    }

    @Public
    private static Map<ResourceLocation, Integer[]> convertMapKeys(Map<?, Integer[]> originalMap) {
        Map<ResourceLocation, Integer[]> convertedMap = new HashMap<>();
        for (Map.Entry<?, Integer[]> entry : originalMap.entrySet()) {
            if (entry.getKey() instanceof java.lang.String) {
                ResourceLocation key = new ResourceLocation((String) entry.getKey());
                convertedMap.put(key, entry.getValue());
            } else if (entry.getKey() instanceof ResourceLocation) {
                convertedMap.put((ResourceLocation) entry.getKey(), entry.getValue());
            } else {
                throw new IllegalArgumentException();
            }
        }
        return convertedMap;
    }
}
