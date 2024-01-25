package sh.miles.pineapple.chat.token;

/**
 * Tokenizer which works through a string and turns it into tokens
 */
public class Tokenizer {

    private final String string;
    private int cursor = 0;

    /**
     * Creates a new Tokenizer
     *
     * @param string the string to tokenize
     */
    public Tokenizer(String string) {
        this.string = string;
    }

    public boolean hasNext() {
        return this.cursor < string.length();
    }

    /**
     * @return the next token
     */
    public Token next() {
        boolean escaped = false;
        if (!hasNext()) {
            return null;
        }

        char current;
        int lastTokenEnd = this.cursor;
        int start = -1;
        for (; cursor < string.length(); ++cursor) {
            current = string.charAt(this.cursor);
            if (current == TokenConstants.ESCAPE) {
                escaped = true;
                continue;
            }

            if (current == TokenConstants.OPEN && !escaped) {
                start = cursor;
                break;
            }

            escaped = false;
        }

        if (this.cursor == string.length()) {
            return new Token(lastTokenEnd, string.length(), TokenType.CONTENT);
        }

        if (start > lastTokenEnd) {
            return new Token(lastTokenEnd, start, TokenType.CONTENT);
        }

        int end = -1;
        boolean quoteEscaped = false;
        for (; cursor < string.length(); ++cursor) {
            current = string.charAt(this.cursor);
            if (current == TokenConstants.ESCAPE) {
                escaped = !escaped;
            } else if (current == TokenConstants.QUOTE_ESCAPE && !escaped) {
                quoteEscaped = !quoteEscaped;
            } else if (current == TokenConstants.CLOSE && !quoteEscaped && !escaped) {
                end = cursor += 1;
                break;
            } else {
                escaped = false;
            }
        }


        if (end == -1) {
            throw new IllegalStateException("the token that started a capture at index %d never ended. Capturing: %s".formatted(start, string.substring(start)));
        }

        TokenType tokenType;
        current = string.charAt(start + 1);
        if (current == TokenConstants.CLOSE_DENOTE) {
            tokenType = TokenType.CLOSE;
        } else if (current == TokenConstants.REPLACE_DENOTE) {
            tokenType = TokenType.REPLACE;
        } else {
            tokenType = TokenType.OPEN;
        }

        return new Token(start, end, tokenType);
    }

}
