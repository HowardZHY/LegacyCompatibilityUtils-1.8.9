package space.libs.mixins.client.forge;

import net.minecraftforge.client.model.IModelPart;
import net.minecraftforge.client.model.ItemLayerModel;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemLayerModel.class)
public class MixinItemLayerModel implements IModelPart {

}
