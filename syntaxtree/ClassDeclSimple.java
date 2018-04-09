package syntaxtree;

import com.sun.corba.se.impl.oa.toa.TOA;
import myparser.Token;
import visitor.TypeVisitor;
import visitor.Visitor;

public class ClassDeclSimple extends ClassDecl {
    public Identifier i;
    public VarDeclList vl;
    public MethodDeclList ml;

    public ClassDeclSimple(Identifier ai, VarDeclList avl, MethodDeclList aml, Token token) {
        super(token);
        i = ai;
        vl = avl;
        ml = aml;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
