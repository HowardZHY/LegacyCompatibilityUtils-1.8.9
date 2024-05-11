package space.libs.mixins;

import net.minecraft.block.properties.PropertyBool;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("all")
@Mixin(PropertyBool.class)
public class MixinPropertyBool {
    public String func_177715_a(Boolean b) {
        return b.toString();
    }
}
