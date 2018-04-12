package visitor;

import myparser.Token;
import syntaxtree.*;

import java.util.HashMap;

public class ReferenceCheckVisitor extends DepthFirstVisitor {

    static Class currClass;
    static Method currMethod;
    static SymbolTable symbolTable;
    static HashMap<Object, Integer> idRefMap;
    static HashMap<Object, Integer> idRefMap2;
    static int nextIdRef = 0;
    static String Y;

    public static int addToIdRefMap(Object o) {

        if (idRefMap2.get(o) == null) {

            idRefMap2.put(o, nextIdRef);
            nextIdRef++;
            return idRefMap.get(o);
        } else {
            return -1;
        }
    }

    public ReferenceCheckVisitor(SymbolTable s, String Y, HashMap<Object, Integer> idRefMap) {
        symbolTable = s;
        ReferenceCheckVisitor.idRefMap = idRefMap;
        ReferenceCheckVisitor.idRefMap2 = new HashMap<>();
        ReferenceCheckVisitor.Y = Y;
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

            n.i1.accept(this);
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
        if (currClass != null) {
            int idRef = addToIdRefMap(currClass);
            if (idRef >= 0) {
                printClassInfo(idRef);

                String className = n.j.s;
                Token token = n.j.token;

                Class aClass = symbolTable.getClass(className);

                if (aClass == null) {
                    String error = n.j.s + ": Unknown identifier ( Line " + n.j.token.beginLine + " Column " + n.j.token.beginColumn
                            + " )";
                    System.out.println(error);
                } else if (className.equals(Y)) {
                    System.out.println(token.beginLine + "," + token.beginColumn + ": " + idRefMap.get(aClass));
                }

                n.j.accept(this);
                for (int i = 0; i < n.vl.size(); i++) {
                    n.vl.elementAt(i).accept(this);
                }
                for (int i = 0; i < n.ml.size(); i++) {
                    n.ml.elementAt(i).accept(this);
                }

            }
        }
    }

    private void printClassInfo(int idRef) {
        boolean hasError = false;
        String error = "";
        if (currClass.id.equals(Y)) {
            System.out.print(idRef + ", Class");

            if (currClass.parent != null) {
                Class parentClass = symbolTable.getClass(currClass.parent);
                System.out.print(", " + parentClass.id);

                while (parentClass.parent != null) {
                    String tempParentClass = parentClass.parent;
                    Identifier tempParentClassIdentifier = parentClass.parentIdentifier;
                    parentClass = symbolTable.getClass(parentClass.parent);
                    if (parentClass != null) {
                        System.out.print(", " + parentClass.id);
                    } else {
//                        System.out.println();
                        hasError = true;
                        error = tempParentClass + ": Unknown identifier ( Line " + tempParentClassIdentifier.token.beginLine + " Column " + tempParentClassIdentifier.token.beginColumn
                                + ")";
                        break;
                    }
                }

            }
            System.out.println();
            if (hasError) {
//                System.out.println(error);

            }
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
        if (var != null) {
            int idRef = addToIdRefMap(var);
            if (idRef >= 0) {
                if (var.id.equals(Y)) {
                    if (currMethod != null) {
                        System.out.println(idRef + ", Local, " + var.type + ", " + currClass.id + "::" + currMethod.id + "(" + currMethod.getParamAsString() + ")");
                    } else {
                        System.out.println(idRef + ", Data member, " + var.type + ", " + currClass.id);

                    }
                }
                n.t.accept(this);
                n.i.accept(this);
            }
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
        int idRef = addToIdRefMap(param);
        if (idRef >= 0) {
            if (param.id.equals(Y)) {
                System.out.println(idRef + ", Param, " + param.type + ", " + currClass.id + "::" + currMethod.id + "(" + currMethod.getParamAsString() + ")");
            }
            n.t.accept(this);
            n.i.accept(this);
        }


    }

    @Override
    public void visit(Call n) {

        //no need to check for method call

        n.e.accept(this);
        n.i.accept(this);
        for (int i = 0; i < n.el.size(); i++) {
            n.el.elementAt(i).accept(this);
        }
    }


    @Override
    public void visit(IdentifierType n) {
        if (symbolTable.getClass(n.s) == null) {
            String error = n.s + ": Unknown identifier ( Line " + n.token.beginLine + " Column " + n.token.beginColumn
                    + " )";
            System.out.println(error);
        } else if (n.s.equals(Y)) {
            System.out.println(n.token.beginLine + "," + n.token.beginColumn + ": " + idRefMap.get(symbolTable.getClass(n.s)));
        }
    }

    // Identifier i;
    // Exp e;
    public void visit(Assign n) {
        String varName = n.i.s;
        Token token = n.i.token;

        printVariableReference(varName, token);
        n.i.accept(this);
        n.e.accept(this);
    }

    @Override
    public void visit(ArrayAssign n) {
        String varName = n.i.s;
        Token token = n.i.token;
        printVariableReference(varName, token);
        super.visit(n);
    }

    @Override
    public void visit(IdentifierExp n) {
        String varName = n.s;
        Token token = n.token;
        printVariableReference(varName, token);
        super.visit(n);
    }

    @Override
    public void visit(NewObject n) {
        String className = n.i.s;
        Token token = n.i.token;
        Class aClass = symbolTable.getClass(className);


        if (aClass == null) {
            String error = className + ": Unknown identifier ( Line " + token.beginLine + " Column " + token.beginColumn
                    + " )";
            System.out.println(error);
        } else if (className.equals(Y)) {
            System.out.println(token.beginLine + "," + token.beginColumn + ": " + idRefMap.get(aClass));
        }

        super.visit(n);
    }

    private void printVariableReference(String varName, Token token) {
        if (currMethod.getVar(varName) == null && currMethod.getParam(varName) == null && currClass.getVar(varName) == null) {

            String error = varName + ": Unknown identifier ( Line " + token.beginLine + " Column " + token.beginColumn
                    + " )";
            System.out.println(error);

        } else if (varName.equals(Y)) {
            if (currMethod.getVar(varName) != null) {
                System.out.println(token.beginLine + "," + token.beginColumn + ": " + idRefMap.get(currMethod.getVar(varName)));

            } else if (currMethod.getParam(varName) != null) {
                System.out.println(token.beginLine + "," + token.beginColumn + ": " + idRefMap.get(currMethod.getParam(varName)));

            } else if (currClass.getVar(varName) != null) {
                System.out.println(token.beginLine + "," + token.beginColumn + ": " + idRefMap.get(currClass.getVar(varName)));
            }
        }
    }
}



