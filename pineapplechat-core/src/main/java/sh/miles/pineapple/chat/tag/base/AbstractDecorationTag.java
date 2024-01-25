package sh.miles.pineapple.chat.tag.base;

import org.jetbrains.annotations.NotNull;
import sh.miles.pineapple.chat.builder.TextBuilder;
import sh.miles.pineapple.chat.parse.PineappleParserContext;
import sh.miles.pineapple.chat.utils.Decoration;

import java.util.Map;
import java.util.Queue;


/**
 * A Tag for Decorations
 */
public abstract class AbstractDecorationTag extends AbstractTag {

    protected AbstractDecorationTag(@NotNull final String namespace, @NotNull final Queue<String> arguments, final int childTextLength) {
        super(namespace, arguments, childTextLength);
    }

    @Override
    public <R> void apply(@NotNull final TextBuilder<R> builder, @NotNull final PineappleParserContext<R> context, final Map<String, Object> replacements) {
        getDecoration().apply(builder, getFlag());
    }

    /**
     * Gets the decoration on this tag
     *
     * @return the decoration
     */
    public abstract Decoration getDecoration();

    /**
     * Gets the flag
     *
     * @return the flag
     */
    public abstract boolean getFlag();
}
