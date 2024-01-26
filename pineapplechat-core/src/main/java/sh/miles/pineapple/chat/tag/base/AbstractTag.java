package sh.miles.pineapple.chat.tag.base;

import org.jetbrains.annotations.NotNull;
import sh.miles.pineapple.chat.builder.TextBuilder;
import sh.miles.pineapple.chat.parse.PineappleParserContext;

import java.util.Map;
import java.util.Queue;

/**
 * An Abstract tag that can be implemented to create new custom tags
 */
public abstract class AbstractTag {

    protected final String namespace;
    protected final Queue<String> arguments;
    protected final int childTextLength;

    /**
     * Creates a new abstract tag
     *
     * @param namespace the namespace
     * @param arguments the arguments
     * @param childTextLength the child text length
     */
    protected AbstractTag(@NotNull final String namespace, @NotNull final Queue<String> arguments, final int childTextLength) {
        this.namespace = namespace;
        this.arguments = arguments;
        this.childTextLength = childTextLength;
    }

    /**
     * Applies the tag to the builder
     *
     * @param <R>          the type of TextBuilder and context
     * @param builder      the builder
     * @param context      the parsing context
     * @param replacements the replacements
     */
    public abstract <R> void apply(@NotNull final TextBuilder<R> builder, @NotNull final PineappleParserContext<R> context, final Map<String, Object> replacements);

    public int getChildTextLength() {
        return this.childTextLength;
    }
}
