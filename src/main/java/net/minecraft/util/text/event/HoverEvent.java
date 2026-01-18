package net.minecraft.util.text.event;

import com.google.common.collect.Maps;
import net.minecraft.util.text.*;
import java.util.Map;

public class HoverEvent extends net.minecraft.event.HoverEvent {

    public Action field_150704_a;

    public ITextComponent field_150703_b;

    public HoverEvent(Action action, ITextComponent component) {
        super(toOriginal(action), component);
        this.field_150704_a = action;
        this.field_150703_b = component;
    }

    public Action func_150701_a() {
        return this.field_150704_a;
    }

    public ITextComponent func_150702_b() {
        return this.field_150703_b;
    }

    public static HoverEvent from(net.minecraft.event.HoverEvent event) {
        if (event instanceof HoverEvent) {
            return (HoverEvent) event;
        } else {
            if (event != null) {
                return new HoverEvent(Action.values()[event.getAction().ordinal()], (ITextComponent) event.getValue());
            }
            return new HoverEvent(Action.SHOW_TEXT, new TextComponentString("Null HoverEvent"));
        }
    }

    public static net.minecraft.event.HoverEvent.Action toOriginal(HoverEvent.Action action) {
        if (action == null) {
            return net.minecraft.event.HoverEvent.Action.SHOW_TEXT;
        } else {
            return net.minecraft.event.HoverEvent.Action.getValueByCanonicalName(action.func_150685_b());
        }
    }

    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public enum Action {
        SHOW_TEXT("show_text", true),
        SHOW_ACHIEVEMENT("show_achievement", true),
        SHOW_ITEM("show_item", true),
        SHOW_ENTITY("show_entity", true);

        public static final Map<String, Action> field_150690_d = Maps.newHashMap();

        public final boolean field_150691_e;

        public final String field_150688_f;

        Action(String p_i45157_3_, boolean p_i45157_4_) {
            this.field_150688_f = p_i45157_3_;
            this.field_150691_e = p_i45157_4_;
        }

        public boolean func_150686_a() {
            return this.field_150691_e;
        }

        public String func_150685_b() {
            return this.field_150688_f;
        }

        public static Action func_150684_a(String p_150684_0_) {
            return field_150690_d.get(p_150684_0_);
        }

        static {
            for (HoverEvent.Action action : values()) {
                field_150690_d.put(action.func_150685_b(), action);
            }
        }
    }
}
