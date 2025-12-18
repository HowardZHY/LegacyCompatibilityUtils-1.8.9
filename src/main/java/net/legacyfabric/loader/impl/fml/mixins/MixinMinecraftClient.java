package net.legacyfabric.loader.impl.fml.mixins;

import net.fabricmc.loader.impl.game.minecraft.Hooks;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

@Mixin(Minecraft.class)
public class MixinMinecraftClient {

    @Shadow(aliases = "field_71412_D")
    public @Final File mcDataDir;

    @Shadow(aliases = "func_71410_x")
    public static Minecraft getMinecraft() {
        throw new AbstractMethodError();
    }

    @Inject(method = "startGame", at = @At("HEAD"))
    private void startGame(CallbackInfo ci) {
        Hooks.startClient(mcDataDir, getMinecraft());
    }
}
