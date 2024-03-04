JC = javac
JAVA = java

JFLAGS = --module-path "lib/" --add-modules javafx.controls,javafx.fxml -cp "target/classes"
JCFLAGS = -g --module-path "lib/" --add-modules javafx.controls,javafx.fxml -d "target/classes"

SOURCES = $(wildcard src/main/java/com/wxxkel001/genericapp/*.java) $(wildcard src/main/java/com/wxxkel001/genericapp/Manager/*.java) $(wildcard src/main/java/com/wxxkel001/genericapp/Manager/Array/*.java) $(wildcard src/main/java/com/wxxkel001/genericapp/Manager/BinaryTree/*.java)

all: build run_array

run_array:
	$(JAVA) $(JFLAGS) com.wxxkel001.genericapp.ArrayController

run_binary:
	$(JAVA) $(JFLAGS) com.wxxkel001.genericapp.BinaryTreeController

build:
	$(JC) $(JCFLAGS) src/main/java/com/wxxkel001/genericapp/BinaryTreeController.java src/main/java/com/wxxkel001/genericapp/ArrayController.java

clean:
	$(RM) *.class