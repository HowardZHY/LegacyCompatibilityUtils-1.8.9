package space.libs.mixins.client.render;

import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.libs.CompatLib;

import java.util.List;

@Mixin(ModelBakery.class)
public abstract class MixinModelBakery {

    @Shadow
    protected List<String> getVariantNames(Item item) {
        throw new AbstractMethodError();
    }

    @Shadow
    protected ResourceLocation getItemLocation(String name) {
        throw new AbstractMethodError();
    }

    @Inject(method = "addVariantName", at = @At("HEAD"), remap = false, cancellable = true)
    private static void addVariantName(Item item, String[] names, CallbackInfo ci) {
        if (ArrayUtils.isEmpty(names)) {
            CompatLib.LOGGER.warn("Empty addVariantName with Legacy Item: " + item.delegate.getResourceName());
            ModelBakery.registerItemVariants(item);
            ci.cancel();
        }
    }

}
