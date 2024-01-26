package sh.miles.pineapple.chat.tag;

import org.jetbrains.annotations.NotNull;
import sh.miles.pineapple.chat.builder.TextBuilder;
import sh.miles.pineapple.chat.parse.PineappleParserContext;
import sh.miles.pineapple.chat.tag.base.AbstractTag;
import sh.miles.pineapple.chat.tag.base.complex.LazyTag;

import java.util.Map;
import java.util.Queue;

class KeybindTag extends AbstractTag implements LazyTag {

    private final String key;

    KeybindTag(@NotNull final String namespace, @NotNull final Queue<String> arguments, final int childTextLength) {
        super(namespace, arguments, childTextLength);
        this.key = arguments.poll();
    }

    @Override
    public <R> void apply(@NotNull final TextBuilder<R> builder, @NotNull final PineappleParserContext<R> context, final Map<String, Object> replacements) {
        builder.keybind(this.key);
    }

    @Override
    public String toString() {
        return "KeybindTag(\"%s\")".formatted(this.key);
    }
}

