package space.libs.mixins.mods.cs;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.mixins.client.render.entity.MixinEntityRenderer;

@Pseudo
@Mixin(targets = "sushen.ioaia", remap = false)
public abstract class MixinCameraStudioRenderCamera extends MixinEntityRenderer {

    @Dynamic
    @Shadow(aliases = "b", remap = false)
    public void func_78480_b(float pt) {}

}
