package space.libs.mixins.forge;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.RegistryDelegate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("all")
@Mixin(value = RegistryDelegate.Delegate.class, remap = false)
public class MixinRegistryDelegate {

    @Shadow
    private ResourceLocation name;

    public void setName(String name) {
        this.name = new ResourceLocation(name);
    }

}
