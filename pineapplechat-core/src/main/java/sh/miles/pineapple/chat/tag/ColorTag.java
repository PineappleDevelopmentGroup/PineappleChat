package sh.miles.pineapple.chat.tag;

import org.jetbrains.annotations.NotNull;
import sh.miles.pineapple.chat.tag.base.AbstractColorTag;
import sh.miles.pineapple.chat.utils.ColorUtils;

import java.awt.Color;
import java.util.Queue;

class ColorTag extends AbstractColorTag {

    protected final Color color;

    ColorTag(@NotNull final String namespace, final @NotNull Queue<String> arguments, final int childTextLength) {
        super(namespace, arguments, childTextLength);
        this.color = ColorUtils.getColor(arguments.poll());
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return "ColorTag(\"%s\")".formatted("#" + Integer.toHexString(this.color.getRGB()).substring(2));
    }
}
