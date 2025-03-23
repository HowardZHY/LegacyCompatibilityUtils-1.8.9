package space.libs.mixins.forge;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(value = FMLMissingMappingsEvent.MissingMapping.class, remap = false)
public class MixinFMLMissingMappingsEvent {

    @ShadowConstructor
    public void MissingMapping(GameRegistry.Type type, ResourceLocation name, int id) {}

    @NewConstructor
    public void MissingMapping(GameRegistry.Type type, String name, int id) {
        MissingMapping(type, new ResourceLocation(name), id);
    }
}
