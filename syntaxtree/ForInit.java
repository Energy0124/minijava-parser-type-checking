package syntaxtree;

import myparser.Token;

public abstract class ForInit extends ASTNode{
    public ForInit(Token token) {
        super(token);
    }

    public abstract void addElement(StmtExpr n);

    public abstract StmtExpr elementAt(int i);

    public abstract int size();

    public abstract boolean hasType();

    public abstract Type type();
}
