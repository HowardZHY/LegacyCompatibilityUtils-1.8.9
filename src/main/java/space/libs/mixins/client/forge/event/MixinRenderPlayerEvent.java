package space.libs.mixins.client.forge.event;

import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(value = RenderPlayerEvent.class, remap = false)
public abstract class MixinRenderPlayerEvent {

    @Final
    @Mutable
    @Shadow
    public RenderPlayer renderer;

    @Final
    @Mutable
    @Shadow
    public float partialRenderTick;

    @Final
    @Mutable
    @Shadow
    public double x;

    @Final
    @Mutable
    @Shadow
    public double y;

    @Final
    @Mutable
    @Shadow
    public double z;

    public RenderPlayer getRenderer() {
        return renderer;
    }

    public float getPartialRenderTick() {
        return partialRenderTick;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
