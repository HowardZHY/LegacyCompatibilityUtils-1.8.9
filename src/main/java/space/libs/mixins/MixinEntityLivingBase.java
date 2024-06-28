package space.libs.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase extends Entity {

    public MixinEntityLivingBase(World worldIn) {
        super(worldIn);
    }

    @Inject(method = {"collideWithNearbyEntities"}, at = {@At("HEAD")}, cancellable = true)
    private void collideWithNearbyEntities(CallbackInfo ci) {
        if (this.worldObj.isRemote) {
            ci.cancel();
        }
    }
}
