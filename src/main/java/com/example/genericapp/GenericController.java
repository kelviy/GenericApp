package com.example.genericapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class GenericController {

    @FXML
    private TextField searchTextBox;
    @FXML
    private TableView dataTable;
    @FXML
    private TableColumn termColumn;
    @FXML
    private TableColumn statementColumn;
    @FXML
    private TableColumn scoreColumn;
    @FXML
    private Label searchStatus;


    private GenericsKbArrayApp manager = new GenericsKbArrayApp("GenericsKB.txt");
    private final ObservableList<TableData> data = FXCollections.observableArrayList(manager.getTableArray());
    @FXML
    protected void onSearchButtonClick() {
        String searchText = searchTextBox.getText();
        dataTable.setItems(FXCollections.observableArrayList(manager.getSearchItem(searchText)));
        searchStatus.setText("Search Filter: '" + searchText + "'");
    }

    @FXML
    protected void onClearButtonClick() {
        searchTextBox.setText("");
        dataTable.setItems(data);
        searchStatus.setText("Search Filter: 'NULL'");
    }

    @FXML
    protected void initialize() {

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
