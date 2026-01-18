package net.minecraft.util.text.event;

import com.google.common.collect.Maps;

import java.util.Map;

public class ClickEvent extends net.minecraft.event.ClickEvent {

    public Action field_150671_a;

    public String field_150670_b;

    public ClickEvent(Action action, String value) {
        super(toOriginal(action), value);
        this.field_150671_a = action;
        this.field_150670_b = value;
    }

    public Action func_150669_a() {
        return this.field_150671_a;
    }

    @Override
    public String getValue() {
        return this.field_150670_b;
    }

    public static ClickEvent from(net.minecraft.event.ClickEvent event) {
        if (event instanceof ClickEvent) {
            return (ClickEvent) event;
        } else {
            if (event != null && event.getAction() != null) {
                if (event.getAction() == net.minecraft.event.ClickEvent.Action.TWITCH_USER_INFO) {
                    return new ClickEvent(Action.SUGGEST_COMMAND, "Unsupported Twitch Info");
                } else {
                    return new ClickEvent(Action.func_150672_a(event.getAction().getCanonicalName()), event.getValue());
                }
            }
            return new ClickEvent(Action.SUGGEST_COMMAND, "Null ClickEvent");
        }
    }

    public static net.minecraft.event.ClickEvent.Action toOriginal(Action action) {
        if (action == null) {
            return net.minecraft.event.ClickEvent.Action.SUGGEST_COMMAND;
        } else {
            return net.minecraft.event.ClickEvent.Action.getValueByCanonicalName(action.func_150673_b());
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
        OPEN_URL("open_url", true),
        OPEN_FILE("open_file", false),
        RUN_COMMAND("run_command", true),
        SUGGEST_COMMAND("suggest_command", true),
        CHANGE_PAGE("change_page", true);

        public static final Map<String, Action> field_150679_e = Maps.newHashMap();

        public final boolean field_150676_f;

        public final String field_150677_g;

        Action(String p_i45155_3_, boolean p_i45155_4_) {
            this.field_150677_g = p_i45155_3_;
            this.field_150676_f = p_i45155_4_;
        }

        public boolean func_150674_a() {
            return this.field_150676_f;
        }

        public String func_150673_b() {
            return this.field_150677_g;
        }

        public static Action func_150672_a(String name) {
            return field_150679_e.get(name);
        }

        static {
            for (Action action : values()) {
                field_150679_e.put(action.func_150673_b(), action);
            }
        }
    }
}
