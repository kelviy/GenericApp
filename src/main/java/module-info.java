module com.wxxkel001.genericapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens com.wxxkel001.genericapp to javafx.fxml;
    exports com.wxxkel001.genericapp;
    exports com.wxxkel001.genericapp.Manager;
    opens com.wxxkel001.genericapp.Manager to javafx.fxml;
    exports com.wxxkel001.genericapp.Manager.Array;
    opens com.wxxkel001.genericapp.Manager.Array to javafx.fxml;
    exports com.wxxkel001.genericapp.Manager.BinaryTree;
    opens com.wxxkel001.genericapp.Manager.BinaryTree to javafx.fxml;
}