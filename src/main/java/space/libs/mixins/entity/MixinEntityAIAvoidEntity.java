package space.libs.mixins.entity;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("all")
@Mixin(EntityAIAvoidEntity.class)
public class MixinEntityAIAvoidEntity<T extends Entity> {

    @ShadowConstructor
    public void EntityAIAvoidEntity(EntityCreature theEntityIn, Class<T> classToAvoidIn, Predicate <? super T > avoidTargetSelectorIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {}

    @NewConstructor
    public void EntityAIAvoidEntity(EntityCreature entity, Predicate avoidTargetSelectorIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
        this.EntityAIAvoidEntity(entity, (Class<T>) entity.getClass(), avoidTargetSelectorIn, avoidDistanceIn, farSpeedIn, nearSpeedIn);
    }

}
