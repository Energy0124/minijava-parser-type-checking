package syntaxtree;

import visitor.TypeVisitor;
import visitor.Visitor;

public class AssignExpr extends StmtExpr {

	public Identifier i;
	public Exp e;

	public AssignExpr(Identifier i, Exp e) {
		this.i = i;
		this.e = e;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

	public Type accept(TypeVisitor v) {
		return v.visit(this);
	}

}
