package space.libs.mixins.client.forge;

import net.minecraftforge.client.model.IModelPart;
import net.minecraftforge.client.model.b3d.B3DLoader;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = B3DLoader.ModelWrapper.class, remap = false)
public abstract class MixinB3DLoaderModelWrapper implements IModelPart {}
