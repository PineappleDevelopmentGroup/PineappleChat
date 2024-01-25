package sh.miles.pineapple.chat.token;

/**
 * Constants surrounding tokens
 */
final class TokenConstants {

    /**
     * The Open Char
     */
    static final char OPEN = '<';
    /**
     * The Close Char
     */
    static final char CLOSE = '>';
    /**
     * Denote of a Close Tag Start
     */
    static final char CLOSE_DENOTE = '/';
    /**
     * Character used to escape tags
     */
    static final char ESCAPE = '\\';
    /**
     * Character used to set off a quote escape
     */
    static final char QUOTE_ESCAPE = '\'';
    /**
     * Character used to set off Replace
     */
    static final char REPLACE_DENOTE = '$';

    private TokenConstants() {
        throw new UnsupportedOperationException("This class can not");
    }

}
