package net.minecraft.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import space.libs.util.cursedmixinextensions.annotations.Public;

@SuppressWarnings("all")
public class SoundEvents {

    static {
        if (!Bootstrap.isRegistered()) {
            throw new RuntimeException("Accessed Sounds before Bootstrap!");
        }
    }

    /** UI_BUTTON_CLICK */
    public static final SoundEvent field_187909_gi = func_187510_a("ui.button.click");

    /** getRegisteredSoundEvent */
    public static SoundEvent func_187510_a(String soundname) {
        SoundEvent sound = new SoundEvent(new ResourceLocation(soundname));
        if (sound == null) {
            throw new IllegalStateException("Invalid Sound requested: " + soundname);
        }
        return sound;
    }
}
