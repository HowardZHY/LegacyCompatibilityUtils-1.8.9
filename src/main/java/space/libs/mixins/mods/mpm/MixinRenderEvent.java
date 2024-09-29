package space.libs.mixins.mods.mpm;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderLivingEvent;
import noppes.mpm.ModelData;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "noppes.mpm.client.RenderEvent", remap = false)
public class MixinRenderEvent {

    private static boolean LEGACY;

    @SuppressWarnings("rawtypes")
    @Dynamic
    @Inject(method = "pre", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/renderer/entity/RenderManager;func_147937_a(Lnet/minecraft/entity/Entity;F)Z"))
    public void pre(RenderLivingEvent.Pre event, CallbackInfo ci) {
        if (LEGACY) {
            GL11.glPopMatrix();
        }
    }

    /**
     * @author HowardZHY
     * @reason Remove the Lag when loading player skins
     * */
    @Overwrite
    public void loadPlayerResource(EntityPlayer player, ModelData data) {}

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void check(CallbackInfo ci) {
        LEGACY = false;
        try {
            Class<?> c = Class.forName("noppes.mpm.client.model.part.tails.ModelCanineTail");
        } catch (Exception ignored) {
            LEGACY = true;
        }
    }
}
