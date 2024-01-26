package sh.miles.pineapple.chat.tag;

import org.jetbrains.annotations.NotNull;
import sh.miles.pineapple.chat.tag.base.AbstractColorTag;
import sh.miles.pineapple.chat.utils.ColorUtils;

import java.awt.Color;
import java.util.Queue;

class NamedColorTag extends AbstractColorTag {

    protected final Color color;

    NamedColorTag(@NotNull final String namespace, @NotNull final Queue<String> arguments, final int childTextLength) {
        super(namespace, arguments, childTextLength);
        this.color = ColorUtils.getNamedColor(namespace);
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return "NamedColorTag(\"%s\")".formatted(this.namespace);
    }
}
