package space.libs.mixins;

import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.FMLControlledNamespacedRegistry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import space.libs.CompatLib;
import space.libs.util.cursedmixinextensions.annotations.Public;

import java.util.Iterator;
import java.util.Map;

@SuppressWarnings({"unused", "deprecation"})
@Mixin(Potion.class)
public class MixinPotion {

    @Shadow
    private static @Final Map<ResourceLocation, Potion> field_180150_I;

    @Shadow
    public static @Final Potion[] potionTypes;

    @Shadow
    public int id;

    @Public
    private static String[] func_180141_c() {
        String[] str = new String[field_180150_I.size()];
        int i = 0;
        ResourceLocation loc;
        for (Iterator<ResourceLocation> var2 = field_180150_I.keySet().iterator(); var2.hasNext(); str[i++] = loc.toString()) {
            loc = var2.next();
        }
        return str;
    }

    @Redirect(
        method = "<init>(ILnet/minecraft/util/ResourceLocation;ZI)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraftforge/fml/common/registry/FMLControlledNamespacedRegistry;register(ILnet/minecraft/util/ResourceLocation;Ljava/lang/Object;)V"
        ),
        remap = false
    )
    protected void init(FMLControlledNamespacedRegistry<Potion> instance, int id, ResourceLocation location, Object thing) {
        Potion This = (Potion) (Object) this;
        if (id >= 32) {
            if (location.getResourceDomain().equals("minecraft")) {
                CompatLib.LOGGER.warn("Old modded potion ID " + id);
                this.id = id;
                potionTypes[id] = This;
                return;
            }
        }
        instance.register(id, location, This);
    }
}
