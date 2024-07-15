package space.libs.mixins.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntitySheep;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(EntitySheep.class)
public abstract class MixinEntitySheep {

    @Shadow
    public abstract EntitySheep createChild(EntityAgeable ageable);

    public EntitySheep func_180491_b(EntityAgeable ageable) {
        return this.createChild(ageable);
    }
}
