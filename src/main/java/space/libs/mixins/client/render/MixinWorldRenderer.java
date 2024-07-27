package space.libs.mixins.client.render;

import net.minecraft.client.renderer.SwitchEnumUsage;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.client.util.QuadComparator;

import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import space.libs.interfaces.IVertexFormatElement;
import space.libs.interfaces.IWorldRenderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;
import java.util.PriorityQueue;

import static net.minecraft.client.renderer.vertex.DefaultVertexFormats.*;

@SuppressWarnings("unused")
@Mixin(value = WorldRenderer.class, priority = 100)
public abstract class MixinWorldRenderer implements IWorldRenderer {

    @Shadow
    private boolean isDrawing;

    /** 1.8 needsUpdate */
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

    @Shadow public abstract WorldRenderer pos(double x, double y, double z);

    @Shadow public abstract WorldRenderer color(int red, int green, int blue, int alpha);

    @Shadow public abstract WorldRenderer color(float red, float green, float blue, float alpha);

    @Shadow public abstract WorldRenderer.State getVertexState();

    @Shadow public abstract int getBufferSize();

    @Shadow public abstract void growBuffer(int p_181670_1_);

    @Shadow public abstract void reset();

    @Shadow public abstract void finishDrawing();

    @Shadow public abstract void endVertex();

    /** Flags to mark legacy behavior */
    public boolean LegacyPOSITION;

    public boolean LegacyPOSITIONCOLORF;

    public boolean LegacyPOSITIONCOLORI;

    public int ColorR, ColorG, ColorB, ColorA;

    public float ColorRF, ColorGF, ColorBF, ColorAF;

    /** textureU */
    public double field_178998_e;

    /** textureV */
    public double field_178995_f;

    /** brightness */
    public int field_178996_g;

    /** normal */
    public int field_179003_o;

    /** color */
    public int field_179007_h;

    /** rawBufferIndex */
    public int field_179008_i;

    /** bufferSize */
    public int field_179009_s;

    /** byteIndex */
    public int field_179012_p;

    /** setColorOpaque */
    public void func_78913_a(int red, int green, int blue) {
        this.ColorR = red; this.ColorG = green; this.ColorB = blue; this.ColorA = 255;
        this.func_178961_b(red, green, blue, 255);
    }

    /** setColorRGBA_F */
    public void func_178960_a(float red, float green, float blue, float alpha) {
        this.LegacyPOSITIONCOLORF = true;
        this.ColorRF = red; this.ColorGF = green; this.ColorBF = blue; this.ColorAF = alpha;
        this.func_178961_b((int)(red * 255.0F), (int)(green * 255.0F), (int)(blue * 255.0F), (int)(alpha * 255.0F));
    }

