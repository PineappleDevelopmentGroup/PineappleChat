package sh.miles.pineapple.chat.token;

import java.util.Map;

/**
 * Tokenizer which works through a string and turns it into tokens
 */
public class Tokenizer {

    private final String string;
    private final int stringLength;
    private int cursor = 0;
    private boolean startHasEscape;

    /**
     * Creates a new Tokenizer
     *
     * @param string the string to tokenize
     */
    public Tokenizer(String string) {
        this.string = string;
        this.stringLength = string.length();
    }

    public boolean hasNext() {
        return this.cursor < string.length();
    }

    public Token next() {
        if (!hasNext()) {
            return null;
        }

        int lastTokenEnd = this.cursor;

        char current;
        int start = nextStart();
        this.startHasEscape = start > 0 && this.string.charAt(start - 1) == TokenConstants.ESCAPE;

        if (this.cursor == this.stringLength) {
            return new Token(lastTokenEnd, this.stringLength, TokenType.CONTENT);
        }

        if (start > lastTokenEnd) {
            return new Token(lastTokenEnd, startHasEscape ? start - 1 : start, TokenType.CONTENT);
        }

        int end = nextEnd();

        if (end == -1) {
            throw new IllegalStateException("the token that started a capture at index %d never ended. Capturing: %s".formatted(start, string.substring(start)));
        }

        TokenType type;
        current = string.charAt(start + 1);
        if (this.startHasEscape) {
            type = TokenType.ESCAPED;
        } else if (current == TokenConstants.CLOSE_DENOTE) {
            type = TokenType.CLOSE;
        } else if (current == TokenConstants.REPLACE_DENOTE) {
            type = TokenType.REPLACE;
        } else {
            type = TokenType.OPEN;
        }

        return new Token(start, end, type);
    }

    private int nextStart() {
        for (; this.cursor < this.stringLength; ++this.cursor) {
            if (this.string.charAt(this.cursor) == TokenConstants.OPEN) {
                return cursor;
            }
        }
        return -1;
    }

    private int nextEnd() {
        boolean quoteEscaped = false;
        boolean escaped = false;
        char current;
        for (; cursor < stringLength; ++cursor) {
            current = string.charAt(this.cursor);
            if (current == TokenConstants.ESCAPE) {
                escaped = !escaped;
            } else if (current == TokenConstants.QUOTE_ESCAPE && !escaped) {
                quoteEscaped = !quoteEscaped;
            } else if (current == TokenConstants.CLOSE && !quoteEscaped && !escaped) {
                return cursor += 1;
            } else {
                escaped = false;
            }
        }

        return -1;
    }
}
