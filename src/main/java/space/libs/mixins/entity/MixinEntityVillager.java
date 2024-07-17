package space.libs.mixins.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityVillager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(EntityVillager.class)
public abstract class MixinEntityVillager {

    @Shadow
    public abstract EntityVillager createChild(EntityAgeable ageable);

    public EntityVillager func_180488_b(EntityAgeable ageable) {
        return this.createChild(ageable);
    }
}
