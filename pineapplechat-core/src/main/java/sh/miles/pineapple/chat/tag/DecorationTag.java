package sh.miles.pineapple.chat.tag;

import org.jetbrains.annotations.NotNull;
import sh.miles.pineapple.chat.tag.base.AbstractDecorationTag;
import sh.miles.pineapple.chat.utils.Decoration;

import java.util.Queue;

class DecorationTag extends AbstractDecorationTag {

    private final Decoration decoration;
    private final boolean flag;

    DecorationTag(@NotNull final String namespace, @NotNull final Queue<String> arguments, final int childTextLength) {
        super(namespace, arguments, childTextLength);
        this.decoration = Decoration.valueOf(arguments.poll().toUpperCase());
        this.flag = arguments.poll() == null;
    }

    @Override
    public Decoration getDecoration() {
        return this.decoration;
    }

    @Override
    public boolean getFlag() {
        return this.flag;
    }
}
