package sh.miles.pineapple.chat.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Special Trie Implementation. At the end of each Node their is a possible insertion for a type to be returned as an
 * entry.
 *
 * @param <E> the entry type
 * @since 1.0.0-SNAPSHOT
 */
public class Trie<E> {
    private static final char END = '$';

    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    /**
     * Inserts a given entry into the Trie
     *
     * @param string the non null string
     * @param entry  the non null entry
     * @since 1.0.0-SNAPSHOT
     */
    public void insert(@NotNull String string, @NotNull E entry) {
        Objects.requireNonNull(entry);
        string = string + END;
        TrieNode current = root;
        char at;
        for (int i = 0; i < string.length(); i++) {
            at = string.charAt(i);
            if (current.hasNoEdge(at)) {
                current = current.insertEdge(at, i == string.length() - 1
                        ? new EntryTrieNode<>(entry)
                        : new TrieNode()
                );
                continue;
            }

            current = current.getChild(at);
        }
    }

    /**
     * Attempts to find the entry if the given string is present
     *
     * @param string the string to check
     * @return the entry if the string is present, otherwise null
     * @since 1.0.0-SNAPSHOT
     */
    @Nullable
    @SuppressWarnings("unchecked")
    public E contains(@NotNull String string) {
        string = string + END;
        TrieNode current = root;
        for (int i = 0; i < string.length(); i++) {
            char at = string.charAt(i);
            if (current.hasNoEdge(at)) {
                return null;
            }

            current = current.getChild(at);
        }

        return ((EntryTrieNode<E>) current).getEntry();
    }

    private static class EntryTrieNode<E> extends TrieNode {
        private final E entry;

        EntryTrieNode(@NotNull E entry) {
            this.entry = entry;
        }

        E getEntry() {
            return entry;
        }
    }

    private static class TrieNode {
        private final Map<Character, TrieNode> nodes = new HashMap<>();

        TrieNode insertEdge(char character, @NotNull final TrieNode child) {
            nodes.put(character, child);
            return child;
        }

        @Nullable
        TrieNode getChild(char character) {
            return nodes.get(character);
        }

        boolean hasNoEdge(char character) {
            return !nodes.containsKey(character);
        }

        boolean hasNoEdges() {
            return nodes.isEmpty();
        }
    }

}
