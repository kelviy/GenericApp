package com.wxxkel001.genericapp;

import com.wxxkel001.genericapp.Manager.BinaryTree.BinaryTreeManager;
import com.wxxkel001.genericapp.Manager.GenericData;
import com.wxxkel001.genericapp.Manager.TableData;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class BinaryTreeController extends Application {

    // GUI Variables
    @FXML
    private TextField searchTextBox, termTextBox, loadTextBox;
    @FXML
    private TextArea statementTextBox;
    @FXML
    private TableView dataTable;
    @FXML
    private TableColumn termColumn, statementColumn, scoreColumn;
    @FXML
    private Label searchStatus;
    @FXML
    private Label actionStatus;
    private ToggleGroup searchRadioGroup = new ToggleGroup();
    @FXML
    private Spinner<Double> spinnerScore;


    // Manager object
    private BinaryTreeManager manager = new BinaryTreeManager();

    private ObservableList<TableData> data = FXCollections.observableArrayList(manager.getTotalTableArray());
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
        // Retrieve User Input
        String searchText = searchTextBox.getText();
        String selectedOption = ((RadioButton)searchRadioGroup.getSelectedToggle()).getText();

        // Search via Knowledge Base with Term or Statement and change items displayed on Table
            TableData items = manager.searchTerm(searchText);
            if (items != null) {
                dataTable.setItems(FXCollections.observableArrayList(items));
                actionStatus.setTextFill(Color.GREEN);
                actionStatus.setText("SEARCH Successful");
            } else {
                actionStatus.setTextFill(Color.RED);
                actionStatus.setText("SEARCH ITEM IS NOT FOUND");
                dataTable.setItems(null);
            }

        // Change Status at the bottom right
        searchStatus.setText("Search Filter: '" + searchText + "'");
    }

    @FXML
    protected void onClearButtonClick() {
        // Clears the search Text Box and populate table with knowledge base
        searchTextBox.setText("");
        dataTable.setItems(data);

        // Changes the status at the bottom right
        searchStatus.setText("Search Filter: 'NULL'");
        actionStatus.setText("");
    }

    @FXML
    protected void onAddButton() {
        String status = "SUCCESS";
        if (termTextBox.getText() != "" && statementTextBox.getText() != "") {
            manager.addItem(termTextBox.getText(), statementTextBox.getText(), spinnerScore.getValue());
            data = FXCollections.observableArrayList(manager.getTotalTableArray());
            dataTable.setItems(data);
        } else {
            status = "INSERT FAILED: Please fill all data (term, sentence, score)";
        }
        if (status.contains("FAILED")) {
            actionStatus.setTextFill(Color.RED);
        } else {
            actionStatus.setTextFill(Color.GREEN);
        }
        actionStatus.setText(status);
    }

    @FXML
    protected void onLoadButtonClick() {
        String fileName = loadTextBox.getText();
        if (new File(fileName).exists()) {
            manager.loadData(fileName);
            FXCollections.observableArrayList(manager.getTotalTableArray());
            data = FXCollections.observableArrayList(manager.getTotalTableArray());
            dataTable.setItems(data);
            actionStatus.setTextFill(Color.GREEN);
            actionStatus.setText("FILE LOADED SUCCESSFULLY");
        } else {
            actionStatus.setTextFill(Color.RED);
            actionStatus.setText("FILE NOT FOUND");
        }
    }

    @FXML
    protected void initialize() {
        // Initialize the table
        termColumn.setCellValueFactory(
                new PropertyValueFactory<TableData, String>("term")
        );
        statementColumn.setCellValueFactory(
                new PropertyValueFactory<GenericData, String>("statement")
        );
        scoreColumn.setCellValueFactory(
                new PropertyValueFactory<GenericData, Integer>("score")
        );
        dataTable.setItems(data);
    }
}
