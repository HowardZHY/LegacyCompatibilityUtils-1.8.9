package space.libs.mixins.entity;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.passive.EntityRabbit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowSuperConstructor;

@SuppressWarnings("all")
@Mixin(targets = "net.minecraft.entity.passive.EntityRabbit$AIAvoidEntity")
public class MixinEntityRabbitAIAvoidEntity<T extends Entity> extends MixinEntityAIAvoidEntity<T> {

    @Shadow
    private EntityRabbit entityInstance;

    @ShadowSuperConstructor
    public void EntityAIAvoidEntity(EntityCreature entity, Predicate avoidTargetSelectorIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {}

    @NewConstructor
    public void AIAvoidEntity(EntityRabbit rabbit, Predicate predicate, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
        this.EntityAIAvoidEntity(rabbit, predicate, avoidDistanceIn, farSpeedIn, nearSpeedIn);
        this.entityInstance = rabbit;
    }

}
