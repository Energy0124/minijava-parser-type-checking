package syntaxtree;

import myparser.Token;
import visitor.TypeVisitor;
import visitor.Visitor;

public class NewObject extends Exp {
    public Identifier i;

    public NewObject(Identifier ai, Token token) {
        super(token);
        i = ai;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public Type accept(TypeVisitor v) {
        return v.visit(this);
    }
}
