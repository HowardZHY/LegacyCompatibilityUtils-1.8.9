package space.libs.interfaces;

import net.minecraft.util.ResourceLocation;

@SuppressWarnings("unused")
public interface IFMLControlledNamespacedRegistry {

    void AddObjectRaw(int id, ResourceLocation name, Object thing);

}
