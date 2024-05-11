package net.minecraft.util;

import net.minecraft.client.audio.SoundPoolEntry;

@SuppressWarnings("unused")
public class SoundEvent extends SoundPoolEntry {

    public SoundEvent(ResourceLocation locationIn, double pitchIn, double volumeIn, boolean streamingSoundIn) {
        super(locationIn, pitchIn, volumeIn, streamingSoundIn);
    }
    public SoundEvent(SoundPoolEntry locationIn) {
        super(locationIn);
    }

    public SoundEvent(ResourceLocation p_i46834_1_) {
        super(p_i46834_1_, 1, 1 , false);
    }

    /** getSoundName */
    public ResourceLocation func_187503_a() {
        return super.getSoundPoolEntryLocation();
    }
}
