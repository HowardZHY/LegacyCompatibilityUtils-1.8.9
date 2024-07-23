package space.libs.mixins.client.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unused")
@Mixin(value = Tessellator.class, priority = 100)
public abstract class MixinTessellator {

    @Shadow
    private WorldRenderer worldRenderer;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(int bufferSize, CallbackInfo ci) {
        this.field_178183_a = new VertexBuffer(bufferSize);
        this.worldRenderer = func_178180_c();
    }

    @Shadow
    public abstract void draw();

    /** worldRenderer */
    public VertexBuffer field_178183_a;

    /** draw */
    public int func_78381_a() {
        this.draw();
        return 0;
    }

    /** getBuffer */
    public VertexBuffer func_178180_c() {
        return this.field_178183_a;
    }

}
