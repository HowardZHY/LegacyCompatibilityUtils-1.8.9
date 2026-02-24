package space.libs.mixins.client.forge;

import net.minecraftforge.client.model.BlockStateLoader;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = BlockStateLoader.class, remap = false)
public abstract class MixinBlockStateLoader {}
