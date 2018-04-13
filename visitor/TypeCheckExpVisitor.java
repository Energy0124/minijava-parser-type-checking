package visitor;

import syntaxtree.*;

public class TypeCheckExpVisitor extends TypeDepthFirstVisitor {


    // Exp e1,e2;
    public Type visit(And n) {
        if (!(n.e1.accept(this) instanceof BooleanType)) {
//            System.out.println("Left side of And must be of type integer");
            System.out.println("Wrong left expression type for And operator, expecting " + "boolean" + ", given " + n.e1.accept(new TypeCheckExpVisitor()) + ". ( Line " + n.e1.token.beginLine + " Column " + n.e1.token.beginColumn + " )");

            //System.exit(-1);
        }
        if (!(n.e2.accept(this) instanceof BooleanType)) {
//            System.out.println("Right side of And must be of type integer");
            System.out.println("Wrong right expression type for And operator, expecting " + "boolean" + ", given " + n.e2.accept(new TypeCheckExpVisitor()) + ". ( Line " + n.e2.token.beginLine + " Column " + n.e2.token.beginColumn + " )");

            //System.exit(-1);
        }
        return new BooleanType(n.token);
    }

    // Exp e1,e2;
    public Type visit(LessThan n) {
        Type type1 = n.e1.accept(this);
        if (!(type1 instanceof IntegerType || type1 instanceof DoubleType)) {
//            System.out.println("Left side of LessThan must be of type integer");
            System.out.println("Wrong left expression type for LessThan operator, expecting " + "int or double" + ", given " + n.e1.accept(new TypeCheckExpVisitor()) + ". ( Line " + n.e1.token.beginLine + " Column " + n.e1.token.beginColumn + " )");

            //System.exit(-1);
        }
        Type type2 = n.e2.accept(this);
        if (!(type2 instanceof IntegerType || type2 instanceof DoubleType)) {
//            System.out.println("Right side of LessThan must be of type integer");
            System.out.println("Wrong right expression type for LessThan operator, expecting " + "int or double" + ", given " + n.e2.accept(new TypeCheckExpVisitor()) + ". ( Line " + n.e2.token.beginLine + " Column " + n.e2.token.beginColumn + " )");

            //System.exit(-1);
        }
        return new BooleanType(n.token);
    }

    // Exp e1,e2;
    public Type visit(Plus n) {
        Type type1 = n.e1.accept(this);
        if (!(type1 instanceof IntegerType || type1 instanceof DoubleType)) {
//            System.out.println("Left side of Plus must be of type integer");
            System.out.println("Wrong left expression type for Plus operator, expecting " + "int or double" + ", given " + n.e1.accept(new TypeCheckExpVisitor()) + ". ( Line " + n.e1.token.beginLine + " Column " + n.e1.token.beginColumn + " )");

            //System.exit(-1);
        }
        Type type2 = n.e2.accept(this);
        if (!(type2 instanceof IntegerType || type2 instanceof DoubleType)) {
//            System.out.println("Right side of Plus must be of type integer");
            System.out.println("Wrong right expression type for Plus operator, expecting " + "int or double" + ", given " + n.e2.accept(new TypeCheckExpVisitor()) + ". ( Line " + n.e2.token.beginLine + " Column " + n.e2.token.beginColumn + " )");

            //System.exit(-1);
        }

        if (type1 instanceof DoubleType || type2 instanceof DoubleType) {
            return new DoubleType(n.token);
        }
        return new IntegerType(n.token);
    }

