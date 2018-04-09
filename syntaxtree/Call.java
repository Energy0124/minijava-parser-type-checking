package syntaxtree;

import myparser.Token;
import visitor.TypeVisitor;
import visitor.Visitor;

public class Call extends Exp {
    public Exp e;
    public Identifier i;
    public ExpList el;

    public Call(Exp ae, Identifier ai, ExpList ael, Token token) {
        super(token);
        e = ae;
        i = ai;
        el = ael;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
