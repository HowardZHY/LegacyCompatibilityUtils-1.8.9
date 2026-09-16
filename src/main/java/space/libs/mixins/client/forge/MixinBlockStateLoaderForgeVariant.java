package space.libs.mixins.client.forge;

import com.google.common.collect.ImmutableMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.util.cursedmixinextensions.annotations.NewConstructor;
import space.libs.util.cursedmixinextensions.annotations.ShadowConstructor;

@SuppressWarnings("unused")
@Mixin(targets = "net.minecraftforge.client.model.BlockStateLoader$ForgeVariant", remap = false)
public abstract class MixinBlockStateLoaderForgeVariant {

    @ShadowConstructor
    public void ForgeVariant(ResourceLocation model, IModelState state, boolean uvLock, boolean smooth, boolean gui3d, int weight, ImmutableMap<String, String> textures, ImmutableMap<String, BlockStateLoader.SubModel> parts, ImmutableMap<String, String> customData) {}

    @NewConstructor
    public void ForgeVariant(ResourceLocation model, IModelState state, boolean uvLock, int weight, ImmutableMap<String, String> textures, ImmutableMap<String, BlockStateLoader.SubModel> parts, ImmutableMap<String, String> customData) {
        ForgeVariant(model, state, uvLock, true, true, weight, textures, parts, customData);
    }

    @Shadow
    private IModel runModelHooks(IModel base, boolean smooth, boolean gui3d, ImmutableMap<String, String> textureMap, ImmutableMap<String, String> customData) {
        throw new AbstractMethodError();
    }

    public IModel runModelHooks(IModel base, ImmutableMap<String, String> textureMap, ImmutableMap<String, String> customData) {
        return this.runModelHooks(base, true, true, textureMap, customData);
    }
}
