package sh.miles.pineapple.chat.token;

public enum TokenType {
    /**
     * An opening Token denoted via a {@literal < and > filled with content}
     */
    OPEN,
    /**
     * A closing Token denoted via {@literal </ and > filed with content}
     */
    CLOSE,
    /**
     * Text usually not within open or close tags
     */
    CONTENT,
    /**
     * A Token which can be replaced
     */
    REPLACE;
}
