package net.fabricmc.loader.impl.fml.mixins;

import net.fabricmc.loader.impl.game.minecraft.Hooks;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.server.FMLServerHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.net.Proxy;

@SideOnly(Side.SERVER)
@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {

    @Inject(method = "<init>(Ljava/io/File;Ljava/net/Proxy;Ljava/io/File;)V", at = @At("RETURN"))
    public void init(File workDir, Proxy proxy, File profileCacheDir, CallbackInfo ci) {
        Hooks.startServer(workDir, FMLServerHandler.instance().getServer());
    }
}
