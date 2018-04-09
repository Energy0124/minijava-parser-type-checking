package syntaxtree;

import myparser.Token;

import java.util.Vector;

public class ExpList extends ASTNode{
    private Vector<Exp> list;

    public ExpList(Token token) {
        super(token);
        list = new Vector<Exp>();
    }

    public void addElement(Exp n) {
        list.addElement(n);
    }

    public Exp elementAt(int i) {
        return (Exp) list.elementAt(i);
    }

    public int size() {
        return list.size();
    }
}
