package space.libs.mixins.util;

import net.minecraft.util.Vec3i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(Vec3i.class)
public abstract class MixinVec3i {

    @Shadow
    public abstract int compareTo(Vec3i vec);

    public int func_177953_g(Vec3i vec) {
        return this.compareTo(vec);
    }
}
