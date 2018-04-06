import syntaxtree.*;
import visitor.*;
import myparser.*;

public class Task1Main {
   public static void main(String [] args) {
      try {
         Program root = new MiniJavaVariantParser(System.in).Goal();

	 // Build the symbol table
	 BuildSymbolTableVisitor buildSymTab = new BuildSymbolTableVisitor();
         root.accept(buildSymTab);

	 // Type check
	 TypeCheckVisitor typeCheck =
             new TypeCheckVisitor(buildSymTab.getSymTab());
         root.accept(typeCheck);
      }
      catch (ParseException e) {
         System.out.println(e.toString());
      }
   }
}
