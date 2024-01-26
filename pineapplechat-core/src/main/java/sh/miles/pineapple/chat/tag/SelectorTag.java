package sh.miles.pineapple.chat.tag;

import org.jetbrains.annotations.NotNull;
import sh.miles.pineapple.chat.builder.TextBuilder;
import sh.miles.pineapple.chat.parse.PineappleParserContext;
import sh.miles.pineapple.chat.tag.base.AbstractTag;
import sh.miles.pineapple.chat.tag.base.complex.LazyTag;

import java.util.Map;
import java.util.Queue;

public class SelectorTag extends AbstractTag implements LazyTag {

    private final String selector;

    protected SelectorTag(@NotNull final String namespace, @NotNull final Queue<String> arguments, final int childTextLength) {
        super(namespace, arguments, childTextLength);
        this.selector = arguments.poll();
    }

    @Override
    public <R> void apply(@NotNull final TextBuilder<R> builder, @NotNull final PineappleParserContext<R> context, final Map<String, Object> replacements) {
        builder.selector(this.selector);
    }

    @Override
    public String toString() {
        return "SelectorTag(\"%s\")".formatted(this.selector);
    }
}
