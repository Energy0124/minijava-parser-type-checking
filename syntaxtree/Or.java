package syntaxtree;

import myparser.Token;
import visitor.TypeVisitor;
import visitor.Visitor;

public class Or extends Exp {

    public Exp e1, e2;

    public Or(Exp e1, Exp e2, Token token) {
        super(token);
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);

    }

    @Override
    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

}
