package space.libs.mixins.mods.mobends;

import net.gobbob.mobends.AnimatedEntity;
import net.gobbob.mobends.client.model.ModelRendererBends;
import net.gobbob.mobends.client.model.entity.ModelBendsZombie;
import net.gobbob.mobends.data.Data_Zombie;
import net.gobbob.mobends.pack.BendsPack;
import net.gobbob.mobends.pack.BendsVar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import org.apache.logging.log4j.LogManager;
import org.lwjgl.util.vector.Vector3f;
import org.spongepowered.asm.mixin.*;
import space.libs.event.GuiScreenEventHandler;
import space.libs.util.mods.IDataZombie;

@SuppressWarnings("all")
@Pseudo
@Mixin(value = ModelBendsZombie.class, remap = false)
public abstract class MixinModelBendsZombie extends ModelBiped {

    @Dynamic
    @Shadow(remap = false)
    public ModelRenderer bipedRightForeArm;

    @Dynamic
    @Shadow(remap = false)
    public ModelRenderer bipedLeftForeArm;

    @Dynamic
    @Shadow(remap = false)
    public ModelRenderer bipedRightForeLeg;

    @Dynamic
    @Shadow(remap = false)
    public ModelRenderer bipedLeftForeLeg;

    /**
     * @author HowardZHY
     * @reason Try Fix
     */
    @Dynamic
    @Overwrite(remap = false)
    public void func_78087_a(float argSwingTime, float argSwingAmount, float argArmSway, float argHeadY, float argHeadX, float argNr6, Entity argEntity) {
        if (Minecraft.getMinecraft().theWorld == null) return;
        ModelBendsZombie This = (ModelBendsZombie) (Object) this;
        Data_Zombie data = Data_Zombie.get(argEntity.getEntityId());
        This.armSwing = argSwingTime;
        This.armSwingAmount = argSwingAmount;
        This.headRotationX = argHeadX;
        This.headRotationY = argHeadY;
        ((ModelRendererBends) this.bipedHead).sync(data.head);
        ((ModelRendererBends) this.bipedHeadwear).sync(data.headwear);
        ((ModelRendererBends) this.bipedBody).sync(data.body);
        ((ModelRendererBends) this.bipedRightArm).sync(data.rightArm);
        ((ModelRendererBends) this.bipedLeftArm).sync(data.leftArm);
        ((ModelRendererBends) this.bipedRightLeg).sync(data.rightLeg);
        ((ModelRendererBends) this.bipedLeftLeg).sync(data.leftLeg);
        ((ModelRendererBends) this.bipedRightForeArm).sync(data.rightForeArm);
        ((ModelRendererBends) this.bipedLeftForeArm).sync(data.leftForeArm);
        ((ModelRendererBends) this.bipedRightForeLeg).sync(data.rightForeLeg);
        ((ModelRendererBends) this.bipedLeftForeLeg).sync(data.leftForeLeg);
        This.renderOffset.set(data.renderOffset);
        This.renderRotation.set(data.renderRotation);
        AnimatedEntity aEntity = AnimatedEntity.getByEntity(argEntity);
        EntityZombie zombie = (EntityZombie) argEntity;
        if (data.canBeUpdated()) {
            This.renderOffset.setSmooth(new Vector3f(0,-1f,0),0.5f);
            This.renderRotation.setSmooth(new Vector3f(0,0,0),0.5f);
            ((ModelRendererBends) this.bipedHead).resetScale();
            ((ModelRendererBends) this.bipedHeadwear).resetScale();
            ((ModelRendererBends) this.bipedBody).resetScale();
            ((ModelRendererBends) this.bipedRightArm).resetScale();
            ((ModelRendererBends) this.bipedLeftArm).resetScale();
            ((ModelRendererBends) this.bipedRightLeg).resetScale();
            ((ModelRendererBends) this.bipedLeftLeg).resetScale();
            ((ModelRendererBends) this.bipedRightForeArm).resetScale();
            ((ModelRendererBends) this.bipedLeftForeArm).resetScale();
            ((ModelRendererBends) this.bipedRightForeLeg).resetScale();
            ((ModelRendererBends) this.bipedLeftForeLeg).resetScale();
            BendsVar.tempData = data;
            if (data.motion.x == 0.0f & data.motion.z == 0.0f) {
                aEntity.get("stand").animate(zombie, this, data);
                BendsPack.animate(This,"zombie","stand");
            } else {
                LogManager.getLogger().info("Walking: " + data.currentWalkingState);
                aEntity.get("walk").animate(zombie, this, data);
                BendsPack.animate(This,"zombie","walk");
            }
            ((ModelRendererBends) this.bipedHead).update(data.ticksPerFrame);
            ((ModelRendererBends) this.bipedHeadwear).update(data.ticksPerFrame);
            ((ModelRendererBends) this.bipedBody).update(data.ticksPerFrame);
            ((ModelRendererBends) this.bipedLeftArm).update(data.ticksPerFrame);
            ((ModelRendererBends) this.bipedRightArm).update(data.ticksPerFrame);
            ((ModelRendererBends) this.bipedLeftLeg).update(data.ticksPerFrame);
            ((ModelRendererBends) this.bipedRightLeg).update(data.ticksPerFrame);
            ((ModelRendererBends) this.bipedLeftForeArm).update(data.ticksPerFrame);
            ((ModelRendererBends) this.bipedRightForeArm).update(data.ticksPerFrame);
            ((ModelRendererBends) this.bipedLeftForeLeg).update(data.ticksPerFrame);
            ((ModelRendererBends) this.bipedRightForeLeg).update(data.ticksPerFrame);
            This.renderOffset.update(data.ticksPerFrame);
            This.renderRotation.update(data.ticksPerFrame);
            data.updatedThisFrame = true;
        }
        IDataZombie idz = (IDataZombie) data;
        if (!idz.isInitialized()) {
            if (data.motion.x == 0.0f & data.motion.z == 0.0f) {
                aEntity.get("stand").animate(zombie, this, data);
                BendsPack.animate(This, "zombie", "stand");
            } else {
                LogManager.getLogger().info("Walking: " + data.currentWalkingState);
                aEntity.get("walk").animate(zombie, this, data);
                BendsPack.animate(This, "zombie", "walk");
            }
            data.syncModelInfo(This);
            idz.initModelPose();
        } else {
            data.syncModelInfo(This);
        }
    }

    public boolean isRenderedInGui() {
        return GuiScreenEventHandler.renderingGuiScreen;
    }
}
