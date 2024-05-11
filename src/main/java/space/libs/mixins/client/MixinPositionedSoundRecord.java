package space.libs.mixins.client;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.Public;

@SuppressWarnings("all")
@Mixin(PositionedSoundRecord.class)
public abstract class MixinPositionedSoundRecord {

    @Shadow
    public static PositionedSoundRecord create(ResourceLocation soundResource, float pitch) {
        throw new AbstractMethodError();
    }

    /** getMasterRecord */
    @Public
    private static PositionedSoundRecord func_184371_a(SoundEvent sound, float pitch) {
        return create(sound.func_187503_a(), pitch);
    }

}
