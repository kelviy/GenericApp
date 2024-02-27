package com.example.genericapp.GUI;

import com.example.genericapp.Application.Array.GenericArrayManager;
import com.example.genericapp.Application.GenericData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

// table code is adapted from: https://docs.oracle.com/javafx/2/ui_controls/table-view.htm#CJAGHGBD
public class GenericController {

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
    private ToggleGroup searchRadioGroup = new ToggleGroup();

    // Manager object
    private GenericArrayManager manager = new GenericArrayManager("GenericsKB.txt");

    // Knowledge base Array
    private final ObservableList<TableData> data = FXCollections.observableArrayList(manager.getTotalTableArray());

    // Search button action
    @FXML
    protected void onSearchButtonClick() {
        // Retrieve User Input
        String searchText = searchTextBox.getText();
        String selectedOption = ((RadioButton)searchRadioGroup.getSelectedToggle()).getText();

        // Search via Knowledge Base with Term or Statement and change items displayed on Table
        if(selectedOption.equals("Term")) {
            dataTable.setItems(FXCollections.observableArrayList(manager.getSearchItem(searchText)));
        } else {
            dataTable.setItems(FXCollections.observableArrayList(manager.getStatementItems(searchText)));
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
