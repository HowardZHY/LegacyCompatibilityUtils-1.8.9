package space.libs.mixins.client.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.EnumArmPose;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.EnumHandSide;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(ModelBiped.class)
public abstract class MixinModelBiped {

    @Shadow
    public ModelRenderer bipedRightArm;

    @Shadow
    public ModelRenderer bipedLeftArm;

    @Shadow
    public void postRenderArm(float scale) {}

    /** leftArmPose */
    public EnumArmPose field_187075_l = EnumArmPose.EMPTY;

     /** rightArmPose */
    public EnumArmPose field_187076_m = EnumArmPose.EMPTY;

    public void func_187073_a(float scale, EnumHandSide side) {
        this.postRenderArm(scale);
    }

    /** getArmForSide */
    public ModelRenderer func_187074_a(EnumHandSide side) {
        if (side == EnumHandSide.LEFT)
            return this.bipedLeftArm;
        return this.bipedRightArm;
    }
}
