package space.libs.mixins.client;

import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.Public;

@SuppressWarnings("unused")
@Mixin(World.class)
public abstract class MixinClientWorld {

    @Shadow
    public abstract Vec3 getSkyColor(Entity entityIn, float partialTicks);

    @Shadow(prefix = "original$", remap = false)
    public abstract Vec3 original$getSkyColorBody(Entity entityIn, float partialTicks);

    @Shadow
    public abstract Vec3 getCloudColour(float partialTicks);

    @Shadow(prefix = "original$", remap = false)
    public abstract Vec3 original$drawCloudsBody(float partialTicks);

    @Shadow
    public abstract Vec3 getFogColor(float partialTicks);

    public Vec3d func_72833_a(Entity entityIn, float partialTicks) {
        return TransformVec3d(getSkyColor(entityIn, partialTicks));
    }

    public Vec3d getSkyColorBody(Entity entity, float partialTicks) {
        return TransformVec3d(this.original$getSkyColorBody(entity, partialTicks));
    }

    public Vec3d drawCloudsBody(float partialTicks) {
        return TransformVec3d(this.original$drawCloudsBody(partialTicks));
    }

    @Public
    private static Vec3d TransformVec3d(Vec3 vec) {
        return new Vec3d(vec.xCoord, vec.yCoord, vec.zCoord);
    }

}
