package space.libs.mixins.mods.skybox;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.skybox.GoGSkybox;
import vazkii.skybox.ModEventHandler;

@SuppressWarnings("all")
@Pseudo
@Mixin(value = GoGSkybox.class, remap = false)
public class MixinGoGSkybox {

    @Inject(method = "init", at = @At("HEAD"))
    public void init(FMLInitializationEvent event, CallbackInfo ci) {
        FMLCommonHandler.instance().bus().register(ModEventHandler.class);
    }
}
