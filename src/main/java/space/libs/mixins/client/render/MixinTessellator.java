package space.libs.mixins.client.render;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.libs.interfaces.ITessellator;

@SuppressWarnings("unused")
@Mixin(value = Tessellator.class, priority = 100)
public abstract class MixinTessellator implements ITessellator {

    @Shadow
    private WorldRenderer worldRenderer;

    @Shadow
    public abstract void draw();

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(int bufferSize, CallbackInfo ci) {
        this.field_178183_a = new BufferBuilder(bufferSize);
        this.worldRenderer = func_178180_c();
    }

    /** buffer */
    public BufferBuilder field_178183_a;

    /** draw */
    public int func_78381_a() {
        this.draw();
        return 0;
        // return this.worldRenderer.getBufferSize() * 4; TODO?
    }

    /** getBuffer */
    public BufferBuilder func_178180_c() {
        return this.field_178183_a;
    }
}
