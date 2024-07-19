package space.libs.mixins.forge;

import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets = "net.minecraftforge.fml.common.registry.RegistryDelegate$Delegate", remap = false)
public class MixinRegistryDelegate {

    @Shadow
    private ResourceLocation name;

    public void setName(String name) {
        this.name = new ResourceLocation(name);
    }
}
