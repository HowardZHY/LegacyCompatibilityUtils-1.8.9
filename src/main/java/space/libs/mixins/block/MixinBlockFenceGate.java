package space.libs.mixins.block;

import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(BlockFenceGate.class)
public class MixinBlockFenceGate {

    @ShadowConstructor
    public void BlockFenceGate(BlockPlanks.EnumType type) {}

    @NewConstructor
    public void BlockFenceGate() {
        BlockFenceGate(BlockPlanks.EnumType.OAK);
    }
}
