package sh.miles.pineapple.chat.builder;

import org.jetbrains.annotations.NotNull;

import java.awt.Color;
import java.util.List;

/**
 * Builds text and returns a specific TextType whether it be strings or components
 *
 * @param <R> the type of text to be return
 */
public interface TextBuilder<R> {

    /**
     * Appends a type of text
     *
     * @param text the text to append
     * @return the TextBuilder
     */
    TextBuilder<R> append(R text);

    /**
     * Appends a string
     *
     * @param text the string to append
     * @return the TextBuilder
     */
    TextBuilder<R> append(String text);

    /**
     * Colors a section of this TextBuilder
     *
     * @param color the color to use
     * @return the TextBuilder
     */
    TextBuilder<R> color(@NotNull final Color color);

    /**
     * Applies a font to a section of this TextBuilder
     *
     * @param fontKey the fontKey in the namespaced key format e.g. "minecraft:random_font_example"
     * @return the TextBuilder
     */
    TextBuilder<R> font(@NotNull final String fontKey);

    /**
     * Applies bold to this text
     *
     * @param bold whether to apply bold
     * @return the TextBuilder
     */
    TextBuilder<R> bold(boolean bold);

    /**
     * Applies italic to this text
     *
     * @param italic whether to apply italic
     * @return the TextBuilder
     */
    TextBuilder<R> italic(boolean italic);

    /**
     * Applies underline to this text
     *
     * @param underline whether to apply underline
     * @return the TextBuilder
     */
    TextBuilder<R> underline(boolean underline);

    /**
     * Applies strikethrough to this text
     *
     * @param strikethrough whether to apply strikethrough
     * @return the TextBuilder
     */
    TextBuilder<R> strikethrough(boolean strikethrough);

    /**
     * Applies obfuscated to this text
     *
     * @param obfuscated whether to apply obfuscated
     * @return the TextBuilder
     */
    TextBuilder<R> obfuscated(boolean obfuscated);

    /**
     * Inserts a string into the currently selected part
     *
     * @param text the string text to insert
     * @return the TextBuilder
     */
    TextBuilder<R> insert(String text);

    /**
     * Appends a translation into the next part
     *
     * @param key the key
     * @return the TextBuilder
     */
    TextBuilder<R> translation(String key);

    /**
     * Appends a keybind into the next part
     *
     * @param key the key of the keybind
     * @return the TextBuilder
     */
    TextBuilder<R> keybind(String key);

    /**
     * Appends a score into the next part
     *
     * @param scoreboardName the scoreboard name
     * @param objective      the name of the objective to insert
     * @return the TextBuilder
     */
    TextBuilder<R> score(String scoreboardName, String objective);

    /**
     * Appends a selector into the next part
     *
     * @param selector the selector
     * @return the TextBuilder
     */
    TextBuilder<R> selector(String selector);

    /**
     * Resets all formatting
     *
     * @return the TextBuilder
     */
    TextBuilder<R> reset();

    /**
     * Gets the current text part
     *
     * @return the current text part
     */
    R getCurrent();

    /**
     * Gets all text parts
     *
     * @return a list of all text parts
     */
    List<R> getParts();

    /**
     * Builds all text parts into one piece of text
     *
     * @return the text
     */
    R build();
}
