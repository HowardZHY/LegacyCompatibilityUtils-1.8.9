package space.libs.mixins.forge;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.lang.reflect.Method;

@Mixin(value = EventBus.class, priority = 100, remap = false)
public abstract class MixinEventBus implements IEventBus {

    @Shadow
    public void register(Object target) {}

    @Shadow
    public void unregister(Object object) {}

    @Redirect(method = "register(Ljava/lang/Object;)V", at = @At(value = "INVOKE", target = "Ljava/lang/reflect/Method;isAnnotationPresent(Ljava/lang/Class;)Z"))
    public boolean redirect(Method real, Class<?> c) {
        return (real.isAnnotationPresent(net.minecraftforge.fml.common.eventhandler.SubscribeEvent.class) ||
            real.isAnnotationPresent(net.minecraftforge.eventbus.api.SubscribeEvent.class));
    }
}
