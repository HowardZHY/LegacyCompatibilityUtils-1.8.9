package net.minecraft.util.text;

import com.google.common.collect.Lists;
import net.minecraft.util.EnumChatFormatting;
import space.libs.util.MappedName;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@SuppressWarnings({"unused", "UnnecessaryUnicodeEscape"})
public enum TextFormatting {
    BLACK("BLACK", '0', 0, 0),
    DARK_BLUE("DARK_BLUE", '1', 1, 170),
    DARK_GREEN("DARK_GREEN", '2', 2, 43520),
    DARK_AQUA("DARK_AQUA", '3', 3, 43690),
    DARK_RED("DARK_RED", '4', 4, 11141120),
    DARK_PURPLE("DARK_PURPLE", '5', 5, 11141290),
    GOLD("GOLD", '6', 6, 16755200),
    GRAY("GRAY", '7', 7, 11184810),
    DARK_GRAY("DARK_GRAY", '8', 8, 5592405),
    BLUE("BLUE", '9', 9, 5592575),
    GREEN("GREEN", 'a', 10, 5635925),
    AQUA("AQUA", 'b', 11, 5636095),
    RED("RED", 'c', 12, 16733525),
    LIGHT_PURPLE("LIGHT_PURPLE", 'd', 13, 16733695),
    YELLOW("YELLOW", 'e', 14, 16777045),
    WHITE("WHITE", 'f', 15, 16777215),
    OBFUSCATED("OBFUSCATED", 'k', true),
    BOLD("BOLD", 'l', true),
    STRIKETHROUGH("STRIKETHROUGH", 'm', true),
    UNDERLINE("UNDERLINE", 'n', true),
    ITALIC("ITALIC", 'o', true),
    RESET("RESET", 'r', -1, null);

    @MappedName("NAME_MAPPING")
    public static final Map<String, TextFormatting> field_96331_x = Arrays.stream(values()).collect(Collectors.toMap((p_199746_0_) -> func_175745_c(p_199746_0_.name), (p_199747_0_) -> p_199747_0_));

    @MappedName("FORMATTING_CODE_PATTERN")
    public static final Pattern field_96330_y = Pattern.compile("(?i)\u00a7[0-9A-FK-OR]");

    private final String name;

    private final char formattingCode;

    private final boolean fancyStyling;

    private final String controlString;

    private final int colorIndex;

    private final Integer color;

    @MappedName("lowercaseAlpha")
    private static String func_175745_c(String string) {
        return string.toLowerCase(Locale.ROOT).replaceAll("[^a-z]", "");
    }

    TextFormatting(String formattingName, char formattingCodeIn, int index, Integer colorCode) {
        this(formattingName, formattingCodeIn, false, index, colorCode);
    }

    TextFormatting(String formattingName, char formattingCodeIn, boolean fancyStylingIn) {
        this(formattingName, formattingCodeIn, fancyStylingIn, -1, null);
    }

    TextFormatting(String formattingName, char formattingCodeIn, boolean fancyStylingIn, int index, Integer colorCode) {
        this.name = formattingName;
        this.formattingCode = formattingCodeIn;
        this.fancyStyling = fancyStylingIn;
        this.colorIndex = index;
        this.color = colorCode;
        this.controlString = "\u00a7" + formattingCodeIn;
    }

    @MappedName("getColorIndex")
    public int func_175746_b() {
        return this.colorIndex;
    }

    @MappedName("isFancyStyling")
    public boolean func_96301_b() {
        return this.fancyStyling;
    }

    @MappedName("isColor")
    public boolean func_96302_c() {
        return !this.fancyStyling && this != RESET;
    }

    @MappedName("getColor")
    public Integer func_211163_e() {
        return this.color;
    }

    @MappedName("getFriendlyName")
    public String func_96297_d() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    public String toString() {
        return this.controlString;
    }

    @MappedName("getTextWithoutFormattingCodes")
    public static String func_110646_a(String text) {
        return text == null ? null : field_96330_y.matcher(text).replaceAll("");
    }

    @MappedName("getValueByName ")
    public static TextFormatting func_96300_b(String friendlyName) {
        return friendlyName == null ? null : field_96331_x.get(func_175745_c(friendlyName));
    }

    @MappedName("fromColorIndex")
    public static TextFormatting func_175744_a(int index) {
        if (index < 0) {
            return RESET;
        } else {
            for (TextFormatting textformatting : values()) {
                if (textformatting.func_175746_b() == index) {
                    return textformatting;
                }
            }
            return null;
        }
    }

    @MappedName("fromFormattingCode")
    public static TextFormatting func_211165_a(char formattingCodeIn) {
        char c0 = Character.toString(formattingCodeIn).toLowerCase(Locale.ROOT).charAt(0);
        for (TextFormatting textformatting : values()) {
            if (textformatting.formattingCode == c0) {
                return textformatting;
            }
        }
        return null;
    }

    @MappedName("getValidValues")
    public static Collection<String> func_96296_a(boolean getColor, boolean getFancyStyling) {
        List<String> list = Lists.newArrayList();
        for (TextFormatting textformatting : values()) {
            if ((!textformatting.func_96302_c() || getColor) && (!textformatting.func_96301_b() || getFancyStyling)) {
                list.add(textformatting.func_96297_d());
            }
        }
        return list;
    }

    public static TextFormatting from(EnumChatFormatting format) {
        if (format != null) {
            int index = format.ordinal();
            if (index > 20) {
                return RESET;
            } else {
                return func_175744_a(index);
            }
        } else {
            return RED;
        }
    }

    public static EnumChatFormatting toOriginal(TextFormatting format) {
        if (format != null) {
            int index = format.ordinal();
            if (index > 20) {
                return EnumChatFormatting.RESET;
            } else {
                return EnumChatFormatting.func_175744_a(index);
            }
        } else {
            return EnumChatFormatting.RED;
        }
    }
}
