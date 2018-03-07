MiniJavaVariantParser
By Ling Leong
SID: 1155062557
For CSCI3120 Asgn2

a.	Description of the files you added or modified (with respect to the files in chap4.zip)
    The minijava.jj file are renamed and modified to MiniJavaVariant.jj, its location are moved to the myparser folder. Its content are modified to complete the requirement of task 1, 2 and 3.
    The original visitors classes are modified to meet requirements. Task2Visitor and Task3Visitor are added, which are modified based on PrettyPrintVisitor.
    A few classes are added to the syntaxtree package, including ArrayAssignExpr, AssignExpr, Divides, Exponential, For, ForInit, StmtExprList,
    LocalVariableDeclList, LocalVariableDecl, StmtExpr, Or, UnaryMinus.
    Makefile are also modified to reflect the changes to filename.
    Main.java in the default package are modified to Task2.java and Task3.java, which can be used to run Task2 and Task3 respectively.

b.	Instructions to run your program.
    There are 2 ways to build the program. Use the Makefile or manually run it.
    To use the Makefile, simply run "make" in the base directory where the Makefile is located at. It will build Task2.class and Task3.class automatically.
    To run the sample testcase, you can run "make task2" and "make task3" respectively to test Task2 and 3 using input/Test.java
    To test using other file, run "java Task2 < [input.java]" or "java Task3 < [input.java]" and replace the [input.java] with your file.
    In case the makefile don't work, you can build it manually with the instruction as follows.

    To build it manually, first generated the java file with javacc, and then compile them, by running:

    mkdir -p myparser
    javacc -OUTPUT_DIRECTORY="myparser" myparser/MiniJavaVariant.jj
    javac -Xlint:unchecked myparser/*.java

    Then we can compile Task2.java and Task3.java by running:

    javac -Xlint:unchecked Task2.java
    javac -Xlint:unchecked Task3.java

    To run the program, run "java Task2 < [input.java]" or "java Task3 < [input.java]" and replace the [input.java] with the input file.

c.	The assumptions you make, if any.
    For both Task2 and 3, the pretty print from the original PrettyPrintVisitor are not changed, so it is still not properly formatted, as mentioned in the piazza.
    For Task3, when translating for loop to while loop, the indentation is likely incorrect due to the limitation of the original implementation.
    I assumed that the indentation is not important for this task.

d.	Acknowledgement of third party code, files, and library (besides those included in chap4.zip) you used in your solution.
e.	Any additional information that could help us understand your implementation.
