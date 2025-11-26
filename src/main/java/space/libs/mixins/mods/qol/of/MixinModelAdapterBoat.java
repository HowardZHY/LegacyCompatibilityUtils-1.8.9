package space.libs.mixins.mods.qol.of;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.model.ModelRenderer;
import org.spongepowered.asm.mixin.*;

@Pseudo
@Mixin(targets = "net.optifine.entity.model.ModelAdapterBoat", remap = false)
public class MixinModelAdapterBoat {

    /**
     * @author HowardZHY
     * @reason 1.9+ Custom Model Compat
     */
    @Dynamic
    @Overwrite
    public ModelRenderer getModelRenderer(ModelBase model, String modelPart) {
        if (!(model instanceof ModelBoat)) {
            return null;
        } else {
            ModelBoat modelboat = (ModelBoat) model;
            if (modelPart == null) {
                return new ModelRenderer(modelboat);
            }
            switch (modelPart) {
                case "bottom":
                    return modelboat.boatSides[0];
                case "back":
                    return modelboat.boatSides[1];
                case "front":
                    return modelboat.boatSides[2];
                case "right":
                    return modelboat.boatSides[3];
                case "left":
                    return modelboat.boatSides[4];
                default:
                    return new ModelRenderer(modelboat);
            }
        }
    }
}
