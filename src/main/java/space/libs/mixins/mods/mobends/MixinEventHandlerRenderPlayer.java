package space.libs.mixins.mods.mobends;

import net.gobbob.mobends.AnimatedEntity;
import net.gobbob.mobends.client.renderer.entity.RenderBendsPlayer;
import net.gobbob.mobends.event.EventHandler_RenderPlayer;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderLivingEvent;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(EventHandler_RenderPlayer.class)
public class MixinEventHandlerRenderPlayer {

    @Shadow(remap = false)
    public static float partialTicks;

    @Dynamic
    @Inject(method = "onPlayerRender", at = @At("HEAD"), remap = false, cancellable = true)
    public void onPlayerRender(RenderLivingEvent.Pre<?> event, CallbackInfo ci) {
        if (event.entity instanceof EntityPlayer) {
            if (!(event.renderer instanceof RenderBendsPlayer)) {
                if (AnimatedEntity.getByEntity(event.entity).animate) {
                    AbstractClientPlayer player = (AbstractClientPlayer)event.entity;
                    event.setCanceled(true);
                    AnimatedEntity.getPlayerRenderer(player).func_76986_a(player, event.x, event.y, event.z, (event.entity.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * partialTicks), partialTicks);
                }
            }
        }
        ci.cancel();
    }
}
