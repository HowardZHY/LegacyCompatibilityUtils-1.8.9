package space.libs.util;

import net.minecraft.util.ResourceLocation;

import java.util.*;

@SuppressWarnings("unused")
public abstract class ForgeUtils {

    public static Map<ResourceLocation, Integer[]> convertMapKeys(Map<?, Integer[]> originalMap) {
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
