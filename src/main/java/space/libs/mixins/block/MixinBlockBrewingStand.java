package space.libs.mixins.block;

import net.minecraft.block.BlockBrewingStand;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@SuppressWarnings("unused")
@Mixin(BlockBrewingStand.class)
public abstract class MixinBlockBrewingStand {

    public Random field_149961_a = new Random();
}
