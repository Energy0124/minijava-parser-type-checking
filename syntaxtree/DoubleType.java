package syntaxtree;

import myparser.Token;
import visitor.TypeVisitor;
import visitor.Visitor;

public class DoubleType extends Type {
    public DoubleType(Token token) {
        super(token);

    }

    @Override
    public String toString() {
        return "double";
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
