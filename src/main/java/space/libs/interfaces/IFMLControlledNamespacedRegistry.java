package space.libs.interfaces;

import net.minecraft.util.ResourceLocation;

import java.util.Map;

@SuppressWarnings("unused")
public interface IFMLControlledNamespacedRegistry {

    void serializeInto(Map<String, Integer> idMapping);

    void AddObjectRaw(int id, ResourceLocation name, Object thing);

}
