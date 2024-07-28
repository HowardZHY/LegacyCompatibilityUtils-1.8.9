package space.libs.mixins.worldgen;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WorldGenAbstractTree.class)
public class MixinWorldGenAbstractTree extends MixinWorldGenerator {

}
