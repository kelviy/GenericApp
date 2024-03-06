package com.wxxkel001.genericapp;

import com.wxxkel001.genericapp.Manager.Array.ArrayManager;
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
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * This class acts like an API for the GUI and the Manager class. It stores a manager instance which it uses to interact with the backend side of the application.
 * @author Kelvin Wei
 */
public class ArrayController extends Application {

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
    private RadioButton radioButtonTerm, radioButtonSentence;
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
    private ArrayManager manager = new ArrayManager();

    // Knowledge base Array
    private ObservableList<TableData> data = FXCollections.observableArrayList(manager.getTotalTableArray());

    /**
     * Starts the GUI
     * @param stage
     * @throws IOException
     */
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

    /**
     * Gets User Inputted Data. This data includes the user's decision to search via term or sentence. The application would then search the knowledge base for any items that matches the knowledge base - on term or sentence. The application would then display all found items on the table in the GUI.
     */
    @FXML
    protected void onSearchButtonClick() {
        // Retrieve User Input
        String searchText = searchTextBox.getText();
        String selectedOption = ((RadioButton)searchRadioGroup.getSelectedToggle()).getText();

        // Search via Knowledge Base with Term or Statement and change items displayed on Table
        if(selectedOption.equals("Term")) {
            TableData[] items = manager.searchListTermResult(searchText);
            if (items.length > 0) {
                dataTable.setItems(FXCollections.observableArrayList(items));
                actionStatus.setTextFill(Color.GREEN);
                actionStatus.setText("SEARCH Successful");
            } else {
                actionStatus.setTextFill(Color.RED);
                actionStatus.setText("SEARCH ITEM IS NOT FOUND");
                dataTable.setItems(null);
            }
        } else {
            TableData[] items = manager.searchListSentenceResult(searchText);
            if (items.length > 0) {
                actionStatus.setTextFill(Color.GREEN);
                actionStatus.setText("SEARCH Successful");
                dataTable.setItems(FXCollections.observableArrayList(items));
            } else {
                actionStatus.setTextFill(Color.RED);
                actionStatus.setText("SEARCH ITEM IS NOT FOUND");
                dataTable.setItems(null);
            }
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

    public static String convertToUnicode(String input) {
        StringBuilder sb = new StringBuilder();

        for (char c : input.toCharArray()) {
            sb.append("\\u").append(Integer.toHexString((int) c));
        }

        return sb.toString();
    }

    /**
     * Retrieves User Input. Checks if term and sentence are filled. If not, the GUI prompts user to fill all fields. Once all fields are filled, the GUI would then insert/update the data using the manager object and update the table again.
     */
    @FXML
    protected void onAddButton() {
        String status = "";
        if (termTextBox.getText().isEmpty() || statementTextBox.getText().isEmpty()) {
            status = "INSERT FAILED: Please fill all data (term, sentence, score)";
        } else {
            status = manager.addItem(termTextBox.getText(), statementTextBox.getText(), spinnerScore.getValue());
            data = FXCollections.observableArrayList(manager.getTotalTableArray());
            dataTable.setItems(data);
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

        // Initialize Toggle Groups
        radioButtonSentence.setToggleGroup(searchRadioGroup);
        radioButtonTerm.setToggleGroup(searchRadioGroup);

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
