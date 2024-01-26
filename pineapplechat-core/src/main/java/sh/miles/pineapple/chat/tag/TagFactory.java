package sh.miles.pineapple.chat.tag;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sh.miles.pineapple.chat.node.TagNode;
import sh.miles.pineapple.chat.tag.base.AbstractTag;
import sh.miles.pineapple.chat.utils.Trie;

import java.util.Objects;
import java.util.Queue;

/**
 * Allows for creation and registration of tags.
 */
public final class TagFactory {

    private final Trie<AbstractTagBuilder> tagTrie;

    public TagFactory() {
        final AbstractTagBuilder NAMED_COLOR = NamedColorTag::new;
        final AbstractTagBuilder NAMED_DECOR = NamedDecorationTag::new;

        this.tagTrie = new Trie<>();
        tagTrie.insert("color", ColorTag::new);
        tagTrie.insert("decor", DecorationTag::new);
        tagTrie.insert("gradient", GradientTag::new);
        tagTrie.insert("keybind", KeybindTag::new);
        tagTrie.insert("lang", LanguageTag::new);
        tagTrie.insert("score", ScoreTag::new);
        tagTrie.insert("selector", SelectorTag::new);

        // NamedDecorations
        tagTrie.insert("bold", NAMED_DECOR);
        tagTrie.insert("italic", NAMED_DECOR);
        tagTrie.insert("obfuscated", NAMED_DECOR);
        tagTrie.insert("reset", NAMED_DECOR);
        tagTrie.insert("strike_through", NAMED_DECOR);
        tagTrie.insert("underlined", NAMED_DECOR);

        // NamedColorTags
        tagTrie.insert("aqua", NAMED_COLOR);
        tagTrie.insert("red", NAMED_COLOR);
        tagTrie.insert("black", NAMED_COLOR);
        tagTrie.insert("blue", NAMED_COLOR);
        tagTrie.insert("dark_aqua", NAMED_COLOR);
        tagTrie.insert("dark_blue", NAMED_COLOR);
        tagTrie.insert("dark_gray", NAMED_COLOR);
        tagTrie.insert("dark_green", NAMED_COLOR);
        tagTrie.insert("dark_red", NAMED_COLOR);
        tagTrie.insert("dark_purple", NAMED_COLOR);
        tagTrie.insert("gray", NAMED_COLOR);
        tagTrie.insert("gold", NAMED_COLOR);
        tagTrie.insert("green", NAMED_COLOR);
        tagTrie.insert("light_purple", NAMED_COLOR);
        tagTrie.insert("red", NAMED_COLOR);
        tagTrie.insert("white", NAMED_COLOR);
        tagTrie.insert("yellow", NAMED_COLOR);
    }

    /**
     * Creates a new Tag from the given TagNode
     *
     * @param tagNode the tagNode to build into an AbstractTag
     * @return the AbstractTag created or null
     */
    @Nullable
    public AbstractTag create(@NotNull final TagNode tagNode) {
        final AbstractTagBuilder builder = tagTrie.contains(tagNode.getNamespace());
        return builder == null ? null : builder.create(tagNode.getNamespace(), tagNode.getArguments(), tagNode.getFullChildTextLength());
    }

    /**
     * Registers a new TagBuilder into the TagFactory
     *
     * @param string  the string name of the tag
     * @param builder the tag builder
     * @return true if registered, otherwise false
     */
    public boolean register(@NotNull final String string, @NotNull final AbstractTagBuilder builder) {
        Objects.requireNonNull(string);
        Objects.requireNonNull(builder);
        if (tagTrie.contains(string) == null) {
            tagTrie.insert(string, builder);
            return true;
        }
        return false;
    }

    public interface AbstractTagBuilder {
        AbstractTag create(@NotNull final String namespace, @NotNull final Queue<String> arguments, int childStringLength);
    }

}
