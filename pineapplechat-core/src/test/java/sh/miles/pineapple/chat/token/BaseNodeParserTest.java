package sh.miles.pineapple.chat.token;

import org.junit.jupiter.api.Test;
import sh.miles.pineapple.chat.node.BaseNodeParser;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BaseNodeParserTest {

    @Test
    public void test_Should_Not_Throw() {
        final String toParse = "<red>Today </red><gold>class</gold> We will be putting on the play <dark_red><$play_name></dark_red> \\<escaped>";
        System.out.println(assertDoesNotThrow(() -> BaseNodeParser.parseTree(toParse, Map.of("play_name", "Hamlet"))));
    }

}
