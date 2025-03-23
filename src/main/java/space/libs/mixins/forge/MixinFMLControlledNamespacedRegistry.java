package space.libs.mixins.forge;

import net.minecraft.util.RegistryNamespacedDefaultedByKey;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.FMLControlledNamespacedRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(value = FMLControlledNamespacedRegistry.class, remap = false)
public class MixinFMLControlledNamespacedRegistry<K, V> extends RegistryNamespacedDefaultedByKey<K, Object> {

    public MixinFMLControlledNamespacedRegistry(K defaultValueKeyIn) {
        super(defaultValueKeyIn);
    }

    @Shadow
    public Object getObject(ResourceLocation name) {
        throw new AbstractMethodError();
    }

    @Shadow
    public boolean containsKey(ResourceLocation name) {
        throw new AbstractMethodError();
    }

    @Shadow
    public int getId(ResourceLocation itemName) {
        throw new AbstractMethodError();
    }

    @Override
    public Object getObject(K name) {
        if (name instanceof java.lang.String) {
            return this.getObject(new ResourceLocation((java.lang.String) name));
        }
        return this.getObject((ResourceLocation) name);
    }

    @Override
    public boolean containsKey(K key) {
        if (key instanceof java.lang.String) {
            return this.containsKey(new ResourceLocation((java.lang.String) key));
        }
        return this.containsKey((ResourceLocation) key);
    }

    public Object get(int id) {
        return getObjectById(id);
    }

    public Object get(String name) {
        return getObject(new ResourceLocation(name));
    }

    public int getId(String itemName) {
        return getId(new ResourceLocation(itemName));
    }

    public boolean contains(String itemName) {
        return containsKey(new ResourceLocation(itemName));
    }
}
