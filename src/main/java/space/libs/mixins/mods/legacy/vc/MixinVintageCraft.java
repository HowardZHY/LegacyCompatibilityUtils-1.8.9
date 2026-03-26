package space.libs.mixins.mods.legacy.vc;

import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;
import space.libs.interfaces.IEntityPlayer;

@SuppressWarnings("all")
@Pseudo
@Mixin(targets = "at.tyron.vintagecraft.VintageCraft", remap = false)
public abstract class MixinVintageCraft {

    /**
     * @author HowardZHY
     * @reason Fix Legacy ReflectionHelper Usage
     */
    @Overwrite
    public int getSleepTimer(EntityPlayer player) {
        return ((IEntityPlayer) player).GetSleepTimer();
    }
}
