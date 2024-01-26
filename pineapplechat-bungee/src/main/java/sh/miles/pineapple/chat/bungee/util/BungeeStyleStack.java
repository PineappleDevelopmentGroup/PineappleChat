package sh.miles.pineapple.chat.bungee.util;

import org.jetbrains.annotations.NotNull;
import sh.miles.pineapple.chat.builder.TextBuilder;
import sh.miles.pineapple.chat.bungee.tag.ClickEventTag;
import sh.miles.pineapple.chat.bungee.tag.HoverEventTag;
import sh.miles.pineapple.chat.parse.PineappleParserContext;
import sh.miles.pineapple.chat.tag.base.AbstractTag;
import sh.miles.pineapple.chat.utils.AbstractStyleStack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class BungeeStyleStack extends AbstractStyleStack {

    private final Deque<ClickEventTag> clicks = new ArrayDeque<>();
    private final Deque<HoverEventTag> hovers = new ArrayDeque<>();

    @Override
    public void push(@NotNull final AbstractTag tag) {
        super.push(tag);
        if (tag instanceof ClickEventTag cet) {
            clicks.push(cet);
        } else if (tag instanceof HoverEventTag het) {
            hovers.push(het);
        }
    }

    @Override
    public void pop(@NotNull final AbstractTag tag) {
        super.push(tag);
        if (tag instanceof ClickEventTag) {
            clicks.pop();
        } else if (tag instanceof HoverEventTag) {
            hovers.pop();
        }
    }

    @Override
    public <R> void applySimple(@NotNull final TextBuilder<R> builder, @NotNull final PineappleParserContext<R> context, @NotNull final Map<String, Object> replacements) {
        super.applySimple(builder, context, replacements);
        final ClickEventTag click = this.clicks.peek();
        if (click != null) {
            click.apply(builder, context, replacements);
        }
        final HoverEventTag hover = this.hovers.peek();
        if (hover != null) {
            hover.apply(builder, context, replacements);
        }
    }
}
