package syntaxtree;

import visitor.TypeVisitor;
import visitor.Visitor;

public class For extends Statement {
    public For(ForInit f, Exp e, StmtExprList l, Statement s) {
        super();
    }

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public Type accept(TypeVisitor v) {
        return null;
    }
}
