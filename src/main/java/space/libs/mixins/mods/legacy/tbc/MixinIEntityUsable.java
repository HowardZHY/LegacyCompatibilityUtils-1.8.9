package space.libs.mixins.mods.legacy.tbc;

import com.superdextor.thinkbigcore.items.IEntityUsable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@SuppressWarnings({"OverwriteModifiers", "UnusedMixin"})
@Mixin(value = IEntityUsable.class, remap = false)
public interface MixinIEntityUsable {

    /**
     * @author HowardZHY
     * @reason Prevent Abstract Method
     */
    @Overwrite
    default boolean isRangedWeapon() {
        return false;
    }
}
