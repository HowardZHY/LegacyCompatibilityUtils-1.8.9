package space.libs.mixins.entity;

import net.minecraft.entity.DataWatcher;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.libs.CompatLib;

import java.util.Map;

@Mixin(value = DataWatcher.class, priority = 10)
public abstract class MixinDataWatcher {

    @Shadow
    private @Final Map<Integer, DataWatcher.WatchableObject> watchedObjects;

    /**
     * @author HowardZHY
     * @reason Prevent Crash
     */
    @Inject(method = "addObject", at = @At(value = "INVOKE", target = "Ljava/util/Map;containsKey(Ljava/lang/Object;)Z"), cancellable = true)
    public <T> void addObject(int id, T object, CallbackInfo ci) {
        if (this.watchedObjects.containsKey(id)) {
            CompatLib.LOGGER.warn("Duplicate id value for " + id + "!");
            ci.cancel();
        }
    }
}
