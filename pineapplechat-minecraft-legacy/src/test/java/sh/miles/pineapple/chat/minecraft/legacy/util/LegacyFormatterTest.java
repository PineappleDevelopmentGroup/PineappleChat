package sh.miles.pineapple.chat.minecraft.legacy.util;

import org.junit.jupiter.api.Test;
import sh.miles.pineapple.chat.utils.ColorUtils;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

public final class LegacyFormatterTest {

    @Test
    public void test_Colors_Equals() {
        assertEquals(LegacyFormatter.SPECIAL_CHAR + "4", LegacyFormatter.getLegacyColor(ColorUtils.getColor("dark_red"), true));
    }

    public static void main(String[] args) {
        ColorUtils.getNamedColors().forEach((k, v) -> System.out.printf("%s %s%n", k, v.getRGB()));
    }

}
