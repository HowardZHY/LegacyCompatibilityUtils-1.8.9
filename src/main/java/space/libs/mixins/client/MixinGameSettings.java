package space.libs.mixins.client;

import net.minecraft.client.settings.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.MappedName;

@SuppressWarnings("unused")
@Mixin(GameSettings.class)
public abstract class MixinGameSettings {
    @Shadow
    public abstract int shouldRenderClouds();

    @MappedName("shouldRenderClouds")
    public boolean func_74309_c() {
        return this.shouldRenderClouds() != 0;
    }

}
