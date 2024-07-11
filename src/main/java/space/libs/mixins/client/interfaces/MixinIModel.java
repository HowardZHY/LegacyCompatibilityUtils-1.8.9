package space.libs.mixins.client.interfaces;

import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.IModelPart;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(IModel.class)
public interface MixinIModel extends IModelPart {

}
