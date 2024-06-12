package net.minecraft.util.text;

public enum ChatType {

    CHAT((byte)0),

    SYSTEM((byte)1),

    GAME_INFO((byte)2);

    public byte id;

    ChatType(byte p_i47429_3_) {
        this.id = p_i47429_3_;
    }

    public byte getId() {
        return this.id;
    }

    public static ChatType byId(byte b) {
        for (ChatType ct : values()) {
            if (b == ct.id)
                return ct;
        }
        return CHAT;
    }
}
