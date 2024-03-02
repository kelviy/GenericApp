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

    exports com.wxxkel001.genericapp.Application;
    opens com.wxxkel001.genericapp.Application to javafx.fxml;
    exports com.wxxkel001.genericapp.Application.BinaryTree;
    opens com.wxxkel001.genericapp.Application.BinaryTree to javafx.fxml;
    exports com.wxxkel001.genericapp.Application.Array;
    opens com.wxxkel001.genericapp.Application.Array to javafx.fxml;
    exports com.wxxkel001.genericapp;
    opens com.wxxkel001.genericapp to javafx.fxml;
}