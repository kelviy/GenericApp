module com.example.genericapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.genericapp to javafx.fxml;
    exports com.example.genericapp.GUI;
    opens com.example.genericapp.GUI to javafx.fxml;
    exports com.example.genericapp.Application;
    opens com.example.genericapp.Application to javafx.fxml;
    exports com.example.genericapp.Application.BinaryTree;
    opens com.example.genericapp.Application.BinaryTree to javafx.fxml;
    exports com.example.genericapp.Application.Array;
    opens com.example.genericapp.Application.Array to javafx.fxml;
}