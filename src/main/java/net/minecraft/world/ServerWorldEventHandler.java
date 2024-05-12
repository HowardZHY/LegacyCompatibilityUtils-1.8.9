package net.minecraft.world;

import net.minecraft.server.MinecraftServer;

public class ServerWorldEventHandler extends WorldManager {
    public ServerWorldEventHandler(MinecraftServer mcServerIn, WorldServer worldServerIn) {
        super(mcServerIn, worldServerIn);
    }
}
