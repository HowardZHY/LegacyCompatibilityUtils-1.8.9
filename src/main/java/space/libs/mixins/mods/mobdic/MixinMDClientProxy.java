package space.libs.mixins.mods.mobdic;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings("all")
@Pseudo
@Mixin(targets = "ayamitsu.mobdictionary.client.ClientProxy", remap = false)
public class MixinMDClientProxy {

    @Dynamic
    @Redirect(method = "getMouseOver", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/common/ObfuscationReflectionHelper;getPrivateValue(Ljava/lang/Class;Ljava/lang/Object;I)Ljava/lang/Object;"))
    private <T, E> T redirectGetPrivateValue(Class<? super E> classToAccess, E instance, int fieldIndex) {
        return ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), "field_71428_T");
    }
}
