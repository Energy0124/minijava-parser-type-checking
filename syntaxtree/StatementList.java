package syntaxtree;

import myparser.Token;

import java.util.Vector;

public class StatementList extends ASTNode {
    private Vector<Statement> list;

    public StatementList(Token token) {
        super(token);
        list = new Vector<Statement>();
    }

    public void addElement(Statement n) {
        list.addElement(n);
    }

    public Statement elementAt(int i) {
        return (Statement) list.elementAt(i);
    }

    public int size() {
        return list.size();
    }
}