    /** setColorRGBA */
    public void func_178961_b(int red, int green, int blue, int alpha) {
        this.LegacyPOSITIONCOLORI = true;
        this.ColorR = red; this.ColorG = green; this.ColorB = blue; this.ColorA = alpha;
        if (this.LegacyPOSITION) {
            return;
        }
        if (!this.noColor) {
            if (red > 255) {
                red = 255;
            }
            if (green > 255) {
                green = 255;
            }
            if (blue > 255) {
                blue = 255;
            }
            if (alpha > 255) {
                alpha = 255;
            }
            if (red < 0) {
                red = 0;
            }
            if (green < 0) {
                green = 0;
            }
            if (blue < 0) {
                blue = 0;
            }
            if (alpha < 0) {
                alpha = 0;
            }
            VertexFormat format = new VertexFormat(POSITION_COLOR);
            this.vertexFormat = format;
            this.vertexFormatElement = format.getElement(this.vertexFormatIndex);
            if (!this.vertexFormat.hasColor()) {
                VertexFormatElement element = new VertexFormatElement(0, VertexFormatElement.EnumType.UBYTE, VertexFormatElement.EnumUsage.COLOR, 4);
                this.vertexFormat.addElement(element);
            }
            if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
                this.field_179007_h = alpha << 24 | blue << 16 | green << 8 | red;
            } else {
                this.field_179007_h = red << 24 | green << 16 | blue << 8 | alpha;
            }
            this.endVertex();
        }
    }

    /** setBrightness */
    public void func_178963_b(int i) {
        if (!this.vertexFormat.hasUvOffset(1)) {
            if (!this.vertexFormat.hasUvOffset(0)) {
                this.vertexFormat.addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.UV, 2));
            }
            VertexFormatElement element = new VertexFormatElement(1, VertexFormatElement.EnumType.SHORT, VertexFormatElement.EnumUsage.UV, 2);
            this.vertexFormat.addElement(element);
        }
        this.field_178996_g = i;
    }

    /** startDrawing */
    public void func_178964_a(int i) {
        if (this.isDrawing) {
            throw new IllegalStateException("Already building!");
        } else {
            this.isDrawing = true;
            this.reset();
            this.drawMode = i;
            this.noColor = false;
            this.byteBuffer.limit(this.byteBuffer.capacity());
        }
    }

    /** setVertexFormat */
    public void func_178967_a(VertexFormat format) {
        this.vertexFormat = new VertexFormat(format);
    }

    /** startDrawingQuads */
    public void func_178970_b() {
        this.LegacyPOSITION = true;
        this.func_178964_a(7);
    }

    /** getVertexState */
    @SuppressWarnings("all")
    public WorldRenderer.State func_178971_a(float p_178971_1_, float p_178971_2_, float p_178971_3_) {
        int[] i = new int[this.field_179008_i];
        PriorityQueue queue = new PriorityQueue(
            this.field_179008_i,
            new QuadComparator(
                this.rawFloatBuffer,
                (float) ((double) p_178971_1_ + this.xOffset),
                (float) ((double) p_178971_2_ + this.yOffset),
                (float) ((double) p_178971_3_ + this.zOffset),
                this.vertexFormat.getNextOffset() / 4)
        );
        int nextOffset = this.vertexFormat.getNextOffset();
        int quadStep = this.vertexFormat.getNextOffset() / 4 * 4;
        int j;
        for (j = 0; j < this.field_179008_i; j += nextOffset) {
            queue.add(Integer.valueOf(j));
        }
        for (j = 0; !queue.isEmpty(); j += nextOffset) {
            int k = ((Integer)queue.remove()).intValue();
            int indexQuad;

            for (indexQuad = 0; indexQuad < nextOffset; ++indexQuad) {
                i[j + indexQuad] = this.rawIntBuffer.get(k + indexQuad);
            }
        }
        this.rawIntBuffer.clear();
        this.rawIntBuffer.put(i);

        this.field_179020_c = this.field_179008_i;
        return this.getVertexState();
    }

    /** setColorRGBA_I */
    public void func_178974_a(int i, int a) {
        int r = i >> 16 & 255;
        int g = i >> 8 & 255;
        int b = i & 255;
        this.ColorR = r; this.ColorG = g; this.ColorB = b; this.ColorA = a;
        this.func_178961_b(r, g, b, a);
    }

    /** putNormal */
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

    /** getByteIndex */
    public int func_178976_e() {
        return this.field_179012_p;
    }

    /** finishDrawing */
    public int func_178977_d() {
        this.finishDrawing();
        this.field_179012_p = this.getBufferSize() * 4;
        return this.field_179012_p;
    }

    /** setNormal */
    public void func_178980_d(float f1, float f2, float f3) {
        if (!this.vertexFormat.hasNormal()) {
            VertexFormatElement element = new VertexFormatElement(0, VertexFormatElement.EnumType.BYTE, VertexFormatElement.EnumUsage.NORMAL, 3);
            this.vertexFormat.addElement(element);
            this.vertexFormat.addElement(new VertexFormatElement(0, VertexFormatElement.EnumType.UBYTE, VertexFormatElement.EnumUsage.PADDING, 1));
        }

        byte b1 = (byte)((int)(f1 * 127.0F));
        byte b2 = (byte)((int)(f2 * 127.0F));
        byte b3 = (byte)((int)(f3 * 127.0F));
        this.field_179003_o = b1 & 255 | (b2 & 255) << 8 | (b3 & 255) << 16;
    }

    /** setColorOpaque_B */
    public void func_178982_a(byte r, byte g, byte b) {
        this.func_78913_a(r & 255, g & 255, b & 255);
    }

    /** growBuffer */
    public void func_178983_e(int i) {
        this.growBuffer(i);
    }

    /** addVertex */
    public void func_178984_b(double x, double y, double z) {

        if (this.LegacyPOSITIONCOLORF) {
            VertexFormat format = new VertexFormat(POSITION_COLOR);
            this.vertexFormat = format;
            this.vertexFormatElement = format.getElement(this.vertexFormatIndex);
            this.pos(x, y, z).color(this.ColorRF, this.ColorGF, this.ColorBF, this.ColorAF).endVertex();
            return;
        }

        if (this.LegacyPOSITIONCOLORI) {
            VertexFormat format = new VertexFormat(POSITION_COLOR);
            this.vertexFormat = format;
            this.vertexFormatElement = format.getElement(this.vertexFormatIndex);
            this.pos(x, y, z).color(this.ColorR, this.ColorG, this.ColorB, this.ColorA).endVertex();
            return;
        }

        if (this.LegacyPOSITION) {
            VertexFormat format = new VertexFormat(POSITION);
            this.vertexFormat = format;
            this.vertexFormatElement = format.getElement(this.vertexFormatIndex);
            this.pos(x, y, z).endVertex();
            return;
        }

        if (this.field_179008_i >= this.getBufferSize() - this.vertexFormat.getNextOffset()) {
            this.growBuffer(2097152);
        }

        LogManager.getLogger().warn("Unknown addVertex call, shouldn't go here...");

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

    /** addVertexWithUV
     * Set the VertexFormat to prevent NPE
     * Original Code
     * this.func_178992_a(u, v);
     * this.func_178984_b(x, y, z);
     * */
    public void func_178985_a(double x, double y, double z, double u, double v) {
        VertexFormat format = new VertexFormat(POSITION_TEX);
        this.vertexFormat = format;
        this.vertexFormatElement = format.getElement(this.vertexFormatIndex);
        this.pos(x, y, z).tex(u, v).endVertex();
    }

    /** setColorOpaque_F */
    public void func_178986_b(float r, float g, float b) {
        this.func_78913_a((int)(r * 255.0F), (int)(g * 255.0F), (int)(b * 255.0F));
    }

    /** setColorOpaque_I */
    public void func_178991_c(int var1) {
        int r = var1 >> 16 & 255;
        int g = var1 >> 8 & 255;
        int b = var1 & 255;
        this.func_78913_a(r, g, b);
    }

    /** setTextureUV */
    public void func_178992_a(double u, double v) {
        if (!this.vertexFormat.hasUvOffset(0) && !this.vertexFormat.hasUvOffset(1)) {
            VertexFormatElement element = new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUsage.UV, 2);
            this.vertexFormat.addElement(element);
        }
        this.field_178998_e = u;
        this.field_178995_f = v;
    }

    public WorldRenderer getRenderer() {
        return (WorldRenderer) (Object) this;
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    public void initBufferSize(int bufferSizeIn, CallbackInfo ci) {
        this.field_179009_s = bufferSizeIn;
        this.LegacyPOSITION = false;
        this.LegacyPOSITIONCOLORF = false;
        this.LegacyPOSITIONCOLORI = false;
    }

    @Dynamic
    @Inject(method = "growBuffer", at = @At(value = "INVOKE_ASSIGN", target = "Lorg/apache/logging/log4j/Logger;warn(Ljava/lang/String;)V"))
    public void growBuffer(int p_181670_1_, CallbackInfo ci) {
        this.field_179009_s += p_181670_1_ / 4;
    }

    @Inject(method = "setVertexState", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/renderer/WorldRenderer$State;getVertexCount()I"))
    public void setVertexState(WorldRenderer.State state, CallbackInfo ci) {
        this.field_179008_i = this.func_179015_b();
    }

    @Inject(method = "reset", at = @At("HEAD"))
    public void resetBufferIndex(CallbackInfo ci) {
        this.field_179008_i = 0;
    }

    @Inject(method = "putPosition", at = @At("HEAD"))
    public void putPosition(double x, double y, double z, CallbackInfo ci) {
        if (this.field_179008_i >= this.getBufferSize() - this.vertexFormat.getNextOffset()) {
            this.growBuffer(2097152);
        }
    }

    @Inject(method = "addVertexData", at = @At("TAIL"))
    public void addVertexData(int[] vertexData, CallbackInfo ci) {
        this.field_179008_i += vertexData.length;
    }

    /** Reset Flags */
    @Inject(method = "finishDrawing", at = @At("HEAD"))
    public void finishDrawing(CallbackInfo ci) {
        this.LegacyPOSITION = false;
        this.LegacyPOSITIONCOLORF = false;
        this.LegacyPOSITIONCOLORI = false;
    }

    /* State members */

    /** stateRawBufferIndex */
    public int field_179020_c;

    /** getRawBufferIndex */
    public int func_179015_b() {
        return this.field_179020_c;
    }

}
