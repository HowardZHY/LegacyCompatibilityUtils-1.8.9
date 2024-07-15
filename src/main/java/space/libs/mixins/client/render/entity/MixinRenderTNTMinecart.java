package space.libs.mixins.client.render.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.entity.RenderTntMinecart;
import net.minecraft.entity.item.EntityMinecartTNT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderTntMinecart.class)
public class MixinRenderTNTMinecart {

    @Shadow
    protected void func_180560_a(EntityMinecartTNT minecart, float partialTicks, IBlockState state) {}

    protected void func_180561_a(EntityMinecartTNT minecart, float partialTicks, IBlockState state) {
        this.func_180560_a(minecart, partialTicks, state);
    }
}
