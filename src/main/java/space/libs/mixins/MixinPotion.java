package space.libs.mixins;

import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.Public;

import java.util.Iterator;
import java.util.Map;

@SuppressWarnings("unused")
@Mixin(Potion.class)
public class MixinPotion {

    @Shadow
    private static @Final Map<ResourceLocation, Potion> field_180150_I;

    @Public
    private static String[] func_180141_c() {
        String[] str = new String[field_180150_I.size()];
        int i = 0;
        ResourceLocation loc;
        for (Iterator<ResourceLocation> var2 = field_180150_I.keySet().iterator(); var2.hasNext(); str[i++] = loc.toString()) {
            loc = var2.next();
        }
        return str;
    }
}
