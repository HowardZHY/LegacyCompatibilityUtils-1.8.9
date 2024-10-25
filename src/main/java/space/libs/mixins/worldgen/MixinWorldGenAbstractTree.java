package space.libs.mixins.worldgen;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = WorldGenAbstractTree.class, priority = 100)
public abstract class MixinWorldGenAbstractTree extends MixinWorldGenerator {

}
