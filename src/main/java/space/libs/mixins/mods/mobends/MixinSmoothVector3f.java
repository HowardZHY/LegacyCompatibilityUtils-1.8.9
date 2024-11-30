package space.libs.mixins.mods.mobends;

import net.gobbob.mobends.util.SmoothVector3f;
import org.lwjgl.util.vector.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.mods.ISmoothVector3f;

@SuppressWarnings("all")
@Pseudo
@Mixin(value = SmoothVector3f.class, remap = false)
public class MixinSmoothVector3f implements ISmoothVector3f {

    @Shadow
    public Vector3f completion;

    @Shadow
    public Vector3f vFinal;

    @Shadow
    public Vector3f vOld;

    @Shadow
    public Vector3f vSmooth;

    public void set(float _x, float _y, float _z) {
        this.vOld = new Vector3f(_x, _y, _z);
        this.vSmooth = vOld;
        this.vFinal = vOld;
        this.completion = new Vector3f(1.0f, 1.0f, 1.0f);
    }

    public void finish() {
        this.set(this.vFinal.x, this.vFinal.y, this.vFinal.z);
    }
}
