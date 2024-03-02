package com.wxxkel001.genericapp;

import com.wxxkel001.genericapp.Application.Array.ArrayManager;
import com.wxxkel001.genericapp.Application.BinaryTree.BinaryTreeManager;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BinaryTreeController extends Application {

    // Manager object
    private BinaryTreeManager manager = new BinaryTreeManager();

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

    @FXML
    protected void onSearchButtonClick() {

    }

    @FXML
    protected void onClearButtonClick() {

    }

    @FXML
    protected void onAddButton() {

    }

    @FXML
    protected void onLoadButtonClick() {

    }

    @FXML
    protected void initialize() {

    }
}
