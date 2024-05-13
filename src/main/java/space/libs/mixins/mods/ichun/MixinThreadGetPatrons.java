package space.libs.mixins.mods.ichun;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

@Pseudo
@Mixin(targets = "us.ichun.mods.ichunutil.client.thread.ThreadGetPatrons", remap = false)
public class MixinThreadGetPatrons {
    /**
     * @author HowardZHY
     * @reason Optimize
     */
    @Overwrite
    public void run() {}
}
