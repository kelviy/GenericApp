package com.example.genericapp;

import com.example.genericapp.Application.Array.ArrayManager;
import com.example.genericapp.Application.GenericData;
import com.example.genericapp.Application.TableData;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

// table code is adapted from: https://docs.oracle.com/javafx/2/ui_controls/table-view.htm#CJAGHGBD
public class ArrayController extends Application {

    // GUI Variables
    @FXML
    private TextField searchTextBox;
    @FXML
    private TableView dataTable;
    @FXML
    private TableColumn termColumn, statementColumn, scoreColumn;
    @FXML
    private Label searchStatus;
    @FXML
    private ChoiceBox dataChoiceBox;
    @FXML
    private RadioButton radioButtonTerm, radioButtonSentence;
    @FXML
    private Label actionStatus;
    private ToggleGroup searchRadioGroup = new ToggleGroup();

    // Manager object
    private ArrayManager manager = new ArrayManager("GenericsKB.txt");

    // Knowledge base Array
    private final ObservableList<TableData> data = FXCollections.observableArrayList(manager.getTotalTableArray());

    // Search button action

    @Override
    public void start(Stage stage) throws IOException {
        // Running the GUI
        FXMLLoader fxmlLoader = new FXMLLoader(ArrayController.class.getResource("array_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Array Application");
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
        if(selectedOption.equals("Term")) {
            TableData[] items = manager.getSearchItem(searchText);
            if (items.length > 0) {
                dataTable.setItems(FXCollections.observableArrayList(items));
            } else {
                actionStatus.setText("SEARCH ITEM IS NOT FOUND");
                dataTable.setItems(null);
            }
        } else {
            TableData[] items = manager.getStatementItems(searchText);
            if (items.length > 0) {
                dataTable.setItems(FXCollections.observableArrayList(items));
            } else {
                actionStatus.setText("SEARCH ITEM IS NOT FOUND");
                dataTable.setItems(null);
            }
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

        // Initialize the Choice Box
        String[] textFileList = {"GenericsKB.txt", "GenericsKB-additional.txt"};
        dataChoiceBox.setItems(FXCollections.observableArrayList(textFileList));

        // Initialize Toggle Groups
        radioButtonSentence.setToggleGroup(searchRadioGroup);
        radioButtonTerm.setToggleGroup(searchRadioGroup);
        
    }


}
