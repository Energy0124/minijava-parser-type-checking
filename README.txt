MiniJavaVariant TypeChecking
By Ling Leong
SID: 1155062557
For CSCI3120 Programming Assignment #3

a.	Instructions to run your program.
    There are 2 ways to build the program. Use the Makefile or manually run it.
    To use the Makefile, simply run "make" in the base directory where the Makefile is located at. It will build Task1Main.class and Task2Main.class automatically.
    To run the sample testcase, you can run "make task1" and "make task2" respectively to test Task1Main and Task2Main using input/test1.java and input/test2.java respectively.
    To test using other file, run "java Task1Main < [input.java]" or "java Task2Main < [input.java]" and replace the [input.java] with your file.
    In case the makefile don't work, you can build it manually with the instruction as follows.

    To build it manually, first generated the java file with javacc, and then compile them, by running:

    mkdir -p myparser
    javacc -OUTPUT_DIRECTORY="myparser" myparser/MiniJavaVariant.jj
    javac -Xlint:unchecked myparser/*.java

    Then we can compile Task1Main.java and Task2Main.java by running:

    javac -Xlint:unchecked Task1Main.java
    javac -Xlint:unchecked Task2Main.java

    To run the program, run "java Task1Main < [input.java]" or "java Task2Main < [input.java]" and replace the [input.java] with the input file.

b.	The assumptions you make, if any.
    I didn't implemented bonus part, so I assume that there is no method overloading in minijava.
    I also assume that there is no circular dependency in class inheritance in the source file, other it will cause infinite loop.
    I assume that the testcase for task2 will not contain redeclaration and undefined identifiers, except for method. (I believed that the spec also assume this, unless I misunderstood the spec.)

c.	Description of the input files you used to test your programs.
    I used input/test1.java to test task 1. It has many unknown identifier and redeclaration of identifier.
    I used input/test2.java to test task 2. It has no unknown identifier or redeclaration of identifier, but it has many type error and also undefined methods.

d.	Acknowledgement of third party code, files, and library (besides those included in chap4&5.zip) you used in your solution.

e.	Any additional information that could help us understand your implementation.
