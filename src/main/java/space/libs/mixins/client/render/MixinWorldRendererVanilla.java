package space.libs.mixins.client.render;

import net.minecraft.client.renderer.*;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldRenderer.class)
public abstract class MixinWorldRendererVanilla {

    @Dynamic
    @Redirect(method = "func_181662_b", at = @At(value = "FIELD", target = "*:[I", ordinal = 0), remap = false)
    public int[] pos() {
        return WorldRenderer_2.field_181661_a;
    }
}
