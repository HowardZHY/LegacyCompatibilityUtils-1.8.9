package space.libs.mixins.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.Public;

import static net.minecraft.entity.EntityList.classToStringMapping;

@SuppressWarnings("all")
@Mixin(EntityList.class)
public class MixinEntityList {

    /** getKey */
    @Public
    private static ResourceLocation func_191301_a(Entity entity) {
        return func_191306_a(entity.getClass());
    }

    @Public
    private static ResourceLocation func_191306_a(Class<? extends Entity> entityclass) {
        return new ResourceLocation((String)classToStringMapping.get(entityclass));
    }
}
