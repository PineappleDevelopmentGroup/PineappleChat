package sh.miles.pineapple.chat.parse;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sh.miles.pineapple.chat.builder.TextBuilder;
import sh.miles.pineapple.chat.node.BaseNode;
import sh.miles.pineapple.chat.node.BaseNodeParser;
import sh.miles.pineapple.chat.node.RootNode;
import sh.miles.pineapple.chat.node.TagNode;
import sh.miles.pineapple.chat.node.TextNode;
import sh.miles.pineapple.chat.tag.TagBuilder;
import sh.miles.pineapple.chat.tag.base.AbstractTag;
import sh.miles.pineapple.chat.utils.StyleStack;

import java.util.Map;

/**
 * An abstract implementation of PineappleParserContext which fulfills many standardized needs for parsing
 *
 * @param <R> the result text type
 */
public abstract class AbstractPineappleParserContext<R> implements PineappleParserContext<R> {

    @Override
    public R parse(@NotNull final String text, @NotNull final Map<String, Object> replacements) {
        final RootNode root = BaseNodeParser.parseTree(text, replacements);
        final TextBuilder<R> builder = newTextBuilder();
        parse(root, builder, this, replacements);
        return builder.build();
    }

    private void parse(@NotNull final RootNode root, @NotNull final TextBuilder<R> builder, PineappleParserContext<R> context, @NotNull final Map<String, Object> replacements) {
        StyleStack styleStack = newStyleStack();
        parse(root, null, builder, context, replacements, styleStack);
        styleStack.dump(builder, context, replacements);
    }

    private void parse(@NotNull final BaseNode root, @Nullable AbstractTag rootTag, @NotNull final TextBuilder<R> builder, PineappleParserContext<R> context, @NotNull final Map<String, Object> replacements, StyleStack styleStack) {
        for (final BaseNode child : root.getChildren()) {
            AbstractTag tag = null;
            if (child instanceof TagNode tagNode) {
                styleStack.push((tag = TagBuilder.build(tagNode)));
            }

            if (child instanceof TextNode textNode) {
                styleStack.apply(textNode.getText(), builder, context, replacements);
            }

            parse(child, tag, builder, context, replacements, styleStack);
        }

        if (rootTag != null) {
            styleStack.pop(rootTag);
        }
    }
}
