package space.libs.mixins.forge.event;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLModIdMappingEvent;
import net.minecraftforge.fml.common.event.FMLModIdMappingEvent.*;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(value = ModRemapping.class, remap = false)
public abstract class MixinFMLModIdMappingEventModRemapping {

    @ShadowConstructor
    public void ModRemapping(FMLModIdMappingEvent outer, int oldId, int newId, ResourceLocation tag, RemapTarget type) {}

    @NewConstructor
    public void ModRemapping(FMLModIdMappingEvent outer, int oldId, int newId, String tag, RemapTarget type) {
        ModRemapping(outer, oldId, newId, new ResourceLocation(tag), type);
    }
}
