package space.libs.mixins.mods.mobends;

import net.gobbob.mobends.event.EventHandler_RenderPlayer;
import net.minecraftforge.client.event.RenderLivingEvent;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("all")
@Pseudo
@Mixin(EventHandler_RenderPlayer.class)
public class MixinEventHandlerRenderPlayer {

    @Shadow(remap = false)
    public static float partialTicks;

    @Dynamic
    @Inject(method = "onPlayerRender", at = @At("HEAD"), remap = false, cancellable = true)
    public void onPlayerRender(RenderLivingEvent.Pre<?> event, CallbackInfo ci) {
        ci.cancel();
    }

}
