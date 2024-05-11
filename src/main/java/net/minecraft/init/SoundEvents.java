package net.minecraft.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

@SuppressWarnings("all")
public class SoundEvents {
    static {
        if (!Bootstrap.isRegistered())
            throw new RuntimeException("Accessed Sounds before Bootstrap!");
    }

    /** UI_BUTTON_CLICK */
    public static final SoundEvent field_187909_gi = getRegisteredSoundEvent("ui.button.click");

    private static SoundEvent getRegisteredSoundEvent(String soundname) {
        SoundEvent sound = new SoundEvent(new ResourceLocation(soundname));
        if (sound == null) throw new IllegalStateException("Invalid Sound requested: " + soundname);
        return sound;
    }
}
