package sh.miles.pineapple.chat.minecraft.legacy.util;

import org.jetbrains.annotations.NotNull;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public final class LegacyFormatter {

    public static final char SPECIAL_CHAR = '§';
    public static final String OBFUSCATED = SPECIAL_CHAR + "k";
    public static final String BOLD = SPECIAL_CHAR + "l";
    public static final String STRIKETHROUGH = SPECIAL_CHAR + "m";
    public static final String UNDERLINE = SPECIAL_CHAR + "n";
    public static final String ITALIC = SPECIAL_CHAR + "o";
    public static final String RESET = SPECIAL_CHAR + "r";

    private static final Map<Integer, String> LEGACY_COLOR_MAPPER;

    static {
        LEGACY_COLOR_MAPPER = new HashMap<>();
        LEGACY_COLOR_MAPPER.put(-5636096, SPECIAL_CHAR + "4");
        LEGACY_COLOR_MAPPER.put(-43691, SPECIAL_CHAR + "c");
        LEGACY_COLOR_MAPPER.put(-22016, SPECIAL_CHAR + "e");
        LEGACY_COLOR_MAPPER.put(-171, SPECIAL_CHAR + "2");
        LEGACY_COLOR_MAPPER.put(-11141291, SPECIAL_CHAR + "a");
        LEGACY_COLOR_MAPPER.put(-11141121, SPECIAL_CHAR + "b");
        LEGACY_COLOR_MAPPER.put(-16733526, SPECIAL_CHAR + "3");
        LEGACY_COLOR_MAPPER.put(-16777046, SPECIAL_CHAR + "1");
        LEGACY_COLOR_MAPPER.put(-11184641, SPECIAL_CHAR + "9");
        LEGACY_COLOR_MAPPER.put(-43521, SPECIAL_CHAR + "d");
        LEGACY_COLOR_MAPPER.put(-5635926, SPECIAL_CHAR + "5");
        LEGACY_COLOR_MAPPER.put(-1, SPECIAL_CHAR + "f");
        LEGACY_COLOR_MAPPER.put(-5592406, SPECIAL_CHAR + "7");
        LEGACY_COLOR_MAPPER.put(-11184811, SPECIAL_CHAR + "8");
        LEGACY_COLOR_MAPPER.put(-16777216, SPECIAL_CHAR + "0");
    }

    /**
     * Gets a string legacy color code from a java.awt.Color
     *
     * @param color    the color to get
     * @param allowHex whether or not to allow hex
     * @return the color string
     */
    @NotNull
    public static String getLegacyColor(@NotNull final Color color, boolean allowHex) {
        String legacyNamedColor = LEGACY_COLOR_MAPPER.get(color.getRGB());
        if (legacyNamedColor == null) {
            if (allowHex) {
                return getLegacyHexColor(color);
            } else {
                return "";
            }
        }
        return legacyNamedColor;
    }

    @NotNull
    private static String getLegacyHexColor(@NotNull final Color color) {
        String hex = Integer.toHexString(color.getRGB());
        StringBuilder result = new StringBuilder();
        result.append(SPECIAL_CHAR).append("x");
        for (int i = 0; i < hex.length(); i++) {
            result.append(SPECIAL_CHAR).append(hex.charAt(i));
        }

        return result.toString();
    }

}
