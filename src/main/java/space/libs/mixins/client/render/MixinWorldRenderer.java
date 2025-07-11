package space.libs.mixins.client.render;

import net.minecraft.client.renderer.SwitchEnumUsage;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.WorldRenderer_2;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.client.util.QuadComparator;

import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import space.libs.interfaces.IVertexFormatElement;
import space.libs.interfaces.IWorldRenderer;
import space.libs.interfaces.IWorldRendererState;
import space.libs.util.MappedName;
import space.libs.util.client.ClientUtils;

import java.nio.*;
import java.util.List;
import java.util.PriorityQueue;

import static net.minecraft.client.renderer.vertex.DefaultVertexFormats.*;

@SuppressWarnings("unused")
@Mixin(value = WorldRenderer.class, priority = 100)
public abstract class MixinWorldRenderer implements IWorldRenderer {

    @Shadow
    private boolean isDrawing;

    /** 1.8's needsUpdate */
    @Shadow
    private boolean noColor;

    @Shadow
    private double xOffset;

    @Shadow
    private double yOffset;

    @Shadow
    private double zOffset;

    @Shadow
    private int drawMode;

    @Shadow
    private int vertexCount;

    @Shadow
    private int vertexFormatIndex;

    @Shadow
    private ByteBuffer byteBuffer;

    @Shadow
    private IntBuffer rawIntBuffer;

    @Shadow
    private FloatBuffer rawFloatBuffer;

    @Shadow
    private VertexFormat vertexFormat;

    @Shadow
    private VertexFormatElement vertexFormatElement;

    @Shadow
    public abstract void begin(int glMode, VertexFormat format);

    @Shadow public abstract WorldRenderer lightmap(int u, int v);

    @Shadow public abstract WorldRenderer color(int red, int green, int blue, int alpha);

    @Shadow public abstract WorldRenderer color(float red, float green, float blue, float alpha);

    @Shadow public abstract WorldRenderer pos(double x, double y, double z);

    @Shadow public abstract WorldRenderer normal(float x, float y, float z);

    @Shadow public abstract WorldRenderer.State getVertexState();

    @Shadow public abstract int getBufferSize();

    @Shadow public abstract void growBuffer(int amount);

    @Shadow public abstract void reset();

    @Shadow public abstract void finishDrawing();

    @Shadow public abstract void endVertex();

    /** Flags to mark legacy behavior */
    public boolean LegacyPOSITION, LegacyCOLORF, LegacyCOLORI, LegacyNORMAL, LegacyLITMAP;

    public int[] Colors;

    public float[] ColorsF;

    public float[] Normals;

    @MappedName("textureU")
    public double field_178998_e;

    @MappedName("textureV")
    public double field_178995_f;

    @MappedName("brightness")
    public int field_178996_g;

    @MappedName("normal")
    public int field_179003_o;

    @MappedName("color")
    public int field_179007_h;

    @MappedName("rawBufferIndex")
    public int field_179008_i;

    @MappedName("bufferSize")
    public int field_179009_s;

    @MappedName("byteIndex")
    public int field_179012_p;

    @MappedName("setColorOpaque")
    public void func_78913_a(int red, int green, int blue) {
        this.func_178961_b(red, green, blue, 255);
    }

    @MappedName("setColorRGBA_F")
    public void func_178960_a(float red, float green, float blue, float alpha) {
        this.LegacyCOLORF = true;
        this.ColorsF = new float[]{red, green, blue, alpha};
        this.func_178961_b((int)(red * 255.0F), (int)(green * 255.0F), (int)(blue * 255.0F), (int)(alpha * 255.0F));
    }

