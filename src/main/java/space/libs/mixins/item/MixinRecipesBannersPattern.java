package space.libs.mixins.item;

import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("all")
@Mixin(
    targets = {
        "net.minecraft.item.crafting.RecipesBanners$RecipeAddPattern",
        "net.minecraft.item.crafting.RecipesBanners$RecipeDuplicatePattern"
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
