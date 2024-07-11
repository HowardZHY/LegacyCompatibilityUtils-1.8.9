package space.libs.mixins;

import net.minecraft.block.BlockBrewingStand;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@SuppressWarnings("unused")
@Mixin(BlockBrewingStand.class)
public class MixinBlockBrewingStand {

    public Random field_149961_a = new Random();

}
