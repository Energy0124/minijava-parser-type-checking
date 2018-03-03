package syntaxtree;

import java.util.Vector;

public class LocalVariableDeclList extends ForInit {
	public Type t;
	private Vector<StmtExpr> list;

	public LocalVariableDeclList(Type t) {
		this.t = t;
		list = new Vector<StmtExpr>();
	}

	public void addElement(StmtExpr n) {
		list.addElement(n);
	}

	public StmtExpr elementAt(int i) {
		return (StmtExpr) list.elementAt(i);
	}

	public int size() {
		return list.size();
	}

	@Override
	public boolean hasType() {
		return true;
	}

	@Override
	public Type type() {
		return t;
	}
}
