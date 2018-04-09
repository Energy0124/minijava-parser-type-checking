package syntaxtree;

import myparser.Token;
import visitor.TypeVisitor;
import visitor.Visitor;

public class For extends Statement {
    public ForInit f;
    public Exp e;
    public StmtExprList l;
    public Statement s;

    public For(ForInit f, Exp e, StmtExprList l, Statement s, Token token) {
        super(token);
        this.f = f;
        this.e = e;
        this.l = l;
        this.s = s;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
