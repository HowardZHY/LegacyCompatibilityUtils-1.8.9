package space.libs.util.forge;

import net.minecraft.util.ResourceLocation;

import java.util.*;

@SuppressWarnings("unused")
public class ResourcesLocationUtils {

    public static Map<ResourceLocation, ResourceLocation> convertMap(Map<String, String> originalMap) {
        Map<ResourceLocation, ResourceLocation> convertedMap = new HashMap<>();
        if (originalMap == null) {
            return convertedMap;
        }
        for (Map.Entry<String, String> entry : originalMap.entrySet()) {
            convertedMap.put(new ResourceLocation(entry.getKey()), new ResourceLocation(entry.getValue()));
        }
        return convertedMap;
    }

    public static <T> Map<ResourceLocation, T> convertMapKeys(Map<?, T> originalMap) {
        Map<ResourceLocation, T> convertedMap = new HashMap<>();
        if (originalMap == null) {
            return convertedMap;
        }
        for (Map.Entry<?, T> entry : originalMap.entrySet()) {
            if (entry.getKey() instanceof String) {
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

    public static <T> ResourceLocation convertNullable(T thing) {
        if (thing == null) {
            return null;
        } else if (thing instanceof ResourceLocation) {
            return (ResourceLocation) thing;
        } else {
            return new ResourceLocation(thing.toString());
        }
    }

    public static Set<ResourceLocation> convertSet(Set<String> originalSet) {
        Set<ResourceLocation> convertedSet = new HashSet<>();
        if (originalSet == null) {
            return convertedSet;
        }
        for (String value : originalSet) {
            convertedSet.add(new ResourceLocation(value));
        }
        return convertedSet;
    }
}
