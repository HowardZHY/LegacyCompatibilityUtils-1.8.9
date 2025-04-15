package space.libs.mixins.client.render;

import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import space.libs.interfaces.IVertexFormatElement;

import java.util.List;

@SuppressWarnings("unused")
@Mixin(value = VertexFormat.class, priority = 2020)
public abstract class MixinVertexFormat {

    @Shadow
    private int nextOffset;

    @Final
    @Mutable
    @Shadow
    private List<VertexFormatElement> elements;

    @Shadow
    public abstract VertexFormat addElement(VertexFormatElement element);

    public void func_177349_a(VertexFormatElement element) {
        this.addElement(element);
    }

    @Inject(method = "addElement", at = @At(target = "Ljava/util/List;add(Ljava/lang/Object;)Z", value = "INVOKE_ASSIGN", ordinal = 1, shift = At.Shift.AFTER))
    public void addElement(VertexFormatElement element, CallbackInfoReturnable<VertexFormat> cir) {
        IVertexFormatElement accessor = (IVertexFormatElement) element;
        accessor.func_177371_a(this.nextOffset);
    }

}
