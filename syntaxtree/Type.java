package syntaxtree;

import myparser.Token;
import visitor.TypeVisitor;
import visitor.Visitor;

// Data type
public abstract class Type extends ASTNode {
    public Type(Token token) {
        super(token);
    }

    public abstract void accept(Visitor v);

    public abstract Type accept(TypeVisitor v);
}
