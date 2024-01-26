package sh.miles.pineapple.chat.bungee.tag;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.ItemTag;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Entity;
import net.md_5.bungee.api.chat.hover.content.Item;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.jetbrains.annotations.NotNull;
import sh.miles.pineapple.chat.builder.TextBuilder;
import sh.miles.pineapple.chat.bungee.builder.BungeeComponentBuilder;
import sh.miles.pineapple.chat.bungee.builder.BungeeTextBuilder;
import sh.miles.pineapple.chat.parse.PineappleParserContext;
import sh.miles.pineapple.chat.tag.base.AbstractTag;

import java.util.Map;
import java.util.Queue;

public class HoverEventTag extends AbstractTag {

    private final HoverEvent.Action action;
    private Content parsedContent = null;

    public HoverEventTag(@NotNull final String namespace, @NotNull final Queue<String> arguments, final int childTextLength) {
        super(namespace, arguments, childTextLength);
        this.action = HoverEvent.Action.valueOf(arguments.poll().toUpperCase());
    }

    @Override
    public <R> void apply(@NotNull final TextBuilder<R> builder, @NotNull final PineappleParserContext<R> context, final Map<String, Object> replacements) {
        if (this.parsedContent == null) {
            switch (action) {
                case SHOW_TEXT ->
                        this.parsedContent = new Text(BungeeComponentBuilder.array((BaseComponent) context.parse(dequote(arguments.poll()), replacements)));
                case SHOW_ITEM ->
                        this.parsedContent = new Item(dequote(arguments.poll()), Integer.parseInt(arguments.poll()), ItemTag.ofNbt(dequote(arguments.poll())));
                case SHOW_ENTITY -> {
                    final String id = dequote(arguments.poll());
                    final String uuid = dequote(arguments.poll());
                    String stringyName = arguments.poll();
                    final BaseComponent name = stringyName != null ? (BaseComponent) context.parse(dequote(stringyName), replacements) : null;
                    this.parsedContent = new Entity(id, uuid, name);
                }
            }
        }

        if (builder instanceof BungeeTextBuilder btbuilder) {
            btbuilder.hover(this.action, parsedContent);
        }
    }

    private static String dequote(String argument) {
        return argument.substring(1, argument.length() - 1);
    }
}
