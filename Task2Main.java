import myparser.MiniJavaVariantParser;
import myparser.ParseException;
import syntaxtree.Program;
import visitor.BuildSymbolTableVisitor;
import visitor.ReferenceCheckVisitor;
import visitor.TypeCheckVisitor;

public class Task2Main {
    public static void main(String[] args) {
        MiniJavaVariantParser parser;
        if (args.length == 0) {
            // System.out.println("MiniJavaVariantParser: Reading from standard input . .
            // .");
            parser = new MiniJavaVariantParser(System.in);
        } else if (args.length == 1) {
            // System.out.println("MiniJavaVariantParser: Reading from file " + args[0] + "
            // . . .");
            try {
                parser = new MiniJavaVariantParser(new java.io.FileInputStream(args[0]));
            } catch (java.io.FileNotFoundException e) {
                System.out.println("MiniJavaVariantParser:  File " + args[0] + " not found.");
                return;
            }
        } else {
            System.out.println("MiniJavaVariantParser:  Usage is one of:");
            System.out.println("         java Task2Main < inputfile");
            System.out.println("OR");
            System.out.println("         java Task2Main inputfile");
            return;
        }
        try {
//            String Y = args[0];
            Program root = parser.Goal();
            // Build the symbol table
            BuildSymbolTableVisitor buildSymTab = new BuildSymbolTableVisitor();
//            buildSymTab.setPrintError(false);
            root.accept(buildSymTab);
            // Ref check
//            ReferenceCheckVisitor refCheck = new ReferenceCheckVisitor(buildSymTab.getSymTab(), Y,buildSymTab.getIdRefMap());
//            root.accept(refCheck);
            // Type check
            TypeCheckVisitor typeCheck = new TypeCheckVisitor(buildSymTab.getSymTab());
            root.accept(typeCheck);

        } catch (ParseException e) {
            System.out.println(e.toString());
        }

    }
}
