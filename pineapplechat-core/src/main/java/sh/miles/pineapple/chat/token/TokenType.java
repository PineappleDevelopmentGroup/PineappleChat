package sh.miles.pineapple.chat.token;

public enum TokenType {
    /**
     * An opening Token denoted via a {@literal < and > filled with content}
     */
    OPEN(1, 1),
    /**
     * A closing Token denoted via {@literal </ and > filed with content}
     */
    CLOSE(2, 1),
    /**
     * Text usually not within open or close tags
     */
    CONTENT(0, 0),
    /**
     * A Token which can be replaced
     */
    REPLACE(2, 1),
    /**
     * A Token which contains escaped tags
     */
    ESCAPED(0, 0);

    private final int offsetLeft;
    private final int offsetRight;

    TokenType(final int offsetLeft, final int offsetRight) {
        this.offsetLeft = offsetLeft;
        this.offsetRight = offsetRight;
    }

    public int getOffsetLeft() {
        return offsetLeft;
    }

    public int getOffsetRight() {
        return offsetRight;
    }
}
