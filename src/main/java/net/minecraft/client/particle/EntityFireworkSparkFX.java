package net.minecraft.client.particle;

import net.minecraft.world.World;

public class EntityFireworkSparkFX extends EntityFirework.SparkFX {

    public EntityFireworkSparkFX(World world, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, EffectRenderer renderer) {
        super(world, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, renderer);
    }
}
