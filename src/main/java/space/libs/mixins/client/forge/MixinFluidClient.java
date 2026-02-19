package space.libs.mixins.client.forge;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.interfaces.IFluid;

@SuppressWarnings("unused")
@Mixin(value = Fluid.class, remap = false)
public abstract class MixinFluidClient implements IFluid {

    public TextureAtlasSprite stillIcon;

    public TextureAtlasSprite flowingIcon;

    public Fluid This() {
        return (Fluid) (Object) this;
    }

    public Fluid setStillIcon(TextureAtlasSprite stillIcon) {
        this.stillIcon = stillIcon;
        return This();
    }

    public Fluid setFlowingIcon(TextureAtlasSprite flowingIcon) {
        this.flowingIcon = flowingIcon;
        return This();
    }

    public Fluid setIcons(TextureAtlasSprite stillIcon, TextureAtlasSprite flowingIcon) {
        this.setStillIcon(stillIcon);
        this.setFlowingIcon(flowingIcon);
        return This();
    }

    public Fluid setIcons(TextureAtlasSprite commonIcon) {
        this.setStillIcon(commonIcon);
        this.setFlowingIcon(commonIcon);
        return This();
    }

    public TextureAtlasSprite getIcon() {
        return this.getStillIcon();
    }

    public TextureAtlasSprite getStillIcon() {
        return this.stillIcon;
    }

    public TextureAtlasSprite getFlowingIcon() {
        return this.flowingIcon;
    }

    public TextureAtlasSprite getIcon(FluidStack stack) {
        return this.getIcon();
    }

    public TextureAtlasSprite getIcon(World world, BlockPos pos) {
        return this.getIcon();
    }
}
