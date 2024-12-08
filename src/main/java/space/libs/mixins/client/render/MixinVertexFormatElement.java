package space.libs.mixins.client.render;

import net.minecraft.client.renderer.vertex.VertexFormatElement;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.interfaces.IVertexFormatElement;

@SuppressWarnings("unused")
@Mixin(value = VertexFormatElement.class, priority = 400)
public class MixinVertexFormatElement implements IVertexFormatElement {

    @Shadow
    @Final
    private VertexFormatElement.EnumType type;

    @Shadow
    @Final
    private VertexFormatElement.EnumUsage usage;

    @Shadow
    private int index;

    @Shadow
    private int elementCount;

    /** offset */
    private int field_177376_f = 0;

    /** setOffset */
    @Override
    public void func_177371_a(int p_177371_1_) {
        this.field_177376_f = p_177371_1_;
    }

    /** getOffset */
    @Override
    public int func_177373_a() {
        return this.field_177376_f;
    }

    /**
     * @author HowardZHY
     * @reason 1.8.0
     */
    @Overwrite
    public int hashCode() {
        int i = this.type.hashCode();
        i = 31 * i + this.usage.hashCode();
        i = 31 * i + this.index;
        i = 31 * i + this.elementCount;
        i = 31 * i + this.field_177376_f;
        return i;
    }

}
