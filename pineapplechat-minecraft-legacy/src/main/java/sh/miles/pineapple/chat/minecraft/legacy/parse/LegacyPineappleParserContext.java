package sh.miles.pineapple.chat.minecraft.legacy.parse;

import sh.miles.pineapple.chat.builder.TextBuilder;
import sh.miles.pineapple.chat.minecraft.legacy.builder.LegacyTextBuilder;
import sh.miles.pineapple.chat.minecraft.legacy.util.LegacyStyleStack;
import sh.miles.pineapple.chat.parse.AbstractPineappleParserContext;
import sh.miles.pineapple.chat.utils.StyleStack;

public class LegacyPineappleParserContext extends AbstractPineappleParserContext<String> {

    private final boolean allowHex;
    public LegacyPineappleParserContext(boolean allowHex) {
        this.allowHex = allowHex;
    }

    @Override
    public TextBuilder<String> newTextBuilder() {
        return new LegacyTextBuilder(this.allowHex);
    }

    @Override
    public StyleStack newStyleStack() {
        return new LegacyStyleStack();
    }
}
