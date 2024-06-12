package space.libs.mixins.forge;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = EventBus.class, remap = false)
public abstract class MixinEventBus implements IEventBus {

    @Override
    @Shadow
    public void register(Object target) {}

    @Override
    @Shadow
    public void unregister(Object object) {}

}
