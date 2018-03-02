package syntaxtree;

import visitor.TypeVisitor;
import visitor.Visitor;

public class ArrayAssignExpr extends StmtExpr {
	public Identifier i;
	public Exp e1;
	public Exp e2;

	public ArrayAssignExpr(Identifier i, Exp e1, Exp e2) {
		this.i = i;
		this.e1 = e1;
		this.e2 = e2;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

	public Type accept(TypeVisitor v) {
		return v.visit(this);
	}

}
