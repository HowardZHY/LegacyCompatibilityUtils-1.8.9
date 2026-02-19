package space.libs.mixins.client.forge;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraftforge.fluids.FluidRegistry;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.client.ClientUtils;
import space.libs.util.cursedmixinextensions.annotations.Public;

@Mixin(value = FluidRegistry.class, remap = false)
public abstract class MixinFluidRegistryClient {

    @Public
    private static void onTextureStitchedPre(TextureMap map) {
        ClientUtils.onTextureStitchedPre(map);
    }
}
