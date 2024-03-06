JC = javac
JAVA = java

# flags for java command
JFLAGS = --module-path "lib-mac/" --add-modules javafx.controls,javafx.fxml -cp "bin/classes"
# flags for javac command, .class files outputted to bin/classes
JCFLAGS = -g --module-path "lib-mac/" --add-modules javafx.controls,javafx.fxml -d "bin/classes"

# .java files of the project
SOURCES = $(wildcard src/main/java/com/wxxkel001/genericapp/*.java) $(wildcard src/main/java/com/wxxkel001/genericapp/Manager/*.java) $(wildcard src/main/java/com/wxxkel001/genericapp/Manager/Array/*.java) $(wildcard src/main/java/com/wxxkel001/genericapp/Manager/BinaryTree/*.java)

all: build run_array

run_array:
	$(JAVA) $(JFLAGS) com.wxxkel001.genericapp.ArrayController

run_binary:
	$(JAVA) $(JFLAGS) com.wxxkel001.genericapp.BinaryTreeController

build:
	$(JC) $(JCFLAGS) $(SOURCES)

clean:
	$(RM) *.class
