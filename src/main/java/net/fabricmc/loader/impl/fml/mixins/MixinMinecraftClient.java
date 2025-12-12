package net.fabricmc.loader.impl.fml.mixins;

import net.fabricmc.loader.impl.game.minecraft.Hooks;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

@SideOnly(Side.CLIENT)
@Mixin(Minecraft.class)
public class MixinMinecraftClient {

    @Shadow(aliases = "field_71412_D")
    public @Final File mcDataDir;

    @Inject(method = "startGame", at = @At("HEAD"))
    private void startGame(CallbackInfo ci) {
        Hooks.startClient(mcDataDir, FMLClientHandler.instance().getClient());
    }
}
