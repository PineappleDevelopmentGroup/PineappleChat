package sh.miles.pineapple.chat.bungee.parse;

import net.md_5.bungee.api.chat.BaseComponent;
import sh.miles.pineapple.chat.builder.TextBuilder;
import sh.miles.pineapple.chat.bungee.builder.BungeeTextBuilder;
import sh.miles.pineapple.chat.bungee.util.BungeeStyleStack;
import sh.miles.pineapple.chat.parse.AbstractPineappleParserContext;
import sh.miles.pineapple.chat.utils.StyleStack;

public class BungeePineappleParserContext extends AbstractPineappleParserContext<BaseComponent> {

    @Override
    public TextBuilder<BaseComponent> newTextBuilder() {
        return new BungeeTextBuilder();
    }

    @Override
    public StyleStack newStyleStack() {
        return new BungeeStyleStack();
    }
}
