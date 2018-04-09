package syntaxtree;

import myparser.Token;
import visitor.TypeVisitor;
import visitor.Visitor;

public abstract class Statement extends ASTNode{
    public Statement(Token token) {
        super(token);
    }

    public abstract void accept(Visitor v);

    public abstract Type accept(TypeVisitor v);
}
