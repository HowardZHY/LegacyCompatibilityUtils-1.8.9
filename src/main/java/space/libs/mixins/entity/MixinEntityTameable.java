package space.libs.mixins.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(EntityTameable.class)
public abstract class MixinEntityTameable {

    @Shadow
    public abstract EntityLivingBase getOwner();

    public EntityLivingBase func_180492_cm() {
        return this.getOwner();
    }
}
