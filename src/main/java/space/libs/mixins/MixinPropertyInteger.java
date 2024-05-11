package space.libs.mixins;

import net.minecraft.block.properties.PropertyInteger;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("all")
@Mixin(PropertyInteger.class)
public class MixinPropertyInteger {
    public String func_177718_a(Integer value)
    {
        return value.toString();
    }
}
