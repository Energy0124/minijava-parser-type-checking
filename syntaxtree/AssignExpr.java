package syntaxtree;

import myparser.Token;
import visitor.TypeVisitor;
import visitor.Visitor;

public class AssignExpr extends StmtExpr {

    public Identifier i;
    public Exp e;

    public AssignExpr(Identifier i, Exp e, Token token) {
        super(token);
        this.i = i;
        this.e = e;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

}
