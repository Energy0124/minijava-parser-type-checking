package syntaxtree;

import myparser.Token;
import visitor.TypeVisitor;
import visitor.Visitor;

// e1 && e2
public class And extends Exp {
    public Exp e1, e2;

    public And(Exp ae1, Exp ae2, Token token) {
        super(token);
        e1 = ae1;
        e2 = ae2;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
