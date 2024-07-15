package space.libs.mixins;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.Public;

import java.util.Iterator;
import java.util.Map;

@SuppressWarnings("unused")
@Mixin(Enchantment.class)
public abstract class MixinEnchantment {

    @Final
    @Shadow
    private static Map<ResourceLocation, Enchantment> locationEnchantments;

    @Public
    private static String[] func_180304_c() {
        String[] astring = new String[locationEnchantments.size()];
        int i = 0;
        ResourceLocation resourcelocation;
        for (Iterator<ResourceLocation> iterator = locationEnchantments.keySet().iterator(); iterator.hasNext(); astring[i++] = resourcelocation.toString()) {
            resourcelocation = iterator.next();
        }
        return astring;
    }
}
