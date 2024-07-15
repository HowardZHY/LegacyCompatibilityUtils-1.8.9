package space.libs.mixins.client.forge;

import net.minecraftforge.client.model.IModelPart;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("unused")
@Mixin(targets = "net.minecraftforge.client.model.ModelLoader$WeightedPartWrapper")
public class MixinModelLoaderWeightedPartWrapper implements IModelPart {

}
