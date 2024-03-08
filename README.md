## Running on Mac
Mac is the default configuration of the project. The user can proceed to running the application without any further configurations.

## Running on Linux
To configure the project to run on linux. Edit the `MakeFile` and replace the original text:
``` 
# flags for java command
JFLAGS = --module-path "lib-mac/" --add-modules javafx.controls,javafx.fxml -cp "bin/classes"
# flags for javac command, .class files outputted to bin/classes
JCFLAGS = -g --module-path "lib-mac/" --add-modules javafx.controls,javafx.fxml -d "bin/classes"
```
With:
```
# flags for java command
JFLAGS = --module-path "lib-linux/" --add-modules javafx.controls,javafx.fxml -cp "bin/classes"
# flags for javac command, .class files outputted to bin/classes
JCFLAGS = -g --module-path "lib-linux/" --add-modules javafx.controls,javafx.fxml -d "bin/classes"
```

## Running on Windows
To configure the project to run on linux. Edit the `MakeFile` and replace the original text:
```
# flags for java command
JFLAGS = --module-path "lib-mac/" --add-modules javafx.controls,javafx.fxml -cp "bin/classes"
# flags for javac command, .class files outputted to bin/classes
JCFLAGS = -g --module-path "lib-mac/" --add-modules javafx.controls,javafx.fxml -d "bin/classes"
```
With:
```
# flags for java command
JFLAGS = --module-path "lib-windows/" --add-modules javafx.controls,javafx.fxml -cp "bin/classes"
# flags for javac command, .class files outputted to bin/classes
JCFLAGS = -g --module-path "lib-windows/" --add-modules javafx.controls,javafx.fxml -d "bin/classes"
```
#  Running the project
The below commands are for running the application if the project has already been configured to run on the user’s Operating System. 

## Running Array Application
Running the `make` command when you’re in the directory of the project folder would compile the project and run the Array Application application. 

## Running Binary Search Application
Run the command `make build` and then the command `make run_binary` to compile and then run the Binary Search Application. 

## Accessing the Javadoc
To access the javadocs related to the project go to the javadoc directory contained in the project. To view all classes open the html file: `allclasses-index.html`. 

# Additional Queries
Please email: wxxkel001@myuct.ac.za if you have trouble running the application or any queries. 

