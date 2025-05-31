package space.libs.mixins.forge;

import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.PersistentRegistryManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Arrays;

@Mixin(value = PersistentRegistryManager.class, remap = false)
public class MixinPersistentRegistryManager {

    @Redirect(method = "injectSnapshot", at = @At(value = "INVOKE", target = "Ljava/util/Arrays;fill([Ljava/lang/Object;Ljava/lang/Object;)V"))
    private static void injectSnapshot(Object[] objects, Object o) {
        Arrays.fill(Potion.potionTypes, 0, 32, null);
    }
}
