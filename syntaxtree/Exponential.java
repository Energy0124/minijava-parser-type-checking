package syntaxtree;

import myparser.Token;
import visitor.TypeVisitor;
import visitor.Visitor;

public class Exponential extends Exp {
    public Exp e1, e2;

    public Exponential(Exp ae1, Exp ae2, Token token) {
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
