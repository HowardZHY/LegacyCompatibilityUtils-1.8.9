package space.libs.mixins.mods.legacy.justbps;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

@SuppressWarnings("SpellCheckingInspection")
@Pseudo
@Mixin(targets = "net.fybertech.backpackmod.ModelBackpack", remap = false)
public class MixinModelBackpack {

    /**
     * @author HowardZHY
     * @reason Broken Codes
     */
    @Overwrite
    private void renderGlint(Entity entity, ModelRenderer model, float partialTicks) {}

}
