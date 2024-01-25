package sh.miles.pineapple.chat.bungee.builder;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;
import org.jetbrains.annotations.NotNull;
import sh.miles.pineapple.chat.builder.TextBuilder;

import java.awt.Color;
import java.util.List;

public class BungeeTextBuilder implements TextBuilder<BaseComponent> {

    private final BungeeComponentBuilder builder;

    public BungeeTextBuilder() {
        this.builder = BungeeComponentBuilder.empty();
    }

    @Override
    public TextBuilder<BaseComponent> append(final BaseComponent text) {
        builder.append(text, BungeeComponentBuilder.FormatRetention.NONE);
        return this;
    }

    @Override
    public TextBuilder<BaseComponent> append(final String text) {
        builder.append(text, BungeeComponentBuilder.FormatRetention.NONE);
        return this;
    }

    @Override
    public TextBuilder<BaseComponent> color(@NotNull final Color color) {
        builder.color(ChatColor.of(color));
        return this;
    }

    @Override
    public TextBuilder<BaseComponent> font(@NotNull final String fontKey) {
        builder.font(fontKey);
        return this;
    }

    @Override
    public TextBuilder<BaseComponent> bold(final boolean bold) {
        builder.bold(bold);
        return this;
    }

    @Override
    public TextBuilder<BaseComponent> italic(final boolean italic) {
        builder.italic(italic);
        return this;
    }

    @Override
    public TextBuilder<BaseComponent> underline(final boolean underline) {
        builder.underlined(underline);
        return this;
    }

    @Override
    public TextBuilder<BaseComponent> strikethrough(final boolean strikethrough) {
        builder.strikethrough(strikethrough);
        return this;
    }

    @Override
    public TextBuilder<BaseComponent> obfuscated(final boolean obfuscated) {
        builder.obfuscated(obfuscated);
        return this;
    }

    @Override
    public TextBuilder<BaseComponent> insert(final String text) {
        builder.insertion(text);
        return this;
    }

    @Override
    public TextBuilder<BaseComponent> translation(final String key) {
        builder.append(new TranslatableComponent(key), BungeeComponentBuilder.FormatRetention.NONE);
        return this;
    }

    @Override
    public TextBuilder<BaseComponent> reset() {
        builder.reset();
        return this;
    }

    @Override
    public BaseComponent getCurrent() {
        return builder.getCurrentComponent();
    }

    @Override
    public List<BaseComponent> getParts() {
        return builder.getParts();
    }

    @Override
    public BaseComponent build() {
        return builder.build();
    }
}
