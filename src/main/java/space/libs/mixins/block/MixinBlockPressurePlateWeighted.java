package space.libs.mixins.block;

import net.minecraft.block.BlockPressurePlateWeighted;
import net.minecraft.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(BlockPressurePlateWeighted.class)
public class MixinBlockPressurePlateWeighted {

    @ShadowConstructor
    protected void BlockPressurePlateWeighted(Material p_i46379_1_, int p_i46379_2_) {}

    @NewConstructor
    public void BlockPressurePlateWeighted(String s, Material m, int i) {
        this.BlockPressurePlateWeighted(m, i);
    }
}
