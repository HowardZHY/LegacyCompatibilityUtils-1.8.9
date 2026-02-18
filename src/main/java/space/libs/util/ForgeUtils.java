package space.libs.util;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.minecraftforge.fml.common.versioning.Restriction;

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

    public static boolean checkVersion(Restriction restriction, ArtifactVersion version) {
        if (version.getVersionString().contains("1.8")) {
            return true;
        }
        return restriction.containsVersion(version);
    }

    public static ModMetadata createModData(String modID, String name, String version, String author) {
        ModMetadata data = new ModMetadata();
        data.modId = modID;
        data.name = name;
        data.version = version;
        data.authorList.add(author);
        return data;
    }
}
