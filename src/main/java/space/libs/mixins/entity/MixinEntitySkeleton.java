package space.libs.mixins.entity;

import net.minecraft.entity.monster.EntitySkeleton;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.MappedName;

@SuppressWarnings("unused")
@Mixin(EntitySkeleton.class)
public class MixinEntitySkeleton {

    @MappedName("isSwingingArms")
    public boolean func_184725_db() {
        return false;
    }
}
