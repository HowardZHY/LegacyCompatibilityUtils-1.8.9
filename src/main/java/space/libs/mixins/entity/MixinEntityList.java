package space.libs.mixins.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import space.libs.util.cursedmixinextensions.annotations.Public;

import static net.minecraft.entity.EntityList.classToStringMapping;

@SuppressWarnings("unused")
@Mixin(EntityList.class)
public class MixinEntityList {

    /**
     * @reason Restore old behavior that forge didn't restrict
     */
    @ModifyConstant(method = "addMapping(Ljava/lang/Class;Ljava/lang/String;I)V", constant = @Constant(intValue = 255))
    private static int addMapping(int constant) {
        return 4095;
    }

    /** getKey */
    @Public
    private static ResourceLocation func_191301_a(Entity entity) {
        return func_191306_a(entity.getClass());
    }

    @Public
    private static ResourceLocation func_191306_a(Class<? extends Entity> entityclass) {
        return new ResourceLocation(classToStringMapping.get(entityclass));
    }
}
