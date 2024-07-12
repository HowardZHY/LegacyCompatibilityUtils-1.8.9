package space.libs.mixins.client.interfaces;

import net.minecraftforge.client.model.IModelPart;
import net.minecraftforge.client.model.IRetexturableModel;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(IRetexturableModel.class)
public interface MixinIRetexturableModel extends IModelPart {

}
