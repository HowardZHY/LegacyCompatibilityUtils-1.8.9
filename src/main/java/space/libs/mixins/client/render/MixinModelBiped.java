package space.libs.mixins.client.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.EnumArmPose;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("unused")
@Mixin(ModelBiped.class)
public abstract class MixinModelBiped {

    /** leftArmPose */
    public EnumArmPose field_187075_l = EnumArmPose.EMPTY;

     /** rightArmPose */
    public EnumArmPose field_187076_m = EnumArmPose.EMPTY;

}
