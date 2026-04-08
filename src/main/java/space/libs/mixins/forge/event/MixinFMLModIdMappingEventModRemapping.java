package space.libs.mixins.forge.event;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLModIdMappingEvent.*;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(ModRemapping.class)
public abstract class MixinFMLModIdMappingEventModRemapping {

    @ShadowConstructor
    public void ModRemapping(int oldId, int newId, ResourceLocation tag, RemapTarget type) {}

    @NewConstructor
    public void ModRemapping(int oldId, int newId, String tag, RemapTarget type) {
        ModRemapping(oldId, newId, new ResourceLocation(tag), type);
    }
}
