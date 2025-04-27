package space.libs.mixins.worldgen;

import net.minecraft.world.biome.BiomeDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@SuppressWarnings("unused")
@Mixin(BiomeDecorator.class)
public class MixinBiomeDecorator {

    @Shadow
    public Random randomGenerator;

    public int nextInt(int i) {
        if (i <= 1) {
            return 0;
        }
        return this.randomGenerator.nextInt(i);
    }
}
