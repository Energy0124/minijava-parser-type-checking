package visitor;

import syntaxtree.*;

import java.util.HashMap;

public class ReferenceCheckVisitor extends DepthFirstVisitor {

    static Class currClass;
    static Method currMethod;
    static SymbolTable symbolTable;
    static HashMap<Object, Integer> idRefMap;
    static int nextIdRef = 0;
    static String Y;

    public static int addToIdRefMap(Object o) {
        if (idRefMap.get(o) == null) {

            idRefMap.put(o, nextIdRef);
            return nextIdRef++;
        } else {
            return -1;
        }
    }

    public ReferenceCheckVisitor(SymbolTable s, String Y) {
        symbolTable = s;
        idRefMap = new HashMap<>();
        this.Y = Y;
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
        int idRef = addToIdRefMap(currClass);
        if (idRef >= 0) {
            printClassInfo(idRef);

            n.i2.accept(this);
            n.s.accept(this);
        }
    }

    // Identifier i;
    // VarDeclList vl;
    // MethodDeclList ml;
    public void visit(ClassDeclSimple n) {
        String id = n.i.toString();
        currClass = symbolTable.getClass(id);
        int idRef = addToIdRefMap(currClass);
        if (idRef >= 0) {
            printClassInfo(idRef);
            for (int i = 0; i < n.vl.size(); i++) {
                n.vl.elementAt(i).accept(this);
            }
            for (int i = 0; i < n.ml.size(); i++) {
                n.ml.elementAt(i).accept(this);
            }
        }
    }

    // Identifier i;
    // Identifier j;
    // VarDeclList vl;
    // MethodDeclList ml;
    public void visit(ClassDeclExtends n) {
        String id = n.i.toString();
        currClass = symbolTable.getClass(id);
        int idRef = addToIdRefMap(currClass);
        if (idRef >= 0) {
            printClassInfo(idRef);
            n.j.accept(this);
            for (int i = 0; i < n.vl.size(); i++) {
                n.vl.elementAt(i).accept(this);
            }
            for (int i = 0; i < n.ml.size(); i++) {
                n.ml.elementAt(i).accept(this);
            }
        }
    }

    private void printClassInfo(int idRef) {
        if (currClass.id.equals(Y)) {
            System.out.print(idRef + ", Class");

            if (currClass.parent != null) {
                Class parentClass = symbolTable.getClass(currClass.parent);
                System.out.print(", " + parentClass.id);

                while (parentClass.parent != null) {
                    parentClass = symbolTable.getClass(parentClass.parent);
                    System.out.print(", " + parentClass.id);
                }

            }
            System.out.println();
        }
    }

    // Type t;
    // Identifier i;
    public void visit(VarDecl n) {
        Variable var;
        if (currMethod != null) {
            var = currMethod.getVar(n.i.s);

        } else {
            var = currClass.getVar(n.i.s);

        }
        int idRef = addToIdRefMap(var);
        if (idRef >= 0) {
            if (var.id.equals(Y)) {
                if (currMethod != null) {
                    System.out.println(idRef + ", Local, " + var.type + ", " + currClass.id + "::" + currMethod.id + "("+currMethod.getParamAsString()+")");
                } else {
                    System.out.println(idRef + ", Data member, " + var.type + ", " + currClass.id);

                }
            }
            n.t.accept(this);
            n.i.accept(this);
        }

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
            int idRef = addToIdRefMap(currMethod);

            if (idRef >= 0) {
                if (currMethod.id.equals(Y)) {
                    System.out.println(idRef + ", " + currClass.id + ", " + currMethod.type + " (" + currMethod.getParamAsString() + ")");
                }

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
            }
        }
        currMethod = null;

    }

    // Type t;
    // Identifier i;
    public void visit(Formal n) {
        Variable param = currMethod.getParam(n.i.s);
        addToIdRefMap(param);
        n.t.accept(this);
//        n.i.accept(this);
    }

    @Override
    public void visit(Call n) {
        n.e.accept(this);
//        n.i.accept(this);
        for (int i = 0; i < n.el.size(); i++) {
            n.el.elementAt(i).accept(this);
        }
    }

    @Override
    public void visit(IdentifierType n) {
        if (n.s.equals(Y)) {
            System.out.println(n.token.beginLine + "," + n.token.beginColumn + ": " + idRefMap.get(symbolTable.getClass(n.s)));
        }
    }

    // Identifier i;
    // Exp e;
    public void visit(Assign n) {
        if (n.i.s.equals(Y)) {
            if (currMethod.getVar(n.i.s) != null) {
                System.out.println(n.token.beginLine + "," + n.token.beginColumn + ": " + idRefMap.get(currMethod.getVar(n.i.s)));

            } else if (currMethod.getParam(n.i.s) != null) {
                System.out.println(n.token.beginLine + "," + n.token.beginColumn + ": " + idRefMap.get(currMethod.getParam(n.i.s)));

            } else {
                System.out.println(n.token.beginLine + "," + n.token.beginColumn + ": " + idRefMap.get(currClass.getVar(n.i.s)));
            }
        }
        n.i.accept(this);
        n.e.accept(this);
    }

}



