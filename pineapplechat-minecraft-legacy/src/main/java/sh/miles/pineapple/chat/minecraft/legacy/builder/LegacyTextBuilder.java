package sh.miles.pineapple.chat.minecraft.legacy.builder;

import org.jetbrains.annotations.NotNull;
import sh.miles.pineapple.chat.builder.TextBuilder;
import sh.miles.pineapple.chat.minecraft.legacy.util.LegacyFormatter;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class LegacyTextBuilder implements TextBuilder<String> {

    private final boolean allowHex;
    private final List<StringInsertionManager> parts;
    private int cursor;

    /**
     * Creates a new LegacyTextBuilder
     *
     * @param allowHex whether or not to allow hexadecimals
     */
    public LegacyTextBuilder(boolean allowHex) {
        this.allowHex = allowHex;
        this.parts = new ArrayList<>();
        this.cursor = -1;
    }

    @Override
    public TextBuilder<String> append(final String text) {
        parts.add(new StringInsertionManager());
        cursor++;
        getBuilder().append(text);
        return this;
    }

    @Override
    public TextBuilder<String> color(@NotNull final Color color) {
        getBuilder().insert(LegacyFormatter.getLegacyColor(color, this.allowHex));
        return this;
    }

    @Override
    public TextBuilder<String> font(@NotNull final String fontKey) {
        return this;
    }

    @Override
    public TextBuilder<String> bold(final boolean bold) {
        getBuilder().insert(LegacyFormatter.BOLD);
        return this;
    }

    @Override
    public TextBuilder<String> italic(final boolean italic) {
        getBuilder().insert(LegacyFormatter.ITALIC);
        return this;
    }

    @Override
    public TextBuilder<String> underline(final boolean underline) {
        getBuilder().insert(LegacyFormatter.UNDERLINE);
        return this;
    }

    @Override
    public TextBuilder<String> strikethrough(final boolean strikethrough) {
        getBuilder().insert(LegacyFormatter.STRIKETHROUGH);
        return this;
    }

    @Override
    public TextBuilder<String> obfuscated(final boolean obfuscated) {
        getBuilder().insert(LegacyFormatter.OBFUSCATED);
        return this;
    }

    @Override
    public TextBuilder<String> insert(final String text) {
        getBuilder().append(text);
        return this;
    }

    @Override
    public TextBuilder<String> translation(final String key) {
        throw thrower("translation");
    }

    @Override
    public TextBuilder<String> keybind(final String key) {
        throw thrower("keybind");
    }

    @Override
    public TextBuilder<String> score(final String scoreboardName, final String objective) {
        throw thrower("score");
    }

    @Override
    public TextBuilder<String> selector(final String selector) {
        throw thrower("selector");
    }

    @Override
    public TextBuilder<String> reset() {
        getBuilder().insert(LegacyFormatter.RESET);
        return this;
    }

    @Override
    public String getCurrent() {
        return this.parts.get(this.cursor).toString();
    }

    private StringInsertionManager getBuilder() {
        return this.parts.get(this.cursor);
    }

    @Override
    public List<String> getParts() {
        return new ArrayList<>();
    }

    @Override
    public String build() {
        StringBuilder master = new StringBuilder();
        parts.forEach(master::append);
        return master.toString();
    }

    private UnsupportedOperationException thrower(@NotNull final String feature) {
        return new UnsupportedOperationException("Can not parse %s when using the Minecraft Legacy parser");
    }

    private static class StringInsertionManager {
        private final StringBuilder builder = new StringBuilder();
        private int offset = 0;

        public void append(@NotNull final String text) {
            builder.append(text);
        }

        public void insert(@NotNull final String text) {
            builder.insert(this.offset, text);
            this.offset += text.length();
        }

        @Override
        public String toString() {
            return builder.toString();
        }
    }
}
