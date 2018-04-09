package syntaxtree;

import myparser.Token;
import visitor.TypeVisitor;
import visitor.Visitor;

public class Assign extends Statement {
    public Identifier i;
    public Exp e;

    public Assign(Identifier ai, Exp ae, Token token) {
        super(token);
        i = ai;
        e = ae;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}

