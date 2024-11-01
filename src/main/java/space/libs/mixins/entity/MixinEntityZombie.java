package space.libs.mixins.entity;

import net.minecraft.entity.monster.EntityZombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(EntityZombie.class)
public abstract class MixinEntityZombie {

    @Shadow
    public void setVillager(boolean villager) {}

    /** isArmsRaised */
    public boolean func_184734_db() {
        return true;
    }

    /** getVillagerType */
    public int func_184736_de() {
        return 1;
    }

    /** setToNotVillager */
    public void func_184732_df() {
        this.setVillager(false);
    }
}
