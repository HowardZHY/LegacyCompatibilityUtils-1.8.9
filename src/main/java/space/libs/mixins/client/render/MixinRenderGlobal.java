package space.libs.mixins.client.render;

import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.Public;

@SuppressWarnings("unused")
@Mixin(RenderGlobal.class)
public abstract class MixinRenderGlobal {

    @Shadow
    public static void drawSelectionBoundingBox(AxisAlignedBB boundingBox) {}

    @Shadow
    protected abstract org.lwjgl.util.vector.Vector3f getViewVector(Entity entityIn, double partialTicks);

    @Shadow
    private RenderChunk getRenderChunkOffset(BlockPos playerPos, RenderChunk renderChunkBase, EnumFacing facing) {
        throw new AbstractMethodError();
    }

    /** drawOutlinedBoundingBox */
    @Public
    private static void func_147590_a(AxisAlignedBB boundingBox, int i) {
        drawSelectionBoundingBox(boundingBox);
    }

    /** getViewVector */
    public javax.vecmath.Vector3f func_174962_a(Entity entityIn, double partialTicks) {
        org.lwjgl.util.vector.Vector3f lwjglVec3f = this.getViewVector(entityIn, partialTicks);
        return new javax.vecmath.Vector3f(
            lwjglVec3f.getX(),
            lwjglVec3f.getY(),
            lwjglVec3f.getZ()
        );
    }

    /** getRenderChunkOffset */
    public RenderChunk func_174973_a(BlockPos playerPos, RenderChunk renderChunkBase, EnumFacing facing) {
        return this.getRenderChunkOffset(playerPos, renderChunkBase, facing);
    }

}
