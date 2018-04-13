package syntaxtree;

import myparser.Token;
import visitor.TypeVisitor;
import visitor.Visitor;

public class UndefinedType extends IdentifierType {
    public String s;
    public String t;

    public UndefinedType(String as, String type, Token token) {
        super(as, token);
        s = as;
        t = type;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        return "Undefined " + t + ": " + s;
    }
}
