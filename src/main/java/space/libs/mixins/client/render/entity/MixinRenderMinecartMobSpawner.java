package space.libs.mixins.client.render.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.entity.RenderMinecartMobSpawner;
import net.minecraft.entity.ai.EntityMinecartMobSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(RenderMinecartMobSpawner.class)
public abstract class MixinRenderMinecartMobSpawner {

    @Shadow
    protected void func_180560_a(EntityMinecartMobSpawner minecart, float partialTicks, IBlockState state) {}

    protected void func_177081_a(EntityMinecartMobSpawner minecart, float partialTicks, IBlockState state) {
        this.func_180560_a(minecart, partialTicks, state);
    }
}
