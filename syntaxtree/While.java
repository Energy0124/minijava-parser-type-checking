package syntaxtree;

import myparser.Token;
import visitor.TypeVisitor;
import visitor.Visitor;

public class While extends Statement {
    public Exp e;
    public Statement s;

    public While(Exp ae, Statement as, Token token) {
        super(token);
        e = ae;
        s = as;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}

