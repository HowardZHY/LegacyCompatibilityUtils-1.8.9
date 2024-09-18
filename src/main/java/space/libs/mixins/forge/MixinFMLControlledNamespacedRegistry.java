package space.libs.mixins.forge;

import net.minecraft.util.RegistryNamespacedDefaultedByKey;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.FMLControlledNamespacedRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

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
}
