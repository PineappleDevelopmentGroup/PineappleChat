package sh.miles.pineapple.chat.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * Named color's that can be translated to and from java.awt.color from strings
 */
public final class ColorUtils {

    private static final Map<String, Color> colors;

    static {
        final Map<String, Color> temp = new HashMap<>();
        temp.put("black", Color.decode("#000000"));
        temp.put("dark_blue", Color.decode("#0000AA"));
        temp.put("dark_green", Color.decode("#00AA00"));
        temp.put("dark_aqua", Color.decode("#00AAAA"));
        temp.put("dark_red", Color.decode("#AA0000"));
        temp.put("dark_purple", Color.decode("#AA00AA"));
        temp.put("gold", Color.decode("#FFAA00"));
        temp.put("gray", Color.decode("#AAAAAA"));
        temp.put("dark_gray", Color.decode("#555555"));
        temp.put("blue", Color.decode("#5555FF"));
        temp.put("green", Color.decode("#55FF55"));
        temp.put("aqua", Color.decode("#55FFFF"));
        temp.put("red", Color.decode("#FF5555"));
        temp.put("light_purple", Color.decode("#FF55FF"));
        temp.put("yellow", Color.decode("#FFFF55"));
        temp.put("white", Color.decode("#FFFFFF"));
        colors = Map.copyOf(temp);
    }

    @Nullable
    public static Color getNamedColor(@NotNull final String color) {
        return colors.get(color.toLowerCase());
    }

    @Nullable
    public static Color getColor(@NotNull final String color) {
        Color awtColor = colors.get(color.toLowerCase());
        return awtColor == null ? Color.decode(color) : awtColor;
    }

    /**
     * Creates a linear gradient using a start and end color with the specified step
     *
     * @param start the start color
     * @param end   the end color
     * @param step  the amount of steps
     * @return the color array
     * @since 1.0.0-SNAPSHOT
     */
    public static Color[] createLinearGradient(@NotNull final Color start, @NotNull final Color end, final int step) {
        Color[] colors = new Color[step];
        int stepR = Math.abs(start.getRed() - end.getRed()) / (step - 1);
        int stepG = Math.abs(start.getGreen() - end.getGreen()) / (step - 1);
        int stepB = Math.abs(start.getBlue() - end.getBlue()) / (step - 1);
        int[] direction = new int[]{
                start.getRed() < end.getRed() ? +1 : -1,
                start.getGreen() < end.getGreen() ? +1 : -1,
                start.getBlue() < end.getBlue() ? +1 : -1
        };

        for (int i = 0; i < step; i++) {
            colors[i] = new Color(start.getRed() + ((stepR * i) * direction[0]), start.getGreen() + ((stepG * i) * direction[1]), start.getBlue() + ((stepB * i) * direction[2]));
        }

        return colors;
    }

}
