package space.libs.mixins.client.forge;

import net.minecraftforge.client.model.ModelLoader;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ModelLoader.class)
public class MixinModelLoader {

    @Redirect(
        method = "onPostBakeEvent",
        at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;error(Ljava/lang/String;Ljava/lang/Throwable;)V"),
        remap = false
    )
    public void onPostBakeEvent(Logger instance, String msg, Throwable t) {
        instance.warn(msg + ": " + t.getMessage());
        StackTraceElement[] stack = t.getStackTrace();
        String trace1 = stack.length > 0 ? stack[0].toString() : "No Stack";
        String trace2 = stack.length > 1 ? stack[1].toString() : "";
        instance.warn("at " + trace1);
        instance.warn("at " + trace2 + " ...");
    }
}
