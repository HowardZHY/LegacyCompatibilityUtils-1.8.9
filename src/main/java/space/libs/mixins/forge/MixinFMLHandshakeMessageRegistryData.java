package space.libs.mixins.forge;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.handshake.FMLHandshakeMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = FMLHandshakeMessage.RegistryData.class, remap = false)
public class MixinFMLHandshakeMessageRegistryData {

    @Shadow
    private ResourceLocation name;

    public String getName() {
        return this.name.toString();
    }
}
