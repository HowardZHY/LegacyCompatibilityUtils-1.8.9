package net.minecraft.inventory;

@SuppressWarnings("all")
public enum EntityEquipmentSlot {

    MAINHAND(Type.HAND, 0, 0, "mainhand"),
    OFFHAND(Type.HAND, 1, 0, "offhand"), //5
    FEET(Type.ARMOR, 0, 1, "feet"),
    LEGS(Type.ARMOR, 1, 2, "legs"),
    CHEST(Type.ARMOR, 2, 3, "chest"),
    HEAD(Type.ARMOR, 3, 4, "head");

    public EntityEquipmentSlot.Type field_188462_g;

    public int field_188463_h;

    public int field_188464_i;

    public String field_188465_j;

    EntityEquipmentSlot(Type slotType, int index, int slotIndex, String name) {
        this.field_188462_g = slotType;
        this.field_188463_h = index;
        this.field_188464_i = slotIndex;
        this.field_188465_j = name;
    }

    public EntityEquipmentSlot.Type func_188453_a() {
        return this.field_188462_g;
    }

    public int func_188454_b() {
        return this.field_188463_h;
    }

    public int func_188452_c() {
        return this.field_188464_i;
    }

    public String func_188450_d() {
        return this.field_188465_j;
    }

    /** fromString */
    public static EntityEquipmentSlot func_188451_a(String name) {
        for (EntityEquipmentSlot entityequipmentslot : values()) {
            if (entityequipmentslot.func_188450_d().equals(name)) {
                return entityequipmentslot;
            }
        }
        throw new IllegalArgumentException("Invalid slot '" + name + "'");
    }

    public static EntityEquipmentSlot fromArmorIndex(int id) {
        for (EntityEquipmentSlot entityequipmentslot : values()) {
            if (entityequipmentslot.func_188454_b() == (id)) {
                return entityequipmentslot;
            }
        }
        throw new IllegalArgumentException("Invalid slot '" + id + "'");
    }

    public static EntityEquipmentSlot fromArmorSlotIndex(int id) {
        for (EntityEquipmentSlot entityequipmentslot : values()) {
            if (entityequipmentslot.func_188452_c() == (id)) {
                return entityequipmentslot;
            }
        }
        throw new IllegalArgumentException("Invalid slot '" + id + "'");
    }

    public enum Type {
        HAND, ARMOR;
    }

}
