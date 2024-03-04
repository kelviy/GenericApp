run_array:
	java --module-path "lib/" --add-modules javafx.controls,javafx.fxml -cp "target/classes" com.wxxkel001.genericapp.ArrayController

run_binary:
	java --module-path "lib/" --add-modules javafx.controls,javafx.fxml -cp "target/classes" com.wxxkel001.genericapp.BinaryTreeController

build:
	javac --module-path "lib/" --add-modules javafx.controls,javafx.fxml -d "target/classes" src/main/java/com/wxxkel001/genericapp/BinaryTreeController.java src/main/java/com/wxxkel001/genericapp/ArrayController.java	
