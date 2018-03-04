import syntaxtree.*;
import visitor.*;

import java.io.IOException;

import myparser.*;

public class Main {
	public static void main(String[] args) {
		MiniJavaParser parser;
		if (args.length == 0) {
			System.out.println("MiniJavaParser:  Reading from standard input . . .");
			parser = new MiniJavaParser(System.in);
		} else if (args.length == 1) {
			System.out.println("MiniJavaParser:  Reading from file " + args[0] + " . . .");
			try {
				parser = new MiniJavaParser(new java.io.FileInputStream(args[0]));
			} catch (java.io.FileNotFoundException e) {
				System.out.println("MiniJavaParser:  File " + args[0] + " not found.");
				return;
			}
		} else {
			System.out.println("MiniJavaParser:  Usage is one of:");
			System.out.println("         java Main < inputfile");
			System.out.println("OR");
			System.out.println("         java Main inputfile");
			return;
		}
		try {
			Program root = parser.Goal();
			root.accept(new PrettyPrintVisitor());

		} catch (ParseException e) {
			System.out.println(e.toString());
		}
	}
}
