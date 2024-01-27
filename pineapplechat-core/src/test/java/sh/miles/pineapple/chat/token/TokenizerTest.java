package sh.miles.pineapple.chat.token;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TokenizerTest {

    @Test
    public void test_Should_Find_All_Basic_Tokens() {
        String basicallyTokened = "<red>This message has basic</red> tokens all over the place <$name>";
        final Tokenizer tokenizer = new Tokenizer(basicallyTokened);
        List<Token> expectedTokens = List.of(
                new Token(0, 5, TokenType.OPEN),
                new Token(5, 27, TokenType.CONTENT),
                new Token(27, 33, TokenType.CLOSE),
                new Token(33, 60, TokenType.CONTENT),
                new Token(60, 67, TokenType.REPLACE)
        );

        List<Token> actualTokens = new ArrayList<>();
        while (tokenizer.hasNext()) {
            actualTokens.add(tokenizer.next());
        }

        assertIterableEquals(expectedTokens, actualTokens);
    }

    @Test
    public void test_Should_Not_Find_Escaped_Tokens() {
        String escapedAll = "<click:open_url:'<><><><>\'\'\'\'::::'>content \\<escaped>";
        final Tokenizer tokenizer = new Tokenizer(escapedAll);

        List<Token> tokens = new ArrayList<>();
        while (tokenizer.hasNext()) {
            tokens.add(tokenizer.next());
        }

        assertEquals(2, tokens.size());
    }

    @Test
    public void test_Should_Yield_Escape_Tokens() {
        final String escapedToken = "\\<red> And Content! <red> and token \\<blue>";
        final Tokenizer tokenizer = new Tokenizer(escapedToken);

        List<Token> tokens = new ArrayList<>();
        while(tokenizer.hasNext()) {
            Token token = tokenizer.next();
            System.out.println(token);
            System.out.println(token.detail(escapedToken));
            if (token.tokenType() == TokenType.ESCAPED) {
                tokens.add(token);
            }
        }

        assertEquals(2, tokens.size());
    }

    public static void main(String[] args) {
        String text = "<gray>Hello! Press <gold><bold><keybind:key.jump></gold> to jump";
        Tokenizer tokenizer = new Tokenizer(text);
        while (tokenizer.hasNext()) {
            final Token token = tokenizer.next();
            System.out.println(token);
            System.out.println(token.detail(text));
        }
    }

}
