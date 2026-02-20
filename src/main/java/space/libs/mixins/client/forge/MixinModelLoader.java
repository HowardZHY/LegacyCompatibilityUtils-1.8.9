package space.libs.mixins.client.forge;

import net.minecraftforge.client.model.ModelLoader;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@SuppressWarnings("unused")
@Mixin(value = ModelLoader.class, priority = 900, remap = false)
public class MixinModelLoader {

    @Redirect(
        method = "onPostBakeEvent",
        at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;error(Ljava/lang/String;Ljava/lang/Throwable;)V")
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
