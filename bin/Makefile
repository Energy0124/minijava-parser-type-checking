# Store the parser files generated by JavaCC
JAVACC_OUTDIR = myparser
JAVACC_FILE = myparser/MiniJavaVariant.jj
PARSER = $(JAVACC_OUTDIR)/MiniJavaVariantParser.java

JAVAC_OPTIONS = -Xlint:unchecked

# AST and Visitor source files
AST = $(wildcard syntaxtree/*.java)
VISITOR = $(wildcard visitor/*.java)

# AST and Visitor class files
AST_CLASS = $(AST:.java=.class)
VISITOR_CLASS = $(VISITOR:.java=.class)



all: Task2.class Task3.class

Task2.class: Task2.java $(PARSER)

Task3.class: Task3.java $(PARSER)

%.class: %.java
	javac $(JAVAC_OPTIONS) $<

$(PARSER): $(JAVACC_FILE) 
	# Create the directory if it does not exist
	mkdir -p $(JAVACC_OUTDIR)
	javacc -OUTPUT_DIRECTORY="$(JAVACC_OUTDIR)" $(JAVACC_FILE)
	javac $(JAVAC_OPTIONS) $(JAVACC_OUTDIR)/*.java

clean:
	rm -rf $(JAVACC_OUTDIR) *.class
	rm -f syntaxtree/*.class visitor/*.class

task2:
	java Task2 < input/Test.java

task3:
	java Task3 < input/Test.java

