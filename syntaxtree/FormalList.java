package syntaxtree;

import myparser.Token;

import java.util.Vector;

// Sequence of formal parameters
public class FormalList extends ASTNode {
    private Vector<Formal> list;

    public FormalList(Token token) {
        super(token);
        list = new Vector<Formal>();
    }

    public void addElement(Formal n) {
        list.addElement(n);
    }

    public Formal elementAt(int i) {
        return (Formal) list.elementAt(i);
    }

    public int size() {
        return list.size();
    }
}
