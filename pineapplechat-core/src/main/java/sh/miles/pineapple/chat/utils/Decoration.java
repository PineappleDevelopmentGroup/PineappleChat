package sh.miles.pineapple.chat.utils;

import org.jetbrains.annotations.NotNull;
import sh.miles.pineapple.chat.builder.TextBuilder;

public enum Decoration {
    OBFUSCATED {
        @Override
        public void apply(final @NotNull TextBuilder<?> builder, boolean flag) {
            builder.obfuscated(flag);
        }
    },
    BOLD {
        @Override
        public void apply(@NotNull final TextBuilder<?> builder, final boolean flag) {
            builder.bold(flag);
        }
    },
    STRIKETHROUGH {
        @Override
        public void apply(@NotNull final TextBuilder<?> builder, final boolean flag) {
            builder.strikethrough(flag);
        }
    },
    UNDERLINED {
        @Override
        public void apply(@NotNull final TextBuilder<?> builder, final boolean flag) {
            builder.underline(flag);
        }
    },
    ITALIC {
        @Override
        public void apply(@NotNull final TextBuilder<?> builder, final boolean flag) {
            builder.italic(flag);
        }
    },
    RESET {
        @Override
        public void apply(@NotNull final TextBuilder<?> builder, final boolean flag) {
            builder.reset();
        }
    };

    public abstract void apply(@NotNull final TextBuilder<?> builder, boolean flag);
}
