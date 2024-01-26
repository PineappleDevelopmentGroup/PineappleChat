package sh.miles.pineapple.chat.bungee.tag;

import net.md_5.bungee.api.chat.ClickEvent;
import org.jetbrains.annotations.NotNull;
import sh.miles.pineapple.chat.builder.TextBuilder;
import sh.miles.pineapple.chat.bungee.builder.BungeeTextBuilder;
import sh.miles.pineapple.chat.parse.PineappleParserContext;
import sh.miles.pineapple.chat.tag.base.AbstractTag;

import java.util.Map;
import java.util.Queue;

public class ClickEventTag extends AbstractTag {

    private final ClickEvent.Action action;
    private final String execution;

    /**
     * Creates a new ClickEventTag
     *
     * @param namespace the namespace
     * @param arguments the arguments
     * @param childTextLength the child text length
     */
    public ClickEventTag(@NotNull final String namespace, @NotNull final Queue<String> arguments, final int childTextLength) {
        super(namespace, arguments, childTextLength);
        this.action = ClickEvent.Action.valueOf(arguments.poll().toUpperCase());
        this.execution = dequote(arguments.poll());
    }

    @Override
    public <R> void apply(@NotNull final TextBuilder<R> builder, @NotNull final PineappleParserContext<R> context, final Map<String, Object> replacements) {
        if (builder instanceof BungeeTextBuilder btbuilder) {
            btbuilder.click(this.action, this.execution);
        }
    }

    private static String dequote(String argument) {
        return argument.substring(1, argument.length() - 1);
    }
}
