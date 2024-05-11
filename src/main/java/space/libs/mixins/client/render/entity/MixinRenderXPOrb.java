package space.libs.mixins.client.render.entity;

import net.minecraft.client.renderer.entity.RenderXPOrb;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("all")
@Mixin(RenderXPOrb.class)
public class MixinRenderXPOrb {
    @Shadow
    private static final ResourceLocation experienceOrbTextures = new ResourceLocation("textures/entity/experience_orb.png");

    public ResourceLocation func_180555_a(EntityXPOrb var1) {
        return experienceOrbTextures;
    }
}
