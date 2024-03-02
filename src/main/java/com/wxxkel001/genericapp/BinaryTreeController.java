package com.wxxkel001.genericapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BinaryTreeController extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Running the GUI
        FXMLLoader fxmlLoader = new FXMLLoader(BinaryTreeController.class.getResource("binaryTree_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Binary Tree Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
