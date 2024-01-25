package sh.miles.pineapple.chat.bungee.util;

import org.jetbrains.annotations.NotNull;
import sh.miles.pineapple.chat.builder.TextBuilder;
import sh.miles.pineapple.chat.parse.PineappleParserContext;
import sh.miles.pineapple.chat.tag.base.AbstractColorTag;
import sh.miles.pineapple.chat.tag.base.AbstractDecorationTag;
import sh.miles.pineapple.chat.tag.base.AbstractTag;
import sh.miles.pineapple.chat.tag.base.complex.IteratingTag;
import sh.miles.pineapple.chat.tag.base.complex.LazyTag;
import sh.miles.pineapple.chat.utils.StyleStack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

public class BungeeStyleStack implements StyleStack {

    private final Deque<AbstractColorTag> colors = new ArrayDeque<>();
    private final Deque<AbstractDecorationTag> decorations = new ArrayDeque<>();
    private final Queue<AbstractTag> insertions = new ArrayDeque<>();

    @Override
    public void push(@NotNull final AbstractTag tag) {
        if (tag instanceof AbstractColorTag act) {
            colors.push(act);
        } else if (tag instanceof AbstractDecorationTag adt) {
            decorations.push(adt);
        } else if (tag instanceof LazyTag) {
            insertions.add(tag);
        }
    }

    @Override
    public void pop(@NotNull final AbstractTag tag) {
        if (tag instanceof AbstractColorTag) {
            colors.pop();
        } else if (tag instanceof AbstractDecorationTag) {
            decorations.pop();
        }
    }

    @Override
    public <R> void apply(@NotNull final String text, @NotNull final TextBuilder<R> builder, @NotNull final PineappleParserContext<R> context, @NotNull final Map<String, Object> replacements) {
        dump(builder, context, replacements);
        if (colors.peek() instanceof IteratingTag iterator) {
            for (int i = 0; i < text.length(); i++) {
                builder.append(String.valueOf(text.charAt(i)));
                applySimple(builder, context, replacements);
                iterator.next();
            }
        } else {
            builder.append(text);
            applySimple(builder, context, replacements);
        }
    }

    @Override
    public <R> void applySimple(@NotNull final TextBuilder<R> builder, @NotNull final PineappleParserContext<R> context, @NotNull final Map<String, Object> replacements) {
        AbstractColorTag peek = colors.peek();
        if (peek != null) {
            peek.apply(builder, context, replacements);
        }
        decorations.descendingIterator().forEachRemaining(decoration -> decoration.apply(builder, context, replacements));
    }

    @Override
    public <R> void dump(@NotNull final TextBuilder<R> builder, @NotNull final PineappleParserContext<R> context, @NotNull final Map<String, Object> replacements) {
        final Iterator<AbstractTag> insertions = this.insertions.iterator();
        while(insertions.hasNext()) {
            insertions.next().apply(builder, context, replacements);
            insertions.remove();
            applySimple(builder, context, replacements);
        }
    }
}
