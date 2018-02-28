import syntaxtree.*;
import visitor.*;
import myparser.*;

public class Main {
	public static void main(String[] args) {
		try {
			System.out.println("start");
			Program root = new MiniJavaParser(System.in).Goal();
			root.accept(new PrettyPrintVisitor());
		} catch (ParseException e) {
			System.out.println(e.toString());
		}
	}
}
