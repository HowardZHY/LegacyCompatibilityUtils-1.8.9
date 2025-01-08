package space.libs.mixins.mods.cs;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;

@Pseudo
@Mixin(targets = "sushen.eieae", remap = false)
public abstract class MixinCameraStudioRenderCameraFOV extends MixinCameraStudioRenderCamera {

    public void func_181560_a(float partialTicks, long nanoTime) {
        this.func_78480_b(partialTicks);
    }

    @Dynamic
    @Shadow(aliases = "b", remap = false)
    public void func_78480_b(float pt) {}

}