    // Exp e1,e2;
    public Type visit(Minus n) {
        Type type1 = n.e1.accept(this);
        if (!(type1 instanceof IntegerType || type1 instanceof DoubleType)) {
//            System.out.println("Left side of Minus must be of type integer");
            System.out.println("Wrong left expression type for Minus operator, expecting " + "int or double" + ", given " + n.e1.accept(new TypeCheckExpVisitor()) + ". ( Line " + n.e1.token.beginLine + " Column " + n.e1.token.beginColumn + " )");

            //System.exit(-1);
        }
        Type type2 = n.e2.accept(this);
        if (!(type2 instanceof IntegerType || type2 instanceof DoubleType)) {
//            System.out.println("Right side of Minus must be of type integer");
            System.out.println("Wrong right expression type for Minus operator, expecting " + "int or double" + ", given " + n.e2.accept(new TypeCheckExpVisitor()) + ". ( Line " + n.e2.token.beginLine + " Column " + n.e2.token.beginColumn + " )");

            //System.exit(-1);
        }

        if (type1 instanceof DoubleType || type2 instanceof DoubleType) {
            return new DoubleType(n.token);
        }
        return new IntegerType(n.token);
    }

    // Exp e1,e2;
    public Type visit(Times n) {
        Type type1 = n.e1.accept(this);
        if (!(type1 instanceof IntegerType || type1 instanceof DoubleType)) {
//            System.out.println("Left side of Times must be of type integer");
            System.out.println("Wrong left expression type for Times operator, expecting " + "int or double" + ", given " + n.e1.accept(new TypeCheckExpVisitor()) + ". ( Line " + n.e1.token.beginLine + " Column " + n.e1.token.beginColumn + " )");

            //System.exit(-1);
        }
        Type type2 = n.e2.accept(this);
        if (!(type2 instanceof IntegerType || type2 instanceof DoubleType)) {
//            System.out.println("Right side of Times must be of type integer");
            System.out.println("Wrong right expression type for Times operator, expecting " + "int or double" + ", given " + n.e2.accept(new TypeCheckExpVisitor()) + ". ( Line " + n.e2.token.beginLine + " Column " + n.e2.token.beginColumn + " )");

            //System.exit(-1);
        }

        if (type1 instanceof DoubleType || type2 instanceof DoubleType) {
            return new DoubleType(n.token);
        }
        return new IntegerType(n.token);
    }

    // Exp e1,e2;
    public Type visit(ArrayLookup n) {
        if (!(n.e1.accept(this) instanceof IntArrayType)) {
//            System.out.println("Left side of ArrayLookup must be of type integer");
            System.out.println("ArrayLookup can only be called on int [], expecting " + "int []" + ", given " + n.e1.accept(new TypeCheckExpVisitor()) + ". ( Line " + n.e1.token.beginLine + " Column " + n.e1.token.beginColumn + " )");

            //System.exit(-1);
        }
        if (!(n.e2.accept(this) instanceof IntegerType)) {
//            System.out.println("Right side of ArrayLookup must be of type integer");
            System.out.println("Array index must be of type int, expecting " + "int" + ", given " + n.e2.accept(new TypeCheckExpVisitor()) + ". ( Line " + n.e2.token.beginLine + " Column " + n.e2.token.beginColumn + " )");

            //System.exit(-1);
        }
        return new IntegerType(n.token);
    }

    // Exp e;
    public Type visit(ArrayLength n) {
        if (!(n.e.accept(this) instanceof IntArrayType)) {
//            System.out.println("Left side of ArrayLength must be of type integer");
            System.out.println("ArrayLength can only be accessed on int [], expecting " + "int []" + ", given " + n.e.accept(new TypeCheckExpVisitor()) + ". ( Line " + n.e.token.beginLine + " Column " + n.e.token.beginColumn + " )");

            //System.exit(-1);
        }
        return new IntegerType(n.token);
    }

