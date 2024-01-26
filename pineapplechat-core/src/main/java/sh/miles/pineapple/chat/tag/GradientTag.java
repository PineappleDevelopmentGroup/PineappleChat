package sh.miles.pineapple.chat.tag;

import org.jetbrains.annotations.NotNull;
import sh.miles.pineapple.chat.tag.base.AbstractColorTag;
import sh.miles.pineapple.chat.tag.base.complex.IteratingTag;
import sh.miles.pineapple.chat.utils.ColorUtils;

import java.awt.Color;
import java.util.Queue;

class GradientTag extends AbstractColorTag implements IteratingTag {

    private final Color[] colors;
    private int index;

    GradientTag(@NotNull final String namespace, @NotNull final Queue<String> arguments, final int childTextLength) {
        super(namespace, arguments, childTextLength);
        this.colors = ColorUtils.createLinearGradient(ColorUtils.getColor(arguments.poll()), ColorUtils.getColor(arguments.poll()), childTextLength);
    }

    @Override
    public Color getColor() {
        return colors[this.index];
    }

    @Override
    public void next() {
        if (index >= colors.length) {
            throw new IllegalStateException("Unable to apply more colors, this gradient has reached its end");
        }

        index++;
    }

    @Override
    public String toString() {
        return "GradientTag(\"%s\", \"%s\")".formatted("#" + Integer.toHexString(colors[0].getRGB()).substring(2), "#" + Integer.toHexString(colors[colors.length - 1].getRGB()).substring(2));
    }
}
