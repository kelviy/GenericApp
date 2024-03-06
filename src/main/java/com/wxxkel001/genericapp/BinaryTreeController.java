package com.wxxkel001.genericapp;

import com.wxxkel001.genericapp.Manager.BinaryTree.BinaryTreeManager;
import com.wxxkel001.genericapp.Manager.GenericData;
import com.wxxkel001.genericapp.Manager.TableData;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * This class acts sort of like an API for the GUI and the Manager class. It has a manager instance which it uses to interact with the backend side of the application.
 * @author Kelvin Wei
 */

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
    @FXML
    private VBox vBox;
    @FXML
    private SplitPane splitPane;
    @FXML
    private ScrollPane scrollPane;


    // Manager object
    private BinaryTreeManager manager = new BinaryTreeManager();

    private ObservableList<TableData> data = FXCollections.observableArrayList(manager.getTotalTableArray());

    /**
     * Starts the GUI from binaryTree_view.fxml in resources folder
     * @param stage
     * @throws IOException
     */
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

    /**
     * Retrieves User Input and Searches the Knowledge Base with Term or Statement. The matching terms/statements are then displayed on the table in GUI
     */
    @FXML
    protected void onSearchButtonClick() {
        // Retrieve User Input
        String searchText = searchTextBox.getText();

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

    /**
     * Clears the search Text Box and repopulates the table with the knowledge base
     */
    @FXML
    protected void onClearButtonClick() {
        // Clears the search Text Box and populate table with knowledge base
        searchTextBox.setText("");
        dataTable.setItems(data);

        // Changes the status at the bottom right
        searchStatus.setText("Search Filter: 'NULL'");
        actionStatus.setText("");
    }

    /**
     * Retrieves User Input. Checks if term and sentence are filled. If not, the GUI prompts user to fill all fields. Once all fields are filled, the GUI would then insert/update the data using the manager object and update the table again.
     */
    @FXML
    protected void onAddButton() {
        String status = "SUCCESS";

        // checks if fields are empty or not
        if (termTextBox.getText() != "" && statementTextBox.getText() != "") {
            // adds and updates the table
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

    /**
     * User enters the name of a text file. The text file will then be attempted to be loaded. If file is found, the text file data will be loaded to the knowledge base.
     */
    @FXML
    protected void onLoadButtonClick() {
        // user input
        String fileName = loadTextBox.getText();
        if (new File(fileName).exists()) {
            // loads the data if file is found
            manager.loadData(fileName);
            FXCollections.observableArrayList(manager.getTotalTableArray());
            data = FXCollections.observableArrayList(manager.getTotalTableArray());
            dataTable.setItems(data);
            actionStatus.setTextFill(Color.GREEN);
            actionStatus.setText("FILE LOADED SUCCESSFULLY");
        } else {
            // displays a FILE NOT FOUND status
            actionStatus.setTextFill(Color.RED);
            actionStatus.setText("FILE NOT FOUND");
        }
    }

    /**
     * This method initializes the table and sets a background color for the GUI.
     */
    @FXML
    protected void initialize() {
        // table code is adapted from: https://docs.oracle.com/javafx/2/ui_controls/table-view.htm#CJAGHGBD
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


        // sets background color
        vBox.setBackground(new Background(
                new BackgroundFill(
                        new LinearGradient(0, 0, 0, 1, true,
                                CycleMethod.NO_CYCLE,
                                new Stop(0, Color.web("#A9CCE3")),
                                new Stop(1, Color.web("#D4E6F1"))
                        ), CornerRadii.EMPTY, Insets.EMPTY
                )
        ));
        
        splitPane.setBackground(Background.EMPTY);
        scrollPane.setStyle("-fx-background: #D4E6F1; -fx-background-color: #D4E6F1; ");

    }
}
