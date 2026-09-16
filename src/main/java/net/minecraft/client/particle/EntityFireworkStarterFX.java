package net.minecraft.client.particle;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityFireworkStarterFX extends EntityFirework.StarterFX {

    public EntityFireworkStarterFX(World world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, EffectRenderer renderer, NBTTagCompound nbt) {
        super(world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, renderer, nbt);
    }
}
