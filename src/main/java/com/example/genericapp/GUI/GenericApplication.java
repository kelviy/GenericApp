package com.example.genericapp.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GenericApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Running the GUI
        FXMLLoader fxmlLoader = new FXMLLoader(GenericApplication.class.getResource("GUI_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Generic Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}