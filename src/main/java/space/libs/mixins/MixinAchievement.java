package space.libs.mixins;

import net.minecraft.stats.Achievement;
import net.minecraft.util.IJsonSerializable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(Achievement.class)
public abstract class MixinAchievement {

    @Shadow
    public abstract Achievement initIndependentStat();

    @Shadow
    public abstract Achievement registerStat();

    @Shadow
    public abstract Achievement func_150953_b(Class <? extends IJsonSerializable> p_150953_1_);

    /** setIndependent */
    public Achievement func_180789_a() {
        return this.initIndependentStat();
    }

    /** registerAchievement */
    public Achievement func_180788_c() {
        return this.registerStat();
    }

    public Achievement func_180787_a(Class <? extends IJsonSerializable> c) {
        return this.func_150953_b(c);
    }
}
