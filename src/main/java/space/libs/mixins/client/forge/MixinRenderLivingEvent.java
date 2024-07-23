package space.libs.mixins.client.forge;

import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(value = RenderLivingEvent.class, remap = false)
public abstract class MixinRenderLivingEvent<T extends EntityLivingBase> {

    @Final
    @Mutable
    @Shadow
    public EntityLivingBase entity;

    @Final
    @Mutable
    @Shadow
    public RendererLivingEntity<T> renderer;

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

    public EntityLivingBase getPartialRenderTick() {
        return entity;
    }

    public RendererLivingEntity<T> getRenderer() {
        return renderer;
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
