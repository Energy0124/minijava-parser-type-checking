package syntaxtree;

import myparser.Token;
import visitor.TypeVisitor;
import visitor.Visitor;

public abstract class Exp extends ASTNode {
    public Exp(Token token) {
        super(token);
        // TODO Auto-generated constructor stub
    }

    public abstract void accept(Visitor v);

    public abstract Type accept(TypeVisitor v);
}
