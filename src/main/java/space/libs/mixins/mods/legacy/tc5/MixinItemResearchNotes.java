package space.libs.mixins.mods.legacy.tc5;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thaumcraft.common.items.resources.ItemResearchNotes;

@SuppressWarnings("all")
@Pseudo
@Mixin(value = ItemResearchNotes.class, remap = false, priority = 1100)
public abstract class MixinItemResearchNotes extends Item {

    /**
     * @author HowardZHY
     * @reason Fix Crash
     */
    @Inject(method = "func_77667_c", at = @At("HEAD"), cancellable = true)
    public void func_77667_c(ItemStack stack, CallbackInfoReturnable<String> cir) {
        int damage = stack.getItemDamage();
        if (damage < 0 || damage >= 2) {
            cir.setReturnValue(super.getUnlocalizedName());
        }
    }
}
