package space.libs.mixins.client.render;

import net.minecraft.client.renderer.vertex.VertexFormatElement;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import space.libs.interfaces.IVertexFormatElement;
import space.libs.util.MappedName;

@SuppressWarnings("unused")
@Mixin(value = VertexFormatElement.class, priority = 100)
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

    @MappedName("offset")
    private int field_177376_f = 0;

    @Override
    @MappedName("setOffset")
    public void func_177371_a(int offset) {
        this.field_177376_f = offset;
    }

    @Override
    @MappedName("getOffset")
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
