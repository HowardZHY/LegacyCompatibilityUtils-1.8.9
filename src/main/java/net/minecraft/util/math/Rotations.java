package net.minecraft.util.math;

import net.minecraft.nbt.NBTTagList;

public class Rotations extends net.minecraft.util.Rotations {
    public Rotations(float x, float y, float z) {
        super(x, y, z);
    }

    public Rotations(NBTTagList nbt) {
        super(nbt);
    }
}
