import myparser.MiniJavaVariantParser;
import myparser.ParseException;
import syntaxtree.Program;
import visitor.Task2Visitor;

public class Task2 {
    public static void main(String[] args) {
        MiniJavaVariantParser parser;
        if (args.length == 0) {
//			System.out.println("MiniJavaVariantParser:  Reading from standard input . . .");
            parser = new MiniJavaVariantParser(System.in);
        } else if (args.length == 1) {
//			System.out.println("MiniJavaVariantParser:  Reading from file " + args[0] + " . . .");
            try {
                parser = new MiniJavaVariantParser(new java.io.FileInputStream(args[0]));
            } catch (java.io.FileNotFoundException e) {
                System.out.println("MiniJavaVariantParser:  File " + args[0] + " not found.");
                return;
            }
        } else {
            System.out.println("MiniJavaVariantParser:  Usage is one of:");
            System.out.println("         java Task2 < inputfile");
            System.out.println("OR");
            System.out.println("         java Task2 inputfile");
            return;
        }
        try {
            Program root = parser.Goal();
            if (parser.hasError) {
                return;
            }
            root.accept(new Task2Visitor());

        } catch (ParseException e) {
            System.out.println(e.toString());
        }
    }
}
