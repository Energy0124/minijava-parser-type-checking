package syntaxtree;

import myparser.Token;

import java.util.Vector;

public class ClassDeclList extends ASTNode{
    private Vector<ClassDecl> list;

    public ClassDeclList(Token token) {
        super(token);
        list = new Vector<ClassDecl>();
    }

    public void addElement(ClassDecl n) {
        list.addElement(n);
    }

    public ClassDecl elementAt(int i) {
        return (ClassDecl) list.elementAt(i);
    }

    public int size() {
        return list.size();
    }
}
