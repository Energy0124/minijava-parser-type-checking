package syntaxtree;

import visitor.TypeVisitor;
import visitor.Visitor;

public class UnaryMinus extends Exp {

	public Exp e;

	public UnaryMinus(Exp e) {
		this.e = e;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);

	}

	@Override
	public Type accept(TypeVisitor v) {
		return v.visit(this);
	}

}
