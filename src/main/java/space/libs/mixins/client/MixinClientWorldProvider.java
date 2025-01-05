package space.libs.mixins.client;

import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.Public;

@SuppressWarnings("unused")
@Mixin(WorldProvider.class)
public abstract class MixinClientWorldProvider {

    @Shadow
    public abstract Vec3 getFogColor(float p_76562_1_, float p_76562_2_);

    @Shadow(prefix = "original$", remap = false)
    public abstract Vec3 original$getSkyColor(Entity cameraEntity, float partialTicks);

    @Shadow(prefix = "original$", remap = false)
    public abstract Vec3 original$drawClouds(float partialTicks);

    public Vec3d getSkyColor(Entity cameraEntity, float partialTicks) {
        return TransformVec3d(this.original$getSkyColor(cameraEntity, partialTicks));
    }

    public Vec3d drawClouds(float partialTicks) {
        return TransformVec3d(this.original$drawClouds(partialTicks));
    }

    @Public
    private static Vec3d TransformVec3d(Vec3 vec) {
        return new Vec3d(vec.xCoord, vec.yCoord, vec.zCoord);
    }
}
