package space.libs.mixins.block;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(BlockState.StateImplementation.class)
public abstract class MixinBlockStateImplementatio {

    @ShadowConstructor
    protected void StateImplementation(Block blockIn, ImmutableMap<IProperty<?>, Comparable<?>> propertiesIn) {}

    @NewConstructor
    public void StateImplementation(Block blockIn, ImmutableMap<IProperty<?>, Comparable<?>> propertiesIn, Object o) {
        StateImplementation(blockIn, propertiesIn);
    }

}
