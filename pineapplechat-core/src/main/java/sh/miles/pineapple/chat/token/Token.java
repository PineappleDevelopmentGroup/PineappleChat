package sh.miles.pineapple.chat.token;

import org.jetbrains.annotations.NotNull;

/**
 * Creates a new Token class
 * <p>
 * A token represents a section of a greater piece of text that can be extracted and used to denote some set of
 * information
 *
 * @param start     the start index of the token
 * @param end       the end index of the token
 * @param tokenType the type of token
 */
public record Token(int start, int end, @NotNull TokenType tokenType) {

    /**
     * The inner detail of a token minus the opening and closing characters.
     * <p>
     * For example this method turns {@literal "<example-tag>"} into "example-tag"
     *
     * @param source the token source
     * @return the detail
     */
    @NotNull
    public String detail(String source) {
        return source.substring(start + tokenType().getOffsetLeft(), end - tokenType.getOffsetRight());
    }

    /**
     * @return whether or not this token is a closing token
     */
    public boolean isClosing() {
        return tokenType == TokenType.CLOSE;
    }
}
