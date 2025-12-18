package net.legacyfabric.loader.impl.fml.mixins;

import net.fabricmc.loader.impl.game.minecraft.Hooks;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.net.Proxy;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {

    @Shadow(aliases = "field_71309_l")
    private static MinecraftServer mcServer;

    @Inject(method = "<init>(Ljava/io/File;Ljava/net/Proxy;Ljava/io/File;)V", at = @At("RETURN"))
    public void init(File workDir, Proxy proxy, File profileCacheDir, CallbackInfo ci) {
        Hooks.startServer(workDir, mcServer);
    }
}
