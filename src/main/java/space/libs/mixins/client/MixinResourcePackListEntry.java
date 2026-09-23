package space.libs.mixins.client;

import net.minecraft.client.resources.ResourcePackListEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ResourcePackListEntry.class)
public abstract class MixinResourcePackListEntry {

    /**
     * @author HowardZHY
     * @reason default
     */
    @SuppressWarnings("OverwriteModifiers")
    @Overwrite
    public int func_183019_a() {
        return 1;
    }
}
