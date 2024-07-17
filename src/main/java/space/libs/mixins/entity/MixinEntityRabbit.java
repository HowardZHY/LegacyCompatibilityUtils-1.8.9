package space.libs.mixins.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityRabbit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(EntityRabbit.class)
public abstract class MixinEntityRabbit {

    @Shadow
    public abstract EntityRabbit createChild(EntityAgeable ageable);

    public EntityRabbit func_175526_b(EntityAgeable ageable) {
        return this.createChild(ageable);
    }
}
