package space.libs.mixins.client.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.EnumArmPose;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.EnumHandSide;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.interfaces.IModelBiped;

@SuppressWarnings("unused")
@Mixin(ModelBiped.class)
public abstract class MixinModelBiped implements IModelBiped {

    @Shadow
    public ModelRenderer bipedRightArm;

    @Shadow
    public ModelRenderer bipedLeftArm;

    /** leftArmPose */
    public EnumArmPose field_187075_l = EnumArmPose.EMPTY;

     /** rightArmPose */
    public EnumArmPose field_187076_m = EnumArmPose.EMPTY;

    public void func_187073_a(float scale, EnumHandSide side) {
        if (side == EnumHandSide.LEFT) {
            this.bipedLeftArm.postRender(scale);
        } else {
            this.bipedRightArm.postRender(scale);
        }
    }

    /** getArmForSide */
    public ModelRenderer func_187074_a(EnumHandSide side) {
        if (side == EnumHandSide.LEFT)
            return this.bipedLeftArm;
        return this.bipedRightArm;
    }

    public EnumArmPose getRightArmPose() {
        return this.field_187076_m;
    }

    public void setRightArmPose(EnumArmPose pose) {
        this.field_187076_m = pose;
    }
}
