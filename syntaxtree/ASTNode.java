package syntaxtree;

import myparser.Token;

public abstract class ASTNode {
    public Token token;

    public ASTNode(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return token.image;
    }
}
