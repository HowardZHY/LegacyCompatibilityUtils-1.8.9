package space.libs.mixins.forge;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLModIdMappingEvent;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(value = FMLModIdMappingEvent.ModRemapping.class, remap = false)
public abstract class MixinModRemapping {

    @ShadowConstructor
    public void ModRemapping(FMLModIdMappingEvent outer, int oldId, int newId, ResourceLocation tag, FMLModIdMappingEvent.RemapTarget type) {}

    @NewConstructor
    public void ModRemapping(FMLModIdMappingEvent outer, int oldId, int newId, String tag, FMLModIdMappingEvent.RemapTarget type) {
        ModRemapping(outer, oldId, newId, new ResourceLocation(tag), type);
    }
}
