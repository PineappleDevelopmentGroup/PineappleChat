package sh.miles.pineapple.chat.node;

import org.jetbrains.annotations.NotNull;
import sh.miles.pineapple.chat.token.Token;

public class RootNode extends BaseNode {
    /**
     * Creates a new BaseNode
     *
     * @param source the source string
     */
    public RootNode(@NotNull final String source) {
        super(null, null, source);
    }

    @Override
    public BaseNode getParent() {
        return null;
    }

    @Override
    public Token getToken() {
        throw new UnsupportedOperationException("Can not run getToken on RootNode");
    }
}
