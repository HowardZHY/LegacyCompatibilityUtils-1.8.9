package space.libs.mixins.forge;

import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.ASMEventHandler;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.IEventListener;
import org.apache.logging.log4j.ThreadContext;
import org.objectweb.asm.Type;
import org.spongepowered.asm.mixin.*;
import space.libs.util.cursedmixinextensions.annotations.ReplaceConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowSuperConstructor;

import java.lang.reflect.Method;

@Mixin(value = ASMEventHandler.class, priority = 100, remap = false)
public abstract class MixinASMEventHandler {

    @Shadow
    private static @Final boolean GETCONTEXT;

    @Shadow
    @Mutable
    private @Final IEventListener handler;

    @Shadow
    @Mutable
    private @Final net.minecraftforge.fml.common.eventhandler.SubscribeEvent subInfo;

    @Shadow
    private ModContainer owner;

    @Shadow
    private String readable;

    @Shadow
    public abstract Class<?> createWrapper(Method callback);

    @ShadowSuperConstructor
    public void Object() {}

    @ReplaceConstructor
    public void ASMEventHandler(Object target, Method method, ModContainer owner) throws Exception {
        Object();
        this.owner = owner;
        this.handler = (IEventListener)createWrapper(method).getConstructor(Object.class).newInstance(target);
        this.subInfo = method.getAnnotation(net.minecraftforge.fml.common.eventhandler.SubscribeEvent.class);
        this.readable = "ASM: " + target + " " + method.getName() + Type.getMethodDescriptor(method);
        this.newSubInfo = method.getAnnotation(net.minecraftforge.eventbus.api.SubscribeEvent.class);
    }

    public net.minecraftforge.eventbus.api.SubscribeEvent newSubInfo;

    /**
     * @author HowardZHY
     * @reason Prevent NPE
     */
    @Overwrite
    public void invoke(Event event) {
        if (GETCONTEXT) ThreadContext.put("mod", owner == null ? "" : owner.getName());
        if (handler != null) {
            if (!event.isCancelable() || !event.isCanceled()) {
                handler.invoke(event);
                if (GETCONTEXT) ThreadContext.remove("mod");
                return;
            } else if (subInfo != null && subInfo.receiveCanceled()) {
                handler.invoke(event);
                if (GETCONTEXT) ThreadContext.remove("mod");
                return;
            } else if (newSubInfo != null && newSubInfo.receiveCanceled()) {
                handler.invoke(event);
            }
        }
        if (GETCONTEXT) ThreadContext.remove("mod");
    }

    /**
     * @author HowardZHY
     * @reason Prevent NPE
     */
    @Overwrite
    public EventPriority getPriority() {
        if (subInfo != null) {
            return subInfo.priority();
        }
        return EventPriority.NORMAL; // Lazy
    }
}
