package space.libs.mixins.entity;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityWolf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.mixins.entity.MixinEntityAIAvoidEntity;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;

@SuppressWarnings("all")
@Mixin(targets = "net.minecraft.entity.passive.EntityRabbit$AIAvoidEntity")
public class MixinEntityRabbitAIAvoidEntity<T extends Entity> extends MixinEntityAIAvoidEntity {

    @Shadow
    private EntityRabbit entityInstance;

    @NewConstructor
    public void EntityRabbitAIAvoidEntity(EntityRabbit entity, Predicate predicate, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
        super.EntityAIAvoidEntity(entity, EntityWolf.class, predicate, avoidDistanceIn, farSpeedIn, nearSpeedIn);
        this.entityInstance = entity;
    }

}
