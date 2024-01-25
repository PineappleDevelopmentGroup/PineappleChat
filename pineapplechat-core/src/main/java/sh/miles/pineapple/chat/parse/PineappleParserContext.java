package sh.miles.pineapple.chat.parse;

import org.jetbrains.annotations.NotNull;
import sh.miles.pineapple.chat.builder.TextBuilder;
import sh.miles.pineapple.chat.utils.StyleStack;

import java.util.HashMap;
import java.util.Map;

/**
 * An abstracted parser which returns a piece of text
 *
 * @param <R> the type of text
 */
public interface PineappleParserContext<R> {

    /**
     * Parses the string into the desired result
     *
     * @param text the text
     * @return the resulting text
     */
    default R parse(@NotNull final String text) {
        return parse(text, new HashMap<>());
    }

    /**
     * Parses the string and it's replacements into the desired result
     *
     * @param text         the text
     * @param replacements the resulting text
     * @return the resulting text
     */
    R parse(@NotNull final String text, @NotNull final Map<String, Object> replacements);

    /**
     * Creates a new text builder for the desired result
     *
     * @return the newTextBuilder
     */
    TextBuilder<R> newTextBuilder();

    StyleStack newStyleStack();
}
