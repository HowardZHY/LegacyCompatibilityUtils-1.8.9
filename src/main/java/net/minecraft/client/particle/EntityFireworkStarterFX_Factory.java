package net.minecraft.client.particle;

import net.minecraft.world.World;

public class EntityFireworkStarterFX_Factory extends EntityFirework.Factory {

    @Override
    public EntityFX getEntityFX(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... args) {
        return super.getEntityFX(particleID, worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, args);
    }
}
