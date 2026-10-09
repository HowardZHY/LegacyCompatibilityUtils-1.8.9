package space.libs.mixins;

import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("all")
@Mixin(
    targets = {
        "net.minecraft.enchantment.EnchantmentHelper$DamageIterator",
        "net.minecraft.enchantment.EnchantmentHelper$HurtIterator",
        "net.minecraft.enchantment.EnchantmentHelper$ModifierDamage",
        "net.minecraft.enchantment.EnchantmentHelper$ModifierLiving"
    },
    remap = false
)
public abstract class MixinEnchantmentHelper {

    @ShadowConstructor
    private void EnchantmentHelper() {}

    @NewConstructor
    private void EnchantmentHelper(Object o) {
        this.EnchantmentHelper();
    }
}
