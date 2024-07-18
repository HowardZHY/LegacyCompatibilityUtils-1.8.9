package space.libs.mixins;

import net.minecraft.item.crafting.ShapedRecipes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@SuppressWarnings("unused")
@Mixin(ShapedRecipes.class)
public class MixinShapedRecipes {

    @Shadow
    private boolean copyIngredientNBT;

    public ShapedRecipes func_92100_c() {
        this.copyIngredientNBT = true;
        return (ShapedRecipes) (Object) this;
    }
}
