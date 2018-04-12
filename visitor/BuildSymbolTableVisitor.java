package visitor;

import syntaxtree.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BuildSymbolTableVisitor extends TypeDepthFirstVisitor {

    SymbolTable symbolTable;
    HashMap<Object, Integer> idRefMap;
    int nextIdRef = 0;
    public  int addToIdRefMap(Object o) {
        if (idRefMap.get(o) == null) {

            idRefMap.put(o, nextIdRef);
            return nextIdRef++;
        } else {
            return -1;
        }
    }

    // In global scope => both currClass and currMethod are null
    // Contains class declaration
    // Inside a class (but not in a method) => currMethod is null
    // Contains field and method declarations
    // Inside a method
    // Contains declaration of local variables
    // These two variables help keep track of the current scope.
    private Class currClass;
    private Method currMethod;

    public BuildSymbolTableVisitor() {
        symbolTable = new SymbolTable();
        idRefMap = new HashMap<>();

    }

    public SymbolTable getSymTab() {
        return symbolTable;
    }

    public HashMap<Object, Integer> getIdRefMap() {
        return idRefMap;
    }

    public void setIdRefMap(HashMap<Object, Integer> idRefMap) {
        this.idRefMap = idRefMap;
    }
    // Note: Because in MiniJava there is no nested scopes and all local
    // variables can only be declared at the beginning of a method. This "hack"
    // uses two variables instead of a stack to track nested level.

    // MainClass m;
    // ClassDeclList cl;
    public Type visit(Program n) {
        n.m.accept(this); // Main class declaration

        // Declaration of remaining classes
        for (int i = 0; i < n.cl.size(); i++) {
            n.cl.elementAt(i).accept(this);
        }

        ArrayList<String> keysToRemove = new ArrayList<>();

        for (Iterator<String> iterator = symbolTable.getHashtable().keySet().iterator(); iterator.hasNext(); ) {
            String key = iterator.next();
            Class c = symbolTable.getClass(key);
            Class thisClass = symbolTable.getClass(key);
            // if parent class don't exist, the class is invalid, we should remove the class?
            //no! we shouldn't remove the class from the table! Just make it extends nothing
            while (c.parent != null) {
                if (symbolTable.getClass(c.parent) == null) {
                    //symbolTable.getHashtable().remove(key);
//                    iterator.remove();
//                    System.out.println("Error: invalid parent");

//                    System.out.println(c.parent + ": Unknown identifier ( Line " + c.parentIdentifier.token.beginLine + " Column " + c.parentIdentifier.token.beginColumn
//                            + ")");
                    thisClass.parentIdentifier=null;
                    thisClass.parent = null;
//                    keysToRemove.add(key);
                    break;
                } else {
                    c = symbolTable.getClass(c.parent);
                }
            }
        }

        for (String key :
                keysToRemove) {
            symbolTable.getHashtable().remove(key);
        }

        return null;
    }

    // Identifier i1 (name of class),i2 (name of argument in main();
    // Statement s;
    public Type visit(MainClass n) {
        symbolTable.addClass(n.i1, null);
        currClass = symbolTable.getClass(n.i1.toString());
        int idRef = addToIdRefMap(currClass);


        // this is an ugly hack.. but its not worth having a Void and
        // String[] type just for one occourance
        currMethod = new Method("main", new IdentifierType("void", n.token));
//        currMethod.addVar(n.i2.toString(), new IdentifierType("String[]", n.token));
        currMethod.addVar(n.i2, new IdentifierType("String[]", n.token));

        n.s.accept(this);

        currMethod = null;

        return null;
    }

    // Identifier i; (Class name)
    // VarDeclList vl; (Field declaration)
    // MethodDeclList ml; (Method declaration)
    public Type visit(ClassDeclSimple n) {
        if (!symbolTable.addClass(n.i, null)) {

//            System.out.println("Class " + n.i.toString() + "is already defined");
            System.out.println(n.i.toString() + ": Redeclaration ( Line " + symbolTable.getClass(n.i.toString()).identifier.token.beginLine
                    + " Column " + symbolTable.getClass(n.i.toString()).identifier.token.beginColumn +
                    " and Line " + n.i.token.beginLine + " Column " + n.i.token.beginColumn + " )");
            return null;
//            System.exit(-1);
        }

        // Entering a new class scope (no need to explicitly leave a class scope)
        currClass = symbolTable.getClass(n.i.toString());
        int idRef = addToIdRefMap(currClass);

        // Process field declaration
        for (int i = 0; i < n.vl.size(); i++) {
            n.vl.elementAt(i).accept(this);
        }

        // Process method declaration
        for (int i = 0; i < n.ml.size(); i++) {
            n.ml.elementAt(i).accept(this);
        }
        return null;
    }

    // Identifier i; (Class name)
    // Identifier j; (Superclass's name)
    // VarDeclList vl; (Field declaration)
    // MethodDeclList ml; (Method declaration)
    public Type visit(ClassDeclExtends n) {
        if (!symbolTable.addClass(n.i, n.j)) {
//            System.out.println("Class " + n.i.toString() + "is already defined");
//            System.exit(-1);
            System.out.println(n.i.toString() + ": Redeclaration ( Line " + symbolTable.getClass(n.i.toString()).identifier.token.beginLine
                    + " Column " + symbolTable.getClass(n.i.toString()).identifier.token.beginColumn +
                    " and Line " + n.i.token.beginLine + " Column " + n.i.token.beginColumn + " )");
            return null;
        }

        // Entering a new class scope (no need to explicitly leave a class scope)
        currClass = symbolTable.getClass(n.i.toString());
        int idRef = addToIdRefMap(currClass);

        for (int i = 0; i < n.vl.size(); i++) {
            n.vl.elementAt(i).accept(this);
        }
        for (int i = 0; i < n.ml.size(); i++) {
            n.ml.elementAt(i).accept(this);
        }
        return null;
    }

    // Type t;
    // Identifier i;
    //
    // Field delcaration or local variable declaration
    public Type visit(VarDecl n) {

        Type t = n.t.accept(this);
        String id = n.i.toString();

        // Not inside a method => a field declaration
        if (currMethod == null) {

            // Add a field
            if (!currClass.addVar(n.i, t)) {
//                System.out.println(id + "is already defined in " + currClass.getId());
//                System.exit(-1);
                System.out.println(n.i.toString() + ": Redeclaration ( Line " + currClass.getVar(n.i.toString()).identifier.token.beginLine
                        + " Column " + currClass.getVar(n.i.toString()).identifier.token.beginColumn +
                        " and Line " + n.i.token.beginLine + " Column " + n.i.token.beginColumn + " )");
                return null;
            }
            int idRef = addToIdRefMap(currClass.getVar(n.i.s));

        } else {
            // Add a local variable
            if (!currMethod.addVar(n.i, t)) {
//                System.out.println(id + "is already defined in " + currClass.getId() + "." + currMethod.getId());
//                System.exit(-1);
                if (currMethod.getVar(n.i.toString()) != null) {
                    System.out.println(n.i.toString() + ": Redeclaration ( Line " + currMethod.getVar(n.i.toString()).identifier.token.beginLine
                            + " Column " + currMethod.getVar(n.i.toString()).identifier.token.beginColumn +
                            " and Line " + n.i.token.beginLine + " Column " + n.i.token.beginColumn + " )");
                } else {
                    System.out.println(n.i.toString() + ": Redeclaration ( Line " + currMethod.getParam(n.i.toString()).identifier.token.beginLine
                            + " Column " + currMethod.getParam(n.i.toString()).identifier.token.beginColumn +
                            " and Line " + n.i.token.beginLine + " Column " + n.i.token.beginColumn + " )");
                }
                return null;
            }
            int idRef = addToIdRefMap(currMethod.getVar(n.i.s));

        }
        return null;
    }

    // Type t; (Return type)
    // Identifier i; (Method name)
    // FormalList fl; (Formal parameters)
    // VarDeclList vl; (Local variables)
    // StatementList sl;
    // Exp e; (The expression that evaluates to the return value)
    //
    // Method delcaration
    public Type visit(MethodDecl n) {
        Type t = n.t.accept(this);
        String id = n.i.toString();

        if (!currClass.addMethod(n.i, t)) {
//            System.out.println("Method " + id + "is already defined in " + currClass.getId());
//            System.exit(-1);
            System.out.println(n.i.toString() + ": Redeclaration ( Line " + currClass.getMethod(n.i.toString()).identifier.token.beginLine
                    + " Column " + currClass.getMethod(n.i.toString()).identifier.token.beginColumn +
                    " and Line " + n.i.token.beginLine + " Column " + n.i.token.beginColumn + " )");
            return null;
        }

        // Entering a method scope
        currMethod = currClass.getMethod(id);
        int idRef = addToIdRefMap(currMethod);


        for (int i = 0; i < n.fl.size(); i++) {
            n.fl.elementAt(i).accept(this);
        }
        for (int i = 0; i < n.vl.size(); i++) {
            n.vl.elementAt(i).accept(this);
        }
        for (int i = 0; i < n.sl.size(); i++) {
            n.sl.elementAt(i).accept(this);
        }

        n.e.accept(this);

        // Leaving a method scope (return to class scope)
        currMethod = null;
        return null;
    }

    // Type t;
    // Identifier i;
    //
    // Register a formal parameter
    public Type visit(Formal n) {

        Type t = n.t.accept(this);
        String id = n.i.toString();

        if (!currMethod.addParam(n.i, t)) {
//            System.out.println("Formal" + id + "is already defined in " + currClass.getId() + "." + currMethod.getId());
//            System.exit(-1);
            System.out.println(n.i.toString() + ": Redeclaration ( Line " + currMethod.getParam(n.i.toString()).identifier.token.beginLine
                    + " Column " + currMethod.getParam(n.i.toString()).identifier.token.beginColumn +
                    " and Line " + n.i.token.beginLine + " Column " + n.i.token.beginColumn + " )");
            return null;
        }
        int idRef = addToIdRefMap(currMethod.getParam(n.i.s));

        return null;
    }

    public Type visit(IntArrayType n) {
        return n;
    }

    public Type visit(BooleanType n) {
        return n;
    }

    public Type visit(IntegerType n) {
        return n;
    }

    public Type visit(DoubleType n) {
        return n;
    }

    // String s;
    public Type visit(IdentifierType n) {
        return n;
    }

    // StatementList sl;
    // Optional for MiniJava (unless variable declaration is allowed inside
    // a block
    public Type visit(Block n) {
        for (int i = 0; i < n.sl.size(); i++) {
            n.sl.elementAt(i).accept(this);
        }
        return null;
    }
}
