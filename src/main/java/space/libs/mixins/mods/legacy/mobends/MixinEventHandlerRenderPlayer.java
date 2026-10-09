package space.libs.mixins.mods.legacy.mobends;

import net.gobbob.mobends.event.EventHandler_RenderPlayer;
import net.minecraftforge.client.event.RenderLivingEvent;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("all")
@Pseudo
@Mixin(value = EventHandler_RenderPlayer.class, remap = false)
public class MixinEventHandlerRenderPlayer {

    @Dynamic
    @Inject(method = "onPlayerRender", at = @At("HEAD"), remap = false, cancellable = true)
    public void OnPlayerRender(RenderLivingEvent.Pre<?> event, CallbackInfo ci) {
        ci.cancel();
    }

}
