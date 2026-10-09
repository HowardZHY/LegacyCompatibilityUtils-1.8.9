package space.libs.mixins.item;

import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

import static net.minecraft.item.crafting.RecipesBanners.*;

@SuppressWarnings("all")
@Mixin(
    value = {
        RecipeAddPattern.class,
        RecipeDuplicatePattern.class
    },
    remap = false
)
public class MixinRecipesBannersPattern {

    @ShadowConstructor
    private void RecipesBanners() {}

    @NewConstructor
    private void RecipesBanners(Object o) {
        this.RecipesBanners();
    }
}
