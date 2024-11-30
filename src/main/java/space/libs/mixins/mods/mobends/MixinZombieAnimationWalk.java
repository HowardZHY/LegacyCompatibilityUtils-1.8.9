package space.libs.mixins.mods.mobends;

import net.gobbob.mobends.animation.Animation;
import net.gobbob.mobends.animation.zombie.Animation_Walk;
import net.gobbob.mobends.client.model.ModelRendererBends;
import net.gobbob.mobends.client.model.entity.ModelBendsZombie;
import net.gobbob.mobends.data.Data_Zombie;
import net.gobbob.mobends.data.EntityData;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@SuppressWarnings("unused")
@Mixin(value = Animation_Walk.class, remap = false)
public abstract class MixinZombieAnimationWalk extends Animation {

    @Dynamic
    @Override
    @Overwrite
    public void animate(EntityLivingBase argEntity, ModelBase argModel, EntityData argData) {
        Data_Zombie data = (Data_Zombie) argData;
        ModelBendsZombie model = (ModelBendsZombie) argModel;
        float headRotationX = model.headRotationX;
        float headRotationY = model.headRotationY;
        float armSwing = model.armSwing;
        float armSwingAmount = model.armSwingAmount;
        model.renderOffset.setSmoothY(-3.0f);
        float var2 = 30.0f+(MathHelper.cos((armSwing * 0.6662F)*2)*10.0f);
        ((ModelRendererBends)model.bipedBody).rotation.setSmoothX(var2,0.3f);
        ((ModelRendererBends)model.bipedRightArm).rotation.setSmoothX(0.9f*(float) ((MathHelper.cos(armSwing * 0.6662F + (float)Math.PI) * 2.0F * armSwingAmount * 0.5F ) / Math.PI * 180.0f));
        ((ModelRendererBends)model.bipedLeftArm).rotation.setSmoothX(0.9f*(float) ((MathHelper.cos(armSwing * 0.6662F) * 2.0F * armSwingAmount * 0.5F) / Math.PI * 180.0f));
        ((ModelRendererBends)model.bipedRightArm).rotation.setSmoothZ(5,0.3f);
        ((ModelRendererBends)model.bipedLeftArm).rotation.setSmoothZ(-5,0.3f);
        ((ModelRendererBends)model.bipedRightLeg).rotation.setSmoothX(-5.0f+0.9f*(float) ((MathHelper.cos(armSwing * 0.6662F) * 1.4F * armSwingAmount) / Math.PI * 180.0f),1.0f);
        ((ModelRendererBends)model.bipedLeftLeg).rotation.setSmoothX(-5.0f+0.9f*(float) ((MathHelper.cos(armSwing * 0.6662F + (float)Math.PI) * 1.4F * armSwingAmount) / Math.PI * 180.0f),1.0f);
        ((ModelRendererBends)model.bipedRightLeg).rotation.setSmoothY(0.0f);
        ((ModelRendererBends)model.bipedLeftLeg).rotation.setSmoothY(0.0f);
        ((ModelRendererBends)model.bipedRightLeg).rotation.setSmoothZ(10,0.2f);
        ((ModelRendererBends)model.bipedLeftLeg).rotation.setSmoothZ(-10,0.2f);
        float var = (float) ((armSwing * 0.6662F) /Math.PI)%2;
        ((ModelRendererBends)model.bipedLeftForeLeg).rotation.setSmoothX( (var > 1 ? 45 : 0), 0.3f);
        ((ModelRendererBends)model.bipedRightForeLeg).rotation.setSmoothX( (var > 1 ? 0 : 45), 0.3f);
        ((ModelRendererBends)model.bipedLeftForeArm).rotation.setSmoothX( ((float) (Math.cos(armSwing * 0.6662F + Math.PI/2)+1.0f)/2.0f)*-20, 1.0f);
        ((ModelRendererBends)model.bipedRightForeArm).rotation.setSmoothX( ((float) (Math.cos(armSwing * 0.6662F)+1.0f)/2.0f)*-20, 0.3f);
        float var1 = ((MathHelper.cos(armSwing * 0.6662F + (float)Math.PI)/(float)Math.PI*180.0f))*0.5f;
        ((ModelRendererBends)model.bipedBody).rotation.setSmoothY(((MathHelper.cos(armSwing * 0.6662F + (float)Math.PI)/(float)Math.PI*180.0f))*0.5f,0.3f);
        if (data.currentWalkingState == 1) {
            ((ModelRendererBends)model.bipedRightArm).rotation.setSmoothX(-90-30.0f);
            ((ModelRendererBends)model.bipedLeftArm).rotation.setSmoothX(-90-30.0f);
        }
        ((ModelRendererBends)model.bipedHead).rotation.setX(headRotationX-30);
        ((ModelRendererBends)model.bipedHead).rotation.setY(headRotationY-var1);
    }
}
