package sh.miles.pineapple.chat.utils;

import org.jetbrains.annotations.NotNull;
import sh.miles.pineapple.chat.builder.TextBuilder;
import sh.miles.pineapple.chat.parse.AbstractPineappleParserContext;
import sh.miles.pineapple.chat.parse.PineappleParserContext;
import sh.miles.pineapple.chat.tag.base.AbstractTag;

import java.util.Map;

/**
 * A stack that helps style a TextBuilder
 */
public interface StyleStack {

    /**
     * Pushes an unknown tag to the top of its respective stack
     *
     * @param tag the tag
     */
    void push(@NotNull final AbstractTag tag);

    /**
     * Pops the given unknown tag from its respective stack
     *
     * @param tag the tag
     */
    void pop(@NotNull final AbstractTag tag);

    /**
     * Applies the style stack to the given text
     *
     * @param text         the text to apply style to
     * @param builder      the builder
     * @param context      the parser context
     * @param replacements all replacements in text
     * @param <R>          the desired result type
     */
    <R> void apply(@NotNull final String text, @NotNull final TextBuilder<R> builder, @NotNull final PineappleParserContext<R> context, @NotNull final Map<String, Object> replacements);

    /**
     * Applies all "simple" tags again to the builder. Simple tags include tags that are not from the sourced
     * {@link sh.miles.pineapple.chat.tag.base.complex} package. An example of a Complex tag is the IteratingTag.
     *
     * @param builder      the builder
     * @param context      the parser context
     * @param replacements all replacements in text
     * @param <R>          the desired result type
     */
    <R> void applySimple(@NotNull final TextBuilder<R> builder, @NotNull final PineappleParserContext<R> context, @NotNull final Map<String, Object> replacements);

    /**
     * Dumps any lazily held tags into the build stream. Within the {@link AbstractPineappleParserContext} this method
     * is only called if no text node was parsed and their is lazily held data.
     *
     * @param builder      the builder to dump into
     * @param context      the parser context
     * @param replacements all replacements in text
     * @param <R>          the desired result type
     */
    <R> void dump(@NotNull final TextBuilder<R> builder, @NotNull final PineappleParserContext<R> context, @NotNull final Map<String, Object> replacements);
}
