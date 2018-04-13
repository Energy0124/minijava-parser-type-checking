package visitor;

import syntaxtree.*;

public class TypeCheckVisitor extends DepthFirstVisitor {

    static Class currClass;
    static Method currMethod;
    static SymbolTable symbolTable;

    public TypeCheckVisitor(SymbolTable s) {
        symbolTable = s;
    }

    // MainClass m;
    // ClassDeclList cl;
    public void visit(Program n) {
        n.m.accept(this);
        for (int i = 0; i < n.cl.size(); i++) {
            n.cl.elementAt(i).accept(this);
        }
    }

    // Identifier i1,i2;
    // Statement s;
    public void visit(MainClass n) {
        String i1 = n.i1.toString();
        currClass = symbolTable.getClass(i1);

        n.i2.accept(this);
        n.s.accept(this);
    }

    // Identifier i;
    // VarDeclList vl;
    // MethodDeclList ml;
    public void visit(ClassDeclSimple n) {
        String id = n.i.toString();
        currClass = symbolTable.getClass(id);
        for (int i = 0; i < n.vl.size(); i++) {
            n.vl.elementAt(i).accept(this);
        }
        for (int i = 0; i < n.ml.size(); i++) {
            n.ml.elementAt(i).accept(this);
        }
    }

    // Identifier i;
    // Identifier j;
    // VarDeclList vl;
    // MethodDeclList ml;
    public void visit(ClassDeclExtends n) {
        String id = n.i.toString();
        currClass = symbolTable.getClass(id);
        n.j.accept(this);
        for (int i = 0; i < n.vl.size(); i++) {
            n.vl.elementAt(i).accept(this);
        }
        for (int i = 0; i < n.ml.size(); i++) {
            n.ml.elementAt(i).accept(this);
        }
    }

    // Type t;
    // Identifier i;
    public void visit(VarDecl n) {
        n.t.accept(this);
        n.i.accept(this);
    }

    // Type t;
    // Identifier i;
    // FormalList fl;
    // VarDeclList vl;
    // StatementList sl;
    // Exp e;
    public void visit(MethodDecl n) {
        n.t.accept(this);
        String id = n.i.toString();
        currMethod = currClass.getMethod(id);
        if (currMethod != null) {
            Type retType = currMethod.type();
            for (int i = 0; i < n.fl.size(); i++) {
                n.fl.elementAt(i).accept(this);
            }
            for (int i = 0; i < n.vl.size(); i++) {
                n.vl.elementAt(i).accept(this);
            }
            for (int i = 0; i < n.sl.size(); i++) {
                n.sl.elementAt(i).accept(this);
            }
            if (!symbolTable.compareTypes(retType, n.e.accept(new TypeCheckExpVisitor()))) {
                System.out.println("Wrong return type for method " + id + ", expecting " + retType + ", given " + n.e.accept(new TypeCheckExpVisitor()) + ". ( Line " + n.e.token.beginLine + " Column " + n.e.token.beginColumn + " )");
//                System.exit(0);
            }
        }

    }

    // Type t;
    // Identifier i;
    public void visit(Formal n) {
        n.t.accept(this);
        n.i.accept(this);
    }

    // Exp e;
    // Statement s1,s2;
    public void visit(If n) {
        if (!(n.e.accept(new TypeCheckExpVisitor()) instanceof BooleanType)) {
            System.out.println("Wrong condition type for if statement, expecting boolean, given " + n.e.accept(new TypeCheckExpVisitor()) + ". ( Line " + n.e.token.beginLine + " Column " + n.e.token.beginColumn + " )");
//            System.exit(-1);
        }
        n.s1.accept(this);
        n.s2.accept(this);
    }

    // Exp e;
    // Statement s;
    public void visit(While n) {
        if (!(n.e.accept(new TypeCheckExpVisitor()) instanceof BooleanType)) {
            System.out.println("Wrong condition type for while statement, expecting boolean, given " + n.e.accept(new TypeCheckExpVisitor()) + ". ( Line " + n.e.token.beginLine + " Column " + n.e.token.beginColumn + " )");
//            System.exit(-1);
        }
        n.s.accept(this);
    }

    // Exp e;
    public void visit(Print n) {
        if (!(n.e.accept(new TypeCheckExpVisitor()) instanceof IntegerType)) {
            System.out.println("Wrong argument type for System.out.println(), expecting int, given " + n.e.accept(new TypeCheckExpVisitor()) + ". ( Line " + n.e.token.beginLine + " Column " + n.e.token.beginColumn + " )");
//            System.exit(-1);
        }
    }

    // Identifier i;
    // Exp e;
    public void visit(Assign n) {
        Type t1 = symbolTable.getVarType(currMethod, currClass, n.i.toString());
        Type t2 = n.e.accept(new TypeCheckExpVisitor());
        if (!symbolTable.compareTypes(t1, t2)) {
//            System.out.println("Type error in assignment to " + n.i.toString());
            System.out.println("Cannot assign expression of wrong type to " + n.i.toString() + ", expecting " + t1 + ", given " + t2 + ". ( Line " + n.e.token.beginLine + " Column " + n.e.token.beginColumn + " )");

//            System.exit(0);
        }
    }

    // Identifier i;
    // Exp e1,e2;
    public void visit(ArrayAssign n) {
        Type typeI = symbolTable.getVarType(currMethod, currClass, n.i.toString());

        if (!(typeI instanceof IntArrayType)) {
//            System.out.println("The identifier in an array assignment" +
//                    "must be of type int []");
            System.out.println("Wrong variable type for array assignment, " + n.i.toString() + " is not an array, expecting " + "int []" + ", given " + typeI + ". ( Line " + n.i.token.beginLine + " Column " + n.i.token.beginColumn + " )");

//            System.exit(-1);
        }

        Type indexType = n.e1.accept(new TypeCheckExpVisitor());
        if (!(indexType instanceof IntegerType)) {
//            System.out.println("The first expression in an array assignment" +
//                    "must be of type int");
            System.out.println("Array index must be of type int for array assignment to " + n.i.toString() + ", expecting " + "int" + ", given " + indexType + ". ( Line " + n.e1.token.beginLine + " Column " + n.e1.token.beginColumn + " )");

//            System.exit(-1);
        }
        Type assignedType = n.e2.accept(new TypeCheckExpVisitor());
        if (!(assignedType instanceof IntegerType)) {
//            System.out.println("The second expression in an array assignment" +
//                    "must be of type int");
            System.out.println("Wrong expression type for array assignment to " + n.i.toString() + ", expecting " + "int" + ", given " + assignedType + ". ( Line " + n.e2.token.beginLine + " Column " + n.e2.token.beginColumn + " )");

//            System.exit(-1);
        }
    }
}



