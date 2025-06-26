package space.libs.mixins.client.forge;

import com.google.common.collect.BiMap;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.interfaces.IFluid;
import space.libs.util.cursedmixinextensions.annotations.Public;

@Mixin(value = FluidRegistry.class, remap = false)
public abstract class MixinFluidRegistryClient {

    @Shadow
    static BiMap<String, Fluid> fluids;

    @Public
    private static void onTextureStitchedPre(TextureMap map) {
        for (Fluid fluid : fluids.values()) {
            IFluid accessor = (IFluid) fluid;
            if (fluid.getStill() != null) {
                TextureAtlasSprite still = map.registerSprite(fluid.getStill());
                accessor.setStillIcon(still);
            }
            if (fluid.getFlowing() != null) {
                TextureAtlasSprite flowing = map.registerSprite(fluid.getFlowing());
                accessor.setStillIcon(flowing);
            }
        }
    }
}