    @MappedName("setColorRGBA")
    public void func_178961_b(int red, int green, int blue, int alpha) {
        this.LegacyCOLORI = true;
        this.Colors = new int[]{red, green, blue, alpha};
        for (int i = 0; i < 4; i++) {
            if (Colors[i] > 255) {
                Colors[i] = 255;
            } else if (Colors[i] < 0) {
                Colors[i] = 0;
            }
        }
        if (!this.noColor) {
            red = this.Colors[0];green = this.Colors[1];blue = this.Colors[2];alpha = this.Colors[3];
            if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
                this.field_179007_h = alpha << 24 | blue << 16 | green << 8 | red;
            } else {
                this.field_179007_h = red << 24 | green << 16 | blue << 8 | alpha;
            }
        }
    }

    @MappedName("setBrightness")
    public void func_178963_b(int bright) {
        this.LegacyLITMAP = true;
        this.field_178996_g = bright;
    }

    @MappedName("startDrawing")
    public void func_178964_a(int mode) {
        if (this.isDrawing) {
            throw new IllegalStateException("Already building!");
        } else {
            this.LegacyPOSITION = true;
            this.isDrawing = true;
            this.reset();
            this.drawMode = mode;
            this.noColor = false;
            this.byteBuffer.limit(this.byteBuffer.capacity());
        }
    }

    @MappedName("setVertexFormat")
    public void func_178967_a(VertexFormat format) {
        this.vertexFormat = new VertexFormat(format);
    }

    @MappedName("startDrawingQuads")
    public void func_178970_b() {
        this.func_178964_a(7);
    }

    @MappedName("getVertexState")
    public WorldRenderer.State func_178971_a(float x, float y, float z) {
        int[] i = new int[this.field_179008_i];
        PriorityQueue<Integer> queue = new PriorityQueue<>(
            this.field_179008_i,
            new QuadComparator(
                this.rawFloatBuffer,
                (float) ((double) x + this.xOffset),
                (float) ((double) y + this.yOffset),
                (float) ((double) z + this.zOffset),
                this.vertexFormat.getNextOffset() / 4
            )
        );
        int nextOffset = this.vertexFormat.getNextOffset();
        int quadStep = this.vertexFormat.getNextOffset() / 4 * 4;
        int j;
        for (j = 0; j < this.field_179008_i; j += nextOffset) {
            queue.add(j);
        }
        for (j = 0; !queue.isEmpty(); j += nextOffset) {
            int k = queue.remove();
            int indexQuad;

            for (indexQuad = 0; indexQuad < nextOffset; ++indexQuad) {
                i[j + indexQuad] = this.rawIntBuffer.get(k + indexQuad);
            }
        }
        this.rawIntBuffer.clear();
        this.rawIntBuffer.put(i);
        return this.getVertexState();
    }

    @MappedName("setColorRGBA_I ")
    public void func_178974_a(int rgb, int a) {
        int r = rgb >> 16 & 255;
        int g = rgb >> 8 & 255;
        int b = rgb & 255;
        this.func_178961_b(r, g, b, a);
    }

    @MappedName("putNormal")
    public void func_178975_e(float x, float y, float z) {
        byte b1 = (byte)((int)(x * 127.0F));
        byte b2 = (byte)((int)(y * 127.0F));
        byte b3 = (byte)((int)(z * 127.0F));
        int i = this.vertexFormat.getNextOffset() >> 2;
        int j = (this.vertexCount - 4) * i + this.vertexFormat.getNormalOffset() / 4;
        this.field_179003_o = b1 & 255 | (b2 & 255) << 8 | (b3 & 255) << 16;
        this.rawIntBuffer.put(j, this.field_179003_o);
        this.rawIntBuffer.put(j + i, this.field_179003_o);
        this.rawIntBuffer.put(j + i * 2, this.field_179003_o);
        this.rawIntBuffer.put(j + i * 3, this.field_179003_o);
    }

    @MappedName("getByteIndex")
    public int func_178976_e() {
        return this.field_179012_p;
    }

    @MappedName("finishDrawing")
    public int func_178977_d() {
        this.finishDrawing();
        this.field_179012_p = this.getBufferSize() * 4;
        return this.field_179012_p;
    }

    @MappedName("setNormal")
    public void func_178980_d(float x, float y, float z) {
        this.LegacyNORMAL = true;
        this.Normals = new float[]{x, y, z};
        if (this.LegacyPOSITION) {
            return;
        }
        LogManager.getLogger().warn("Unknown setNormal call?");
        if (!this.vertexFormat.hasNormal()) {
            VertexFormatElement element = new VertexFormatElement(0, VertexFormatElement.EnumType.BYTE, VertexFormatElement.EnumUsage.NORMAL, 3);
            this.vertexFormat.addElement(element);
            this.vertexFormat.addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.UBYTE, VertexFormatElement.EnumUsage.PADDING, 1));
        }
        byte b1 = (byte)((int)(x * 127.0F));
        byte b2 = (byte)((int)(y * 127.0F));
        byte b3 = (byte)((int)(z * 127.0F));
        this.field_179003_o = b1 & 255 | (b2 & 255) << 8 | (b3 & 255) << 16;
    }

    @MappedName("setColorOpaque_B")
    public void func_178982_a(byte r, byte g, byte b) {
        this.func_78913_a(r & 255, g & 255, b & 255);
    }

    @MappedName("growBuffer")
    public void func_178983_e(int amount) {
        this.growBuffer(amount);
    }

    @MappedName("addVertex")
    public void func_178984_b(double x, double y, double z) {
        if (this.LegacyCOLORI) {
            this.setVertexFormatAndElement(new VertexFormat(POSITION_COLOR));
            if (this.LegacyCOLORF) {
                this.pos(x, y, z).color(this.ColorsF[0], this.ColorsF[1], this.ColorsF[2], this.ColorsF[3]).endVertex();
            } else {
                this.pos(x, y, z).color(this.Colors[0], this.Colors[1], this.Colors[2], this.Colors[3]).endVertex();
            }
            return;
        }
        if (this.LegacyPOSITION) {
            if (this.LegacyNORMAL) {
                this.setVertexFormatAndElement(new VertexFormat(POSITION_NORMAL));
                this.pos(x, y, z).normal(this.Normals[0], this.Normals[1], this.Normals[2]).endVertex();
                return;
            }
            this.setVertexFormatAndElement(new VertexFormat(POSITION));
            this.pos(x, y, z).endVertex();
            return;
        }

        LogManager.getLogger().warn("Unknown addVertex call, shouldn't go here?");

        if (this.field_179008_i >= this.getBufferSize() - this.vertexFormat.getNextOffset()) {
            this.growBuffer(2097152);
        }
        List<VertexFormatElement> list = this.vertexFormat.getElements();
        int listSize = list.size();
        for (int i = 0; i < listSize; ++i) {
            VertexFormatElement element = list.get(i);
            IVertexFormatElement accessor = (IVertexFormatElement) element;
            int j = accessor.func_177373_a() >> 2;
            int l = this.field_179008_i + j;
            switch (SwitchEnumUsage.field_178959_a[element.getUsage().ordinal()]) {
                case 1:
                    this.rawIntBuffer.put(l, Float.floatToRawIntBits((float) (x + this.xOffset)));
                    this.rawIntBuffer.put(l + 1, Float.floatToRawIntBits((float) (y + this.yOffset)));
                    this.rawIntBuffer.put(l + 2, Float.floatToRawIntBits((float) (z + this.zOffset)));
                    break;
                case 2:
                    this.rawIntBuffer.put(l, this.field_179007_h);
                    break;
                case 3:
                    if (element.getIndex() == 0) {
                        this.rawIntBuffer.put(l, Float.floatToRawIntBits((float) this.field_178998_e));
                        this.rawIntBuffer.put(l + 1, Float.floatToRawIntBits((float) this.field_178995_f));
                    } else {
                        this.rawIntBuffer.put(l, this.field_178996_g);
                    }
                    break;
                case 4:
                    this.rawIntBuffer.put(l, this.field_179003_o);
            }
        }
        this.field_179008_i += this.vertexFormat.getNextOffset() >> 2;
        ++this.vertexCount;
    }

    /**
     * Set the VertexFormat to prevent NPE
     * Original Code
     * this.func_178992_a(u, v);
     * this.func_178984_b(x, y, z);
     * */
    @MappedName("addVertexWithUV")
    public void func_178985_a(double x, double y, double z, double u, double v) {
        if (this.LegacyCOLORI) {
            if (this.LegacyLITMAP) {
                int j = this.field_178996_g >> 16 & '\uffff';
                int k = this.drawMode & '\uffff';
                if (this.LegacyNORMAL) {
                    this.setVertexFormatAndElement(new VertexFormat(ClientUtils.POS_TEX_CO_LM_NO));
                    if (this.LegacyCOLORF) {
                        this.pos(x, y, z).tex(u, v).color(this.ColorsF[0], this.ColorsF[1], this.ColorsF[2], this.ColorsF[3]).lightmap(j, k).normal(Normals[0], Normals[1], Normals[2]).endVertex();
                    } else {
                        this.pos(x, y, z).tex(u, v).color(this.Colors[0], this.Colors[1], this.Colors[2], this.Colors[3]).lightmap(j, k).normal(Normals[0], Normals[1], Normals[2]).endVertex();
                    }
                    return;
                } else {
                    this.setVertexFormatAndElement(new VertexFormat(POSITION_TEX_LMAP_COLOR));
                    if (this.LegacyCOLORF) {
                        this.pos(x, y, z).tex(u, v).lightmap(j, k).color(this.ColorsF[0], this.ColorsF[1], this.ColorsF[2], this.ColorsF[3]).endVertex();
                    } else {
                        this.pos(x, y, z).tex(u, v).lightmap(j, k).color(this.Colors[0], this.Colors[1], this.Colors[2], this.Colors[3]).endVertex();
                    }
                }
                return;
            } else if (this.LegacyNORMAL) {
                this.setVertexFormatAndElement(new VertexFormat(POSITION_TEX_COLOR_NORMAL));
                if (this.LegacyCOLORF) {
                    this.pos(x, y, z).tex(u, v).color(this.ColorsF[0], this.ColorsF[1], this.ColorsF[2], this.ColorsF[3]).normal(Normals[0], Normals[1], Normals[2]).endVertex();
                } else {
                    this.pos(x, y, z).tex(u, v).color(this.Colors[0], this.Colors[1], this.Colors[2], this.Colors[3]).normal(Normals[0], Normals[1], Normals[2]).endVertex();
                }
                return;
            } else {
                this.setVertexFormatAndElement(new VertexFormat(POSITION_TEX_COLOR));
                if (this.LegacyCOLORF) {
                    this.pos(x, y, z).tex(u, v).color(this.ColorsF[0], this.ColorsF[1], this.ColorsF[2], this.ColorsF[3]).endVertex();
                } else {
                    this.pos(x, y, z).tex(u, v).color(this.Colors[0], this.Colors[1], this.Colors[2], this.Colors[3]).endVertex();
                }
                return;
            }
        }
        if (this.LegacyNORMAL) {
            this.setVertexFormatAndElement(new VertexFormat(POSITION_TEX_NORMAL));
            this.pos(x, y, z).tex(u, v).normal(Normals[0], Normals[1], Normals[2]).endVertex();
        } else {
            this.setVertexFormatAndElement(new VertexFormat(POSITION_TEX));
            this.pos(x, y, z).tex(u, v).endVertex();
        }
    }

    @MappedName("setColorOpaque_F")
    public void func_178986_b(float r, float g, float b) {
        this.func_78913_a((int)(r * 255.0F), (int)(g * 255.0F), (int)(b * 255.0F));
    }

    @MappedName("setColorOpaque_I")
    public void func_178991_c(int rgb) {
        int r = rgb >> 16 & 255;
        int g = rgb >> 8 & 255;
        int b = rgb & 255;
        this.func_78913_a(r, g, b);
    }

    @MappedName("setTextureUV")
    public void func_178992_a(double u, double v) {
        if (!this.vertexFormat.hasUvOffset(0) && !this.vertexFormat.hasUvOffset(1)) {
            VertexFormatElement element = new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.UV, 2);
            this.vertexFormat.addElement(element);
        }
        this.field_178998_e = u;
        this.field_178995_f = v;
    }

    @Override
    public boolean hasDrawing() {
        return isDrawing;
    }

    @Override
    public int getVertexFormatIndex() {
        return vertexFormatIndex;
    }

    @Override
    public VertexFormatElement getVertexFormatElement() {
        return vertexFormatElement;
    }

    @Override
    public void setVertexFormatElement(VertexFormatElement element) {
        this.vertexFormatElement = element;
    }

    public void setVertexFormatAndElement(VertexFormat format) {
        this.vertexFormat = format;
        this.vertexFormatElement = format.getElement(this.vertexFormatIndex);
    }

    public void ClearFlags() {
        this.LegacyPOSITION = false;
        this.LegacyCOLORF = false;
        this.LegacyCOLORI = false;
        this.LegacyNORMAL = false;
        this.LegacyLITMAP = false;
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    public void initBufferSize(int bufferSizeIn, CallbackInfo ci) {
        this.field_179009_s = bufferSizeIn;
        this.ClearFlags();
    }

    @Inject(method = "growBuffer", at = @At(
        value = "INVOKE",
        target = "Lnet/minecraft/client/renderer/GLAllocation;createDirectByteBuffer(I)Ljava/nio/ByteBuffer;",
        shift = At.Shift.BEFORE
    ))
    public void growBuffer(int amount, CallbackInfo ci) {
        this.field_179009_s += amount / 4;
    }

    @Inject(method = "getVertexState", at = @At(value = "RETURN"))
    public void getVertexState(CallbackInfoReturnable<WorldRenderer.State> cir) {
        IWorldRendererState accessor = (IWorldRendererState) cir.getReturnValue();
        accessor.setRawBufferIndex(this.field_179008_i);
    }

    @Inject(method = "setVertexState", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/renderer/WorldRenderer$State;getVertexCount()I"))
    public void setVertexState(WorldRenderer.State state, CallbackInfo ci) {
        IWorldRendererState accessor = (IWorldRendererState) state;
        this.field_179008_i = accessor.func_179015_b();
    }

    @Inject(method = "reset", at = @At("HEAD"))
    public void resetBufferIndex(CallbackInfo ci) {
        this.field_179008_i = 0;
    }

    @Inject(method = "putPosition", at = @At("HEAD"))
    public void putPosition(double x, double y, double z, CallbackInfo ci) {
        if (LegacyPOSITION) {
            if (this.field_179008_i >= this.getBufferSize() - this.vertexFormat.getNextOffset()) {
                this.growBuffer(2097152);
            }
        }
    }

    @Inject(method = "addVertexData", at = @At("TAIL"))
    public void addVertexData(int[] vertexData, CallbackInfo ci) {
        this.field_179008_i += vertexData.length;
    }

    @Inject(method = "finishDrawing", at = @At("HEAD"))
    public void finishDrawing(CallbackInfo ci) {
        this.ClearFlags();
    }

    @Dynamic
    @Redirect(method = "func_181662_b", at = @At(value = "FIELD", target = "*:[I", ordinal = 0), remap = false)
    public int[] pos() {
        return WorldRenderer_2.field_181661_a;
    }
}
