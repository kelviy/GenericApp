package com.wxxkel001.genericapp.Manager.BinaryTree;

import com.wxxkel001.genericapp.Manager.GenericData;
import com.wxxkel001.genericapp.Manager.TableData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Manager class for the Binary Tree Application. Handles the code and the data structures of the application. Some functions include loading data from text file and converting data for the GUI
 * @author Kelvin Wei
 */
public class BinaryTreeManager {
    private BinarySearchTree<GenericData> dataStructure = new BinarySearchTree<>();

    /**
     * Loads a text file if object is constructed with a text file name.
     * @param textFile
     */
    public BinaryTreeManager(String textFile) {
        loadData(textFile);
    }

    /**
     * Empty Constructor
     */
    public BinaryTreeManager() {

    }

    /**
     * Loads the data from the text file. Reads in each line, splits and creates a GenericData object which is then added to the Binary Search Tree data structure.
     * @param textFile name of the text file (Type: String)
     */
    public void loadData(String textFile) {
        try {
            BufferedReader ff = new BufferedReader(new FileReader(textFile));

            String line;

            while((line = ff.readLine()) != null) {
                String[] parts = line.split("\t");

                String term = parts[0];
                String sentence = parts[1];
                double score = Double.parseDouble(parts[2]);

                GenericData data = new GenericData(term, sentence, score);
                dataStructure.insertItem(data);
            }

        } catch(FileNotFoundException e) {
            System.out.println(e);
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Searches the data structure for the specified term. Would return null if not found.
     * @param term name of the Term (Type: String)
     * @return Object that is found (Type: TableData)
     */
    public TableData searchTerm(String term) {
        GenericData temp = new GenericData(term, null, -1);
        if (dataStructure.find(temp) == null) {
            return null;
        }
        return GenericData.convertTableData(dataStructure.find(temp).getData());
    }

    /**
     * Used for a textBased GUI. Returns a string with all the data in the knowledge base.
     * @return String with all data in knowledge base (Type: String)
     */
    public String viewAll() {
        String temp = "";
        for(GenericData i: dataStructure.getAllData()) {
            temp += i.toString();
        }
        return temp;
    }

    /**
     *  Interacts with the GUI. Would get all the data from the data structure add it to an ArrayList and convert it to a ArrayList of type TableData
     * @return An ArrayList of type (TableData)
     */
    public ArrayList<TableData> getTotalTableArray() {
        ArrayList<TableData> tableData = new ArrayList<>();
        for (GenericData i : dataStructure.getAllData()) {
            tableData.add(GenericData.convertTableData(i));
        }
        return tableData;
    }

    /**
     * Adds the data from the GUI and creates a new object which is then added to the data structure
     * @param term term name (Type: String)
     * @param sentence sentence to associated with term (Type: String)
     * @param score confidence score associated with term (Type: double)
     */
    public boolean addItem(String term, String sentence, double score) {
        return dataStructure.insertItem(new GenericData(term, sentence, score));
    }


}
