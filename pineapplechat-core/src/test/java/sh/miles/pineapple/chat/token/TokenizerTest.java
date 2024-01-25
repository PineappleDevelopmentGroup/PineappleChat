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
    public void test_Should_Not_Find_Escaped_Toknes() {
        String escapedAll = "<click:open_url:'<><><><>\'\'\'\'::::'>content \\<escaped>";
        final Tokenizer tokenizer = new Tokenizer(escapedAll);

        List<Token> tokens = new ArrayList<>();
        while (tokenizer.hasNext()) {
            tokens.add(tokenizer.next());
        }

        assertEquals(2, tokens.size());
    }

}
