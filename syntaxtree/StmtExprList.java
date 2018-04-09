package syntaxtree;

import myparser.Token;

import java.util.Vector;

public class StmtExprList extends ForInit {
    private Vector<StmtExpr> list;

    public StmtExprList(Token token) {
        super(token);
        list = new Vector<StmtExpr>();
    }

    public void addElement(StmtExpr n) {
        list.addElement(n);
    }

    public StmtExpr elementAt(int i) {
        return (StmtExpr) list.elementAt(i);
    }

    public int size() {
        return list.size();
    }

    @Override
    public boolean hasType() {
        return false;
    }

    @Override
    public Type type() {
        return null;
    }
}
