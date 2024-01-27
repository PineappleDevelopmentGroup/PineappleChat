package sh.miles.pineapple.chat.bungee.parse;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.miles.pineapple.chat.node.BaseNodeParser;
import sh.miles.pineapple.chat.parse.PineappleParserContext;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class BungeePineappleParserContextTest {

    private PineappleParserContext<BaseComponent> parser;

    @BeforeEach
    public void setup() {
        parser = new BungeePineappleParserContext();
    }

    @Test
    public void test_Not_Throw_On_Parse_Valid_Syntax() {
//        String text = "<red>Hello <blue>my</blue> <bold>friend <italic>he <obfuscated><gradient:#FFF000:#000FFF>said</gradient><reset> yo <lang:container.barrel> BARREL";
        String text = "<gray>Hello! \\<red> Press <gold><bold><keybind:key.jump></gold> to jump";
        BaseComponent component = assertDoesNotThrow(() -> parser.parse(text));
        System.out.println(ComponentSerializer.toString(component));
    }

}
