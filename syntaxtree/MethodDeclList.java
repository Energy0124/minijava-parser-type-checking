package syntaxtree;

import myparser.Token;

import java.util.Vector;

public class MethodDeclList extends ASTNode{
    private Vector<MethodDecl> list;

    public MethodDeclList(Token token) {
        super(token);
        list = new Vector<MethodDecl>();
    }

    public void addElement(MethodDecl n) {
        list.addElement(n);
    }

    public MethodDecl elementAt(int i) {
        return (MethodDecl) list.elementAt(i);
    }

    public int size() {
        return list.size();
    }
}
