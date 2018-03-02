package syntaxtree;

import visitor.TypeVisitor;
import visitor.Visitor;

public class LocalVariableDecl extends StmtExpr {
    public Type t;
    public Identifier i;
    public Exp e;

    public LocalVariableDecl(Type at, Identifier ai, Exp e) {
        t=at; i=ai; this.e = e;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
