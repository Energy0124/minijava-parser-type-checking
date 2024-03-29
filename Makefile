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



all: Task1Main.class Task2Main.class

Task1Main.class: Task1Main.java $(PARSER)

Task2Main.class: Task2Main.java $(PARSER)

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

task1:
	java Task1Main num < input/test1.java

task2:
	java Task2Main < input/test2.java

