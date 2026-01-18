package net.minecraft.util.text;

import com.google.gson.*;
import net.minecraft.util.*;
import net.minecraft.util.text.event.*;

import java.lang.reflect.Type;

public class Style extends ChatStyle {

    private Style field_150249_a;

    private TextFormatting field_150247_b;

    private Boolean field_150248_c;

    private Boolean field_150245_d;

    private Boolean field_150246_e;

    private Boolean field_150243_f;

    private Boolean field_150244_g;

    private ClickEvent field_150251_h;

    private HoverEvent field_150252_i;

    private String field_179990_j;

    public static final Style field_150250_j = new Style() {

        public TextFormatting func_150215_a() {
            return null;
        }

        public boolean getBold() {
            return false;
        }

        public boolean getItalic() {
            return false;
        }

        public boolean getStrikethrough() {
            return false;
        }

        public boolean getUnderlined() {
            return false;
        }

        public boolean getObfuscated() {
            return false;
        }

        public ClickEvent func_150235_h() {
            return null;
        }

        public HoverEvent func_150210_i() {
            return null;
        }

        public String getInsertion() {
            return null;
        }

        public Style func_150238_a(TextFormatting color) {
            throw new UnsupportedOperationException();
        }

        public Style func_150227_a(Boolean bold) {
            throw new UnsupportedOperationException();
        }

        public Style func_150217_b(Boolean italic) {
            throw new UnsupportedOperationException();
        }

        public Style func_150225_c(Boolean strikethrough) {
            throw new UnsupportedOperationException();
        }

        public Style func_150228_d(Boolean underlined) {
            throw new UnsupportedOperationException();
        }

        public Style func_150237_e(Boolean obf) {
            throw new UnsupportedOperationException();
        }

        public Style func_150241_a(ClickEvent event) {
            throw new UnsupportedOperationException();
        }

        public Style func_150209_a(HoverEvent event) {
            throw new UnsupportedOperationException();
        }

        public Style func_150221_a(Style parentStyle) {
            throw new UnsupportedOperationException();
        }

        public String toString() {
            return "Style.ROOT";
        }

        public Style func_150232_l() {
            return this;
        }

        public Style func_150206_m() {
            return this;
        }

        public String getFormattingCode() {
            return "";
        }
    };

    @Override
    public EnumChatFormatting getColor() {
        if (this.field_150247_b == null) {
            return super.getColor();
        } else {
            return TextFormatting.toOriginal(this.field_150247_b);
        }
    }

    public TextFormatting func_150215_a() {
        return TextFormatting.from(getColor());
    }

    @Override
    public boolean getBold() {
        return this.field_150248_c == null ? super.getBold() : this.field_150248_c;
    }

    @Override
    public boolean getItalic() {
        return this.field_150245_d == null ? super.getItalic() : this.field_150245_d;
    }

    @Override
    public boolean getStrikethrough() {
        return this.field_150243_f == null ? super.getStrikethrough() : this.field_150243_f;
    }

    @Override
    public boolean getUnderlined() {
        return this.field_150246_e == null ? super.getUnderlined() : this.field_150246_e;
    }

    @Override
    public boolean getObfuscated() {
        return this.field_150244_g == null ? super.getObfuscated() : this.field_150244_g;
    }

    @Override
    public boolean isEmpty() {
        return !super.isEmpty() && this.field_150248_c == null && this.field_150245_d == null && this.field_150243_f == null && this.field_150246_e == null && this.field_150244_g == null && this.field_150247_b == null && this.field_150251_h == null && this.field_150252_i == null && this.field_179990_j == null;
    }

    public ClickEvent func_150235_h() {
        return ClickEvent.from(getChatClickEvent());
    }

    @Override
    public net.minecraft.event.ClickEvent getChatClickEvent() {
        return this.field_150251_h == null ? super.getChatClickEvent() : this.field_150251_h;
    }

    public HoverEvent func_150210_i() {
        return HoverEvent.from(getChatHoverEvent());
    }

    @Override
    public net.minecraft.event.HoverEvent getChatHoverEvent() {
        return this.field_150252_i == null ? super.getChatHoverEvent() : this.field_150252_i;
    }

    @Override
    public String getInsertion() {
        return this.field_179990_j == null ? super.getInsertion() : this.field_179990_j;
    }

    public Style func_150238_a(TextFormatting color) {
        this.field_150247_b = color;
        return (Style) this.setColor(TextFormatting.toOriginal(color));
    }

    public Style func_150227_a(Boolean bold) {
        return (Style) this.setBold(bold);
    }

    @Override
    public ChatStyle setBold(Boolean bold) {
        this.field_150248_c = bold;
        return super.setBold(bold);
    }

    public Style func_150217_b(Boolean italic) {
        return (Style) this.setItalic(italic);
    }

