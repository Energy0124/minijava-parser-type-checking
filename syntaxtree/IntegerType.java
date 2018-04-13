package syntaxtree;

import myparser.Token;
import visitor.TypeVisitor;
import visitor.Visitor;

public class IntegerType extends Type {
    public IntegerType(Token token) {
        super(token);
    }

    @Override
    public String toString() {
        return "int";
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
