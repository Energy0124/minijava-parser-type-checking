package syntaxtree;

import java.util.Vector;

public class LocalVariableDeclList extends ForInit{
    private Vector<StmtExpr> list;

    public LocalVariableDeclList() {
        list = new Vector<StmtExpr>();
    }

    public void addElement(StmtExpr n) {
        list.addElement(n);
    }

    public StmtExpr elementAt(int i)  {
        return (StmtExpr)list.elementAt(i);
    }

    public int size() {
        return list.size();
    }
}
