package space.libs.mixins.client.render;

import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(targets = "net.minecraft.client.renderer.RenderGlobal$ContainerLocalRenderInformation")
public class MixinContainerLocalRenderInformation {

    @ShadowConstructor
    private void ContainerLocalRenderInformation(RenderChunk renderChunkIn, EnumFacing facingIn, int counterIn) {}

    @NewConstructor
    public void ContainerLocalRenderInformation(RenderGlobal renderer, RenderChunk renderChunkIn, EnumFacing facingIn, int counterIn, Object o) {
        this.ContainerLocalRenderInformation(renderChunkIn, facingIn, counterIn);
    }
}
