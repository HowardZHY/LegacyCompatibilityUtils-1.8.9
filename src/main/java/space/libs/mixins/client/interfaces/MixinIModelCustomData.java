package space.libs.mixins.client.interfaces;

import net.minecraftforge.client.model.*;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(IModelCustomData.class)
public interface MixinIModelCustomData extends IModelPart {}
