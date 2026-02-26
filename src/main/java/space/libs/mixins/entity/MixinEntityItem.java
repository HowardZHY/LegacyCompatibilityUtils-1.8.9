package space.libs.mixins.entity;

import net.minecraft.entity.item.EntityItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.libs.util.ModDetector;

import java.lang.reflect.Method;

@Mixin(value = EntityItem.class, priority = 3800)
public abstract class MixinEntityItem extends MixinEntity {

    private static Method itemGroupHook;

    @Inject(method = "searchForOtherItemsNearby", at = @At("HEAD"), cancellable = true)
    private void searchForOtherItemsNearby(CallbackInfo ci) {
        if (shouldCancelMerge()) {
            ci.cancel();
        }
    }

    @SuppressWarnings("SpellCheckingInspection")
    private static boolean shouldCancelMerge() {
        if (!ModDetector.hasAllTheItems) {
            return false;
        }
        try {
            if (itemGroupHook == null) {
                Class<?> c = Class.forName("net.fybertech.alltheitems.AllTheItems");
                itemGroupHook = c.getDeclaredMethod("itemGroupHook");
            }
            return !((boolean) itemGroupHook.invoke(null));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
