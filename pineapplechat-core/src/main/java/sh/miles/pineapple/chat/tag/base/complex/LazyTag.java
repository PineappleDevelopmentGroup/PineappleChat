package sh.miles.pineapple.chat.tag.base.complex;

import sh.miles.pineapple.chat.parse.PineappleParserContext;

/**
 * Used for signalling the insertion of things into the builder of which the length can not be immediately determined
 * before the initial parsing. For replacements please see {@link PineappleParserContext} which provides a replacements
 * with a Map.
 */
public interface LazyTag {
}
