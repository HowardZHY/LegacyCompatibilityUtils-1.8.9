package space.libs.mixins;

import net.minecraft.block.BlockChest;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@SuppressWarnings("unused")
@Mixin(BlockChest.class)
public class MixinBlockChest {

    public Random field_149955_b = new Random();
}
