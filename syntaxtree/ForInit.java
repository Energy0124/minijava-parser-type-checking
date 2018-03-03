package syntaxtree;

public abstract class ForInit {
	public abstract void addElement(StmtExpr n);

	public abstract StmtExpr elementAt(int i);

	public abstract int size();

	public abstract boolean hasType();

	public abstract Type type();
}
