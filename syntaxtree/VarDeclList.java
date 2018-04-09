package syntaxtree;

import myparser.Token;

import java.util.Vector;

public class VarDeclList extends ASTNode{
    private Vector<VarDecl> list;

    public VarDeclList(Token token) {
        super(token);
        list = new Vector<VarDecl>();
    }

    public void addElement(VarDecl n) {
        list.addElement(n);
    }

    public VarDecl elementAt(int i) {
        return (VarDecl) list.elementAt(i);
    }

    public int size() {
        return list.size();
    }
}