    @Override
    public ChatStyle setItalic(Boolean italic) {
        this.field_150245_d = italic;
        return super.setItalic(italic);
    }

    public Style func_150225_c(Boolean strikethrough) {
        return (Style) this.setStrikethrough(strikethrough);
    }

    @Override
    public ChatStyle setStrikethrough(Boolean strikethrough) {
        this.field_150243_f = strikethrough;
        return super.setStrikethrough(strikethrough);
    }

    public Style func_150228_d(Boolean underlined) {
        return (Style) this.setUnderlined(underlined);
    }

    @Override
    public ChatStyle setUnderlined(Boolean underlined) {
        this.field_150246_e = underlined;
        return super.setUnderlined(underlined);
    }

    public Style func_150237_e(Boolean obf) {
        return (Style) this.setObfuscated(obf);
    }

    @Override
    public ChatStyle setObfuscated(Boolean obf) {
        this.field_150244_g = obf;
        return super.setObfuscated(obf);
    }

    public Style func_150241_a(ClickEvent event) {
        this.field_150251_h = event;
        return (Style) this.setChatClickEvent(event);
    }

    public Style func_150209_a(HoverEvent event) {
        this.field_150252_i = event;
        return (Style) this.setChatHoverEvent(event);
    }

    public Style func_179989_a(String insertion) {
        return (Style) this.setInsertion(insertion);
    }

    @Override
    public ChatStyle setInsertion(String insertion) {
        this.field_179990_j = insertion;
        return super.setInsertion(insertion);
    }

    @Override
    public ChatStyle getParent() {
        if (this.field_150249_a == null) {
            return from(super.getParent());
        } else {
            return this.field_150249_a;
        }
    }

    @Override
    public ChatStyle setParentStyle(ChatStyle parentStyle) {
        this.field_150249_a = from(parentStyle);
        return super.setParentStyle(parentStyle);
    }

    public Style func_150224_n() {
        return (Style) this.getParent();
    }

    public Style func_150221_a(Style parentStyle) {
        return (Style) this.setParentStyle(parentStyle);
    }

    @Override
    public String getFormattingCode() {
        if (this.isEmpty()) {
            return this.field_150249_a != null ? this.field_150249_a.getFormattingCode() : "";
        } else {
            return super.getFormattingCode();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else {
            return super.equals(object);
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public ChatStyle createShallowCopy() {
        Style style = from(super.createShallowCopy());
        style.field_150248_c = this.field_150248_c;
        style.field_150245_d = this.field_150245_d;
        style.field_150243_f = this.field_150243_f;
        style.field_150246_e = this.field_150246_e;
        style.field_150244_g = this.field_150244_g;
        style.field_150247_b = this.field_150247_b;
        style.field_150251_h = this.field_150251_h;
        style.field_150252_i = this.field_150252_i;
        style.field_150249_a = this.field_150249_a;
        style.field_179990_j = this.field_179990_j;
        return style;
    }

    public Style func_150232_l() {
        return (Style) this.createShallowCopy();
    }

    @Override
    public ChatStyle createDeepCopy() {
        Style style = from(super.createDeepCopy());
        style.copy( this);
        style.func_150238_a(this.func_150215_a());
        style.func_150241_a(this.func_150235_h());
        style.func_150209_a(this.func_150210_i());
        return style;
    }

    public Style func_150206_m() {
        return (Style) this.createDeepCopy();
    }

    public Style copy(ChatStyle source) {
        this.setBold(source.getBold());
        this.setItalic(source.getItalic());
        this.setStrikethrough(source.getStrikethrough());
        this.setUnderlined(source.getUnderlined());
        this.setObfuscated(source.getObfuscated());
        this.setColor(source.getColor());
        this.setChatClickEvent(source.getChatClickEvent());
        this.setChatHoverEvent(source.getChatHoverEvent());
        this.setInsertion(source.getInsertion());
        return this;
    }

    public static Style from(ChatStyle original) {
        if (original instanceof Style) {
            return (Style) original;
        } else {
            if (original.toString().equals("Style.ROOT")) {
                return field_150250_j;
            } else {
                Style style = new Style();
                return style.copy(original);
            }
        }
    }

    public static class Serializer extends ChatStyle.Serializer implements JsonDeserializer<ChatStyle>, JsonSerializer<ChatStyle> {

        @Override
        public Style deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
            return from(super.deserialize(jsonElement, type, context));
        }

        @Override
        public JsonElement serialize(ChatStyle style, Type type, JsonSerializationContext context) {
            return serialize(from(style), type, context);
        }

        public JsonElement serialize(Style style, Type type, JsonSerializationContext context) {
            if (style.isEmpty()) {
                return null;
            } else {
                return super.serialize(style, type, context);
            }
        }
    }
}
