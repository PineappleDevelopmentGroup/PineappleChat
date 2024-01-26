package sh.miles.pineapple.chat.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrieTest {

    @Test
    public void test_GetEntry() {
        Trie<Integer> trie = new Trie<>();
        trie.insert("a", 1);
        assertNotNull(trie.contains("a"));
        assertEquals(1, trie.contains("a"));
    }

}
