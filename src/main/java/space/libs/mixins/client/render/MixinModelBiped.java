package space.libs.mixins.client.render;

import net.minecraft.client.model.*;
import net.minecraft.util.EnumHandSide;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.interfaces.IModelBiped;
import space.libs.util.MappedName;

@SuppressWarnings("unused")
@Mixin(ModelBiped.class)
public abstract class MixinModelBiped implements IModelBiped {

    @Shadow
    public ModelRenderer bipedRightArm;

    @Shadow
    public ModelRenderer bipedLeftArm;

    @MappedName("leftArmPose")
    public EnumArmPose field_187075_l = EnumArmPose.EMPTY;

    @MappedName("rightArmPose")
    public EnumArmPose field_187076_m = EnumArmPose.EMPTY;

    @Override
    public void func_187073_a(float scale, EnumHandSide side) {
        if (side == EnumHandSide.LEFT) {
            this.bipedLeftArm.postRender(scale);
        } else {
            this.bipedRightArm.postRender(scale);
        }
    }

    @MappedName("getArmForSide")
    public ModelRenderer func_187074_a(EnumHandSide side) {
        if (side == EnumHandSide.LEFT)
            return this.bipedLeftArm;
        return this.bipedRightArm;
    }

    @Override
    public EnumArmPose getRightArmPose() {
        return this.field_187076_m;
    }

    @Override
    public void setRightArmPose(EnumArmPose pose) {
        this.field_187076_m = pose;
    }
}
