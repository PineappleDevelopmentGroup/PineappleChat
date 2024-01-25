package sh.miles.pineapple.chat.tag.base;

import org.jetbrains.annotations.NotNull;
import sh.miles.pineapple.chat.builder.TextBuilder;
import sh.miles.pineapple.chat.parse.PineappleParserContext;

import java.awt.Color;
import java.util.Map;
import java.util.Queue;

/**
 * A Tag for Colors
 */
public abstract class AbstractColorTag extends AbstractTag {

    protected AbstractColorTag(@NotNull final String namespace, @NotNull final Queue<String> arguments, final int childTextLength) {
        super(namespace, arguments, childTextLength);
    }

    @Override
    public <R> void apply(@NotNull final TextBuilder<R> builder, @NotNull final PineappleParserContext<R> context, final Map<String, Object> replacements) {
        builder.color(getColor());
    }

    /**
     * Gets the color
     *
     * @return the color
     */
    public abstract Color getColor();
}
