package space.libs.interfaces;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.fluids.Fluid;

@SuppressWarnings("UnusedReturnValue")
public interface IFluid {

    Fluid setStillIcon(TextureAtlasSprite stillIcon);

    Fluid setFlowingIcon(TextureAtlasSprite flowingIcon);
}
