package space.libs.mixins;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.IStringSerializable;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("all")
@Mixin(PropertyEnum.class)
public class MixinPropertyEnum {
    public String func_177705_a(Enum value) {
        return ((IStringSerializable)value).getName();
    }
}
