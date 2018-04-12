package syntaxtree;

import myparser.Token;
import visitor.TypeVisitor;
import visitor.Visitor;

public class IntArrayType extends Type {
    public IntArrayType(Token token) {
        super(token);

    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }

    @Override
    public String toString() {
        return "int []";
    }
}
