package space.libs.mixins.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityOcelot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(EntityOcelot.class)
public abstract class MixinEntityOcelot {

    @Shadow
    public abstract EntityOcelot createChild(EntityAgeable ageable);

    public EntityOcelot func_180493_b(EntityAgeable ageable) {
        return this.createChild(ageable);
    }
}
