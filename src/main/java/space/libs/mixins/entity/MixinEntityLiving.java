package space.libs.mixins.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.EnumHandSide;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("unused")
@Mixin(EntityLiving.class)
public abstract class MixinEntityLiving extends MixinEntityLivingBase {

    public MixinEntityLiving(World worldIn) {
        super(worldIn);
    }

    @Override
    public EnumHandSide func_184591_cq() {
        return EnumHandSide.RIGHT;
    }
}