    // Exp e;
    // Identifier i;
    // ExpList el;
    public Type visit(Call n) {

        if (!(n.e.accept(this) instanceof IdentifierType)) {
//            System.out.println("method " + n.i.toString()
//                    + "called  on something that is not a" +
//                    " class or Object.");
            System.out.println("Cannot call method " + n.i.toString() + "() on something that is not a Class or Object, expecting " + "Class or Object" + ", given " + n.e.accept(new TypeCheckExpVisitor()) + ". ( Line " + n.e.token.beginLine + " Column " + n.e.token.beginColumn + " )");
            return new UndefinedType(n.i.toString(), "method", n.token);

            //System.exit(-1);
        }

        String mname = n.i.toString();
        String cname = ((IdentifierType) n.e.accept(this)).s;

        Method calledMethod = TypeCheckVisitor.symbolTable.getMethod(mname, cname);


        if (calledMethod != null) {

            if (n.el.size() > calledMethod.params.size()) {
                System.out.println("Too many arguments are passed to " + cname + "::" + mname +
                        ", expecting " + calledMethod.params.size() + ", given " + n.el.size() + ". ( Line " + n.i.token.beginLine + " Column " + n.i.token.beginColumn + " )");
                return TypeCheckVisitor.symbolTable.getMethodType(mname, cname);

            } else if (n.el.size() < calledMethod.params.size()) {
                System.out.println("Too few arguments are passed to " + cname + "::" + mname +
                        ", expecting " + calledMethod.params.size() + ", given " + n.el.size() + ". ( Line " + n.i.token.beginLine + " Column " + n.i.token.beginColumn + " )");
                return TypeCheckVisitor.symbolTable.getMethodType(mname, cname);
            }
            for (int i = 0; i < n.el.size(); i++) {
                Type t1 = null;
                Type t2 = null;

                if (calledMethod.getParamAt(i) != null)
                    t1 = calledMethod.getParamAt(i).type();
                t2 = n.el.elementAt(i).accept(this);
                if (!TypeCheckVisitor.symbolTable.compareTypes(t1, t2)) {
//                    System.out.println("Type Error in arguments passed to " + cname + "." + mname);
                    System.out.println("Wrong argument type passed to " + cname + "::" + mname +
                            ", expecting " + t1 + ", given " + t2 + ". ( Line " + n.el.elementAt(i).token.beginLine + " Column " + n.el.elementAt(i).token.beginColumn + " )");

                    //                //System.exit(-1);
                }
            }

            return TypeCheckVisitor.symbolTable.getMethodType(mname, cname);
        } else {
//            System.out.println("Method " + mname + " not defined in class " + cname);
            System.out.println("Method " + mname + " is undefined in class " + cname + ". ( Line " + n.i.token.beginLine + " Column " + n.i.token.beginColumn + " )");

            return new UndefinedType(cname + "::" + mname, "method", n.token);
        }
    }

    // int i;
    public Type visit(IntegerLiteral n) {
        return new IntegerType(n.token);
    }

    public Type visit(True n) {
        return new BooleanType(n.token);
    }

    public Type visit(False n) {
        return new BooleanType(n.token);
    }

    // String s;
    public Type visit(IdentifierExp n) {

        Type varType = TypeCheckVisitor.symbolTable.getVarType(TypeCheckVisitor.currMethod,
                TypeCheckVisitor.currClass, n.s);
        if (varType == null) {
            return new UndefinedType(n.s, "type", n.token);
        }
        return varType;
    }

    public Type visit(This n) {
        return TypeCheckVisitor.currClass.type();
    }

    // Exp e;
    public Type visit(NewArray n) {

        if (!(n.e.accept(this) instanceof IntegerType)) {
//            System.out.println("Left side of NewArray must be of type integer");
            System.out.println("Array size of NewArray operator must be of type int, expecting " + "int" + ", given " + n.e.accept(new TypeCheckExpVisitor()) + ". ( Line " + n.e.token.beginLine + " Column " + n.e.token.beginColumn + " )");

            //System.exit(-1);
        }
        return new IntArrayType(n.token);
    }

    // Identifier i;
    public Type visit(NewObject n) {
        return new IdentifierType(n.i.s, n.token);
    }

    // Exp e;
    public Type visit(Not n) {
        if (!(n.e.accept(this) instanceof BooleanType)) {
//            System.out.println("Left side of Not must be of type boolean");
            System.out.println("Left side of Not operator must be of type boolean, expecting " + "boolean" + ", given " + n.e.accept(new TypeCheckExpVisitor()) + ". ( Line " + n.e.token.beginLine + " Column " + n.e.token.beginColumn + " )");

            //System.exit(-1);
        }
        return new BooleanType(n.token);
    }

}
//TypeCheckVisitor.






