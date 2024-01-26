package sh.miles.pineapple.chat.minecraft.legacy.builder;

import org.junit.jupiter.api.Test;
import sh.miles.pineapple.chat.utils.ColorUtils;

import static org.junit.jupiter.api.Assertions.*;

public class LegacyTextBuilderTest {

    @Test
    public void test_Should_Get_Correct_Colored_Output() {
        String result = new LegacyTextBuilder(true).append("Hello World!")
                .color(ColorUtils.getColor("dark_red"))
                .bold(true)
                .italic(true)
                .build();
        assertEquals("§4§l§oHello World!", result);
    }

}
