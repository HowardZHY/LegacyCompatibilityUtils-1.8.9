package space.libs.mixins.mods.mobends;

import net.gobbob.mobends.client.model.ModelRendererBends;
import net.gobbob.mobends.data.Data_Zombie;
import net.gobbob.mobends.util.SmoothVector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import space.libs.util.mods.IDataZombie;
import space.libs.util.mods.ISmoothVector3f;

@SuppressWarnings("all")
@Pseudo
@Mixin(value = Data_Zombie.class, remap = false)
public class MixinDataZombie implements IDataZombie {

    public boolean initialized = false;

    public boolean isInitialized() {
        return this.initialized;
    }

    public void setInitialized(boolean flag) {
        this.initialized = flag;
    }

    public void initModelPose() {
        Data_Zombie This = (Data_Zombie) (Object) this;
        if (This.body == null) return;
        this.setInitialized(true);
        this.finish(This.head);
        this.finish(This.headwear);
        this.finish(This.body);
        this.finish(This.leftArm);
        this.finish(This.rightArm);
        this.finish(This.leftLeg);
        this.finish(This.rightLeg);
        this.finish(This.leftForeArm);
        this.finish(This.rightForeArm);
        this.finish(This.leftForeLeg);
        this.finish(This.rightForeLeg);
    }

    public void finish(ModelRendererBends bends) {
        accessor(bends.rotation).finish();
        accessor(bends.pre_rotation).finish();
    }

    public ISmoothVector3f accessor(SmoothVector3f s) {
        return (ISmoothVector3f) s;
    }

}
