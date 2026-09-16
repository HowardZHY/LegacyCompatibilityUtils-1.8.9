package space.libs.mixins.client.render;

import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.entity.Entity;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.MappedName;
import space.libs.util.client.IMathUtils;
import space.libs.util.cursedmixinextensions.annotations.Public;

@SuppressWarnings("unused")
@Mixin(value = RenderGlobal.class, priority = 100)
public abstract class MixinRenderGlobal implements IMathUtils {

    @Shadow
    private int renderDistanceChunks;

    @Shadow
    private ViewFrustum viewFrustum;

    @Shadow
    private RenderChunk getRenderChunkOffset(BlockPos playerPos, RenderChunk renderChunkBase, EnumFacing facing) {
        throw new AbstractMethodError();
    }

    @Shadow
    protected abstract org.lwjgl.util.vector.Vector3f getViewVector(Entity entityIn, double partialTicks);

    @Shadow
    public static void drawSelectionBoundingBox(AxisAlignedBB boundingBox) {}

    @MappedName("drawOutlinedBoundingBox")
    @Public
    private static void func_147590_a(AxisAlignedBB boundingBox, int i) {
        drawSelectionBoundingBox(boundingBox);
    }

    @MappedName("getViewVector")
    public javax.vecmath.Vector3f func_174962_a(Entity entityIn, double partialTicks) {
        return TransformVec3f(this.getViewVector(entityIn, partialTicks));
    }

    @MappedName("getRenderChunkOffset")
    public RenderChunk func_174973_a(BlockPos playerPos, RenderChunk renderChunkBase, EnumFacing facing) {
        return this.getRenderChunkOffset(playerPos, renderChunkBase, facing);
    }
}
