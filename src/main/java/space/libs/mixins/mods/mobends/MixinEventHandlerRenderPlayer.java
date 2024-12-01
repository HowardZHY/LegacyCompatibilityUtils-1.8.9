package space.libs.mixins.mods.mobends;

import net.gobbob.mobends.event.EventHandler_RenderPlayer;
import net.minecraftforge.client.event.RenderLivingEvent;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(EventHandler_RenderPlayer.class)
public class MixinEventHandlerRenderPlayer {

    @Shadow(remap = false)
    public static float partialTicks;

    private RenderLivingEvent.Pre<?> Event;

    @Dynamic
    @Inject(method = "onPlayerRender", at = @At("HEAD"), remap = false)
    public void onPlayerRender(RenderLivingEvent.Pre<?> event, CallbackInfo ci) {
        this.Event = event;
    }

    @Dynamic
    @ModifyArg(method = "onPlayerRender",
        at = @At(
            value = "INVOKE",
            target = "Lnet/gobbob/mobends/client/renderer/entity/RenderBendsPlayer;func_76986_a(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V"
        ),
        index = 4, remap = false)
    public float onPlayerRender(float p_76986_8_) {
        return (Event.entity.prevRotationYaw + (Event.entity.rotationYaw - Event.entity.prevRotationYaw) * partialTicks);
    }
}
