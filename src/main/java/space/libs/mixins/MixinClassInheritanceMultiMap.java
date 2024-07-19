package space.libs.mixins;

import net.minecraft.util.ClassInheritanceMultiMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(ClassInheritanceMultiMap.class)
public abstract class MixinClassInheritanceMultiMap {

    @Shadow
    protected abstract Class<?> initializeClassLookup(Class<?> clazz);

    public Class<?> func_180212_a(Class<?> c, boolean flag) {
        return this.initializeClassLookup(c);
    }

}
