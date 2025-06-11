package space.libs.mixins.entity;

import net.minecraft.entity.ai.EntityAITasks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = EntityAITasks.class, priority = 2100)
public abstract class MixinEntityAITasks {

    @Shadow
    private List<EntityAITasks.EntityAITaskEntry> executingTaskEntries;

    @Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
    private void canUse(EntityAITasks.EntityAITaskEntry taskEntry, CallbackInfoReturnable<Boolean> cir) {
        if (this.executingTaskEntries.isEmpty()) {
            cir.setReturnValue(true);
        }
    }
}
