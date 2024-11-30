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
        Data_Zombie data = (Data_Zombie) Data_Zombie.get(argEntity.getEntityId());
        AnimatedEntity aEntity = AnimatedEntity.getByEntity(argEntity);
        EntityZombie zombie = (EntityZombie) argEntity;
        This.armSwing = argSwingTime;
        This.armSwingAmount = argSwingAmount;
        This.headRotationX = argHeadX;
        This.headRotationY = argHeadY;
        ((ModelRendererBends) This.bipedHead).sync(data.head);
        ((ModelRendererBends) This.bipedHeadwear).sync(data.headwear);
        ((ModelRendererBends) This.bipedBody).sync(data.body);
        ((ModelRendererBends) This.bipedRightArm).sync(data.rightArm);
        ((ModelRendererBends) This.bipedLeftArm).sync(data.leftArm);
        ((ModelRendererBends) This.bipedRightLeg).sync(data.rightLeg);
        ((ModelRendererBends) This.bipedLeftLeg).sync(data.leftLeg);
        ((ModelRendererBends) this.bipedRightForeArm).sync(data.rightForeArm);
        ((ModelRendererBends) this.bipedLeftForeArm).sync(data.leftForeArm);
        ((ModelRendererBends) this.bipedRightForeLeg).sync(data.rightForeLeg);
        ((ModelRendererBends) this.bipedLeftForeLeg).sync(data.leftForeLeg);
        This.renderOffset.set(data.renderOffset);
        This.renderRotation.set(data.renderRotation);
        if (data.canBeUpdated()) {
            This.renderOffset.setSmooth(new Vector3f(0,-1f,0),0.5f);
            This.renderRotation.setSmooth(new Vector3f(0,0,0),0.5f);
            ((ModelRendererBends) This.bipedHead).resetScale();
            ((ModelRendererBends) This.bipedHeadwear).resetScale();
            ((ModelRendererBends) This.bipedBody).resetScale();
            ((ModelRendererBends) This.bipedRightArm).resetScale();
            ((ModelRendererBends) This.bipedLeftArm).resetScale();
            ((ModelRendererBends) This.bipedRightLeg).resetScale();
            ((ModelRendererBends) This.bipedLeftLeg).resetScale();
            ((ModelRendererBends) this.bipedRightForeArm).resetScale();
            ((ModelRendererBends) this.bipedLeftForeArm).resetScale();
            ((ModelRendererBends) this.bipedRightForeLeg).resetScale();
            ((ModelRendererBends) this.bipedLeftForeLeg).resetScale();
            BendsVar.tempData = data;
            this.animate(aEntity, zombie, data);
            ((ModelRendererBends) This.bipedHead).update(data.ticksPerFrame);
            ((ModelRendererBends) This.bipedHeadwear).update(data.ticksPerFrame);
            ((ModelRendererBends) This.bipedBody).update(data.ticksPerFrame);
            ((ModelRendererBends) This.bipedLeftArm).update(data.ticksPerFrame);
            ((ModelRendererBends) This.bipedRightArm).update(data.ticksPerFrame);
            ((ModelRendererBends) This.bipedLeftLeg).update(data.ticksPerFrame);
            ((ModelRendererBends) This.bipedRightLeg).update(data.ticksPerFrame);
            ((ModelRendererBends) this.bipedLeftForeArm).update(data.ticksPerFrame);
            ((ModelRendererBends) this.bipedRightForeArm).update(data.ticksPerFrame);
            ((ModelRendererBends) this.bipedLeftForeLeg).update(data.ticksPerFrame);
            ((ModelRendererBends) this.bipedRightForeLeg).update(data.ticksPerFrame);
            This.renderOffset.update(data.ticksPerFrame);
            This.renderRotation.update(data.ticksPerFrame);
            //System.out.println(data.ticksPerFrame);
            data.updatedThisFrame = true;
            if(!this.isRenderedInGui()) {
                data.syncModelInfo(This);
            }
        }
        IDataZombie idz = (IDataZombie) data;
        if (!idz.isInitialized()) {
            this.animate(aEntity, zombie, data);
            data.syncModelInfo(This);
            idz.initModelPose();
        }
    }

    public boolean isRenderedInGui() {
        return GuiScreenEventHandler.renderingGuiScreen;
    }

    public void animate(AnimatedEntity aEntity, EntityZombie entity, Data_Zombie data) {
        ModelBendsZombie This = (ModelBendsZombie) (Object) this;
        if (data.motion.x == 0.0f & data.motion.z == 0.0f) {
            aEntity.get("stand").animate(entity, this, data);
            BendsPack.animate(This, "zombie", "stand");
        } else {
            aEntity.get( "walk").animate(entity, this, data);
            BendsPack.animate(This, "zombie", "walk");
        }
    }
}
