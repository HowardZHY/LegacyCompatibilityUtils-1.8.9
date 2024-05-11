package space.libs.mixins.client.render;

import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import space.libs.interfaces.IVertexFormatElement;

import java.util.List;

@SuppressWarnings("unused")
@Mixin(VertexFormat.class)
public abstract class MixinVertexFormat {

    @Shadow
    private int nextOffset;

    @Final
    @Shadow
    private List<VertexFormatElement> elements;

    @Final
    @Shadow
    private static Logger LOGGER = LogManager.getLogger();

    @Shadow
    public VertexFormat addElement(VertexFormatElement element) {
        return (VertexFormat) (Object) this;
    }

    public void func_177349_a(VertexFormatElement element) {
        this.addElement(element);
    }

    @Inject(method = "addElement", at = @At(target = "Ljava/util/List;add(Ljava/lang/Object;)Z", value = "INVOKE_ASSIGN", ordinal = 1, shift = At.Shift.AFTER))
    public void addElement(VertexFormatElement element, CallbackInfoReturnable<VertexFormat> cir) {
        IVertexFormatElement accessor = (IVertexFormatElement) element;
        accessor.func_177371_a(this.nextOffset);
    }

}
