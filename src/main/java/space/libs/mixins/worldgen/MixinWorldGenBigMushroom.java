package space.libs.mixins.worldgen;

import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowSuperConstructor;

@SuppressWarnings("unused")
@Mixin(WorldGenBigMushroom.class)
public class MixinWorldGenBigMushroom {

    @ShadowSuperConstructor
    public void WorldGenerator(boolean notify) {}

    @NewConstructor
    public void WorldGenBigMushroom(int p_i2017_1_) {
        this.WorldGenerator(true);
    }
}
