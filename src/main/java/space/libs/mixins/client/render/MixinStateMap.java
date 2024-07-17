package space.libs.mixins.client.render;

import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.block.statemap.StateMap;
import org.spongepowered.asm.mixin.Mixin;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

import java.util.List;

@SuppressWarnings("all")
@Mixin(StateMap.class)
public class MixinStateMap {

    @ShadowConstructor
    private void StateMap(IProperty<?> name, String suffix, List< IProperty<? >> ignored) {}

    @NewConstructor
    public void StateMap(IProperty name, String suffix, List ignored, Object o) {
        this.StateMap(name, suffix, ignored);
    }
}
