package space.libs.mixins.mods.mobends;

import net.gobbob.mobends.data.EntityData;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = EntityData.class, remap = false)
public abstract class MixinEntityData {

    //implements IEntityData

}
