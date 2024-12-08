package space.libs.mixins.forge;

import net.minecraftforge.fml.common.eventhandler.ASMEventHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = ASMEventHandler.class, remap = false)
public class MixinASMEventHandler {

    @Final
    @Shadow
    private SubscribeEvent subInfo;

    /**
     * @author HowardZHY
     * @reason Prevent NPE
     */
    @Overwrite
    public EventPriority getPriority() {
        if (subInfo != null) {
            return subInfo.priority();
        }
        return EventPriority.NORMAL;
    }
}
