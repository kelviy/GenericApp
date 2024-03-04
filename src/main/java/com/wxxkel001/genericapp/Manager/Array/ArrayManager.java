package com.wxxkel001.genericapp.Manager.Array;

import com.wxxkel001.genericapp.Manager.GenericData;
import com.wxxkel001.genericapp.Manager.TableData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * <p>
 *     This class manages and coordinates between the data structures(Array data structure) and the GUI. It handles all the code that would be run
 * </p>
 * @author Kelvin Wei (WXXKEL001)
 */
public class ArrayManager {

    private GenericData[] data = new GenericData[100_000];
    private int count = 0;

    /**
     * Constructor for the ArrayManager. It would initialise the object and load the text file contents if passed as a parameter
     * @param textFile text file name to be loaded into data structure (Type: String)
     */
    public ArrayManager(String textFile) {
        loadData(textFile);
    }

    /**
     * Constructor for ArrayManager. It is an empty constructor to just initialise the object
     */
    public ArrayManager() {

    }

    /**
     * Loads data from given text file name and adds the content to the Manager's Data Structure (Array)
     * @param textFile text file name to be loaded (Type: String)
     */
    // loads data from TextFile name and returns a fixed size Array with loaded data
    public void loadData(String textFile) {
        try {
            BufferedReader ff = new BufferedReader(new FileReader(textFile));

            // reads next line then splits the lines and add item
            String line;

            while((line = ff.readLine()) != null) {
                String[] parts = line.split("\t");

                String term = parts[0];
                String sentence = parts[1];
                double score = Double.parseDouble(parts[2]);

                addItem(term, sentence, score);
            }

        } catch(FileNotFoundException e) {
            System.out.println(e);
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    /**
     * This is a private method that acts as a helper method for searching for an existing term in the data structure.
     * @param term search term (Type: String)
     * @return returns -1 if not found. Else returns the index of found term (Type: int)
     */
    private int searchTerm(String term) {
        for(int i = 0; i < count; i++) {
            if (data[i].getTerm().equalsIgnoreCase(term)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This is a private method that would return and array of all GenericData that match the search term. It is private as it is a helper method to searchListTermResult() - interacts with the GUI.
     * @param term The name of the search term (Type: String)
     * @return returns an fixed array (Type: GenericData[]) of size 100_000. Array contains null if no matching terms, else contains matched terms
     */
    private GenericData[] searchMultiTerm(String term) {
        GenericData[] matched = new GenericData[100_000];
        int foundCount = 0;

        for(int i = 0; i < count; i++) {
            if (data[i].getTerm().toLowerCase().contains(term.toLowerCase())) {
                matched[foundCount] = data[i];
                foundCount++;
            }
        }
        return matched;
    }

    /**
     * This is a private method that would return and array of all GenericData that has a sentence that matches the search sentence. It is private as it is a helper method to searchListSentenceResult() - interacts with the GUI.
     * @param sentence The search string for sentence (Type: String)
     * @return returns an fixed array (Type: GenericData[]) of size 100_000. Array contains null if no matching terms, else contains matched terms
     */
    private GenericData[] searchMultiSentence(String sentence) {
        GenericData[] matched = new GenericData[100_000];
        int foundCount = 0;

        for(int i = 0; i < count; i++) {
            if (data[i].getSentence().toLowerCase().contains(sentence.toLowerCase())) {
                matched[foundCount] = data[i];
                foundCount++;
            }
        }

        return matched;
    }

    /**
     * Called by the GUI to display all current objects in the knowledge base. It returns the global 'data' Array converted to Array of (Type: TableData). Conversion is for compatibility for the GUI to understand the data.
     * @return Array of TableData (Type: TableData[])
     */
    public TableData[] getTotalTableArray() {
        return TableData.convertGenericToTable(data);
    }

    /**
     * Called by the GUI to display all objects in the knowledge base that match the search term. An Array (Type: GenericData) is converted to Array of (Type: TableData). Conversion is for compatibility for the GUI to understand the data.
     * returns an all items that match the given term.
     * @param term The name of the term (Type: String)
     * @return
     */
    public TableData[] searchListTermResult(String term) {
        GenericData[] searchItems = searchMultiTerm(term);
        TableData[] returnData = TableData.convertGenericToTable(searchItems);

        return returnData;
    }

    /**
     * Called by the GUI to display all objects in the knowledge base that match the search sentence. An Array (Type: GenericData) is converted to Array of (Type: TableData). Conversion is for compatibility for the GUI to understand the data.
     * @param sentence The name of the term (Type: String)
     * @return
     */
    public TableData[] searchListSentenceResult(String sentence) {
        GenericData[] matched = searchMultiSentence(sentence);
        TableData[] returnData = TableData.convertGenericToTable(matched);

        return returnData;
    }

    /**
     * Called by the GUI to add an object to the knowledge base. The method would check if the item is in the knowledge base. If not found in the knowledge base then it would add the object. Else would check if original score is lower than the object being inserted - If true update the object, else do not update. Would return the status of the insert to be displayed by the GUI
     * @param term The term of the object (Type: String)
     * @param sentence The sentence of the object (Type: String)
     * @param score The confidence score of the object (Type: double)
     * @return
     */
    public String addItem(String term, String sentence, double score) {
        int index = searchTerm(term);

        if (index >= 0 && data[index].getScore() < score) {
            data[index].setSentence(sentence);
            data[index].setScore(score);
            return "UPDATE SUCCESSFUL";
        } if (index < 0) {
            data[count] = new GenericData(term, sentence, score);
            count++;
            return "INSERT SUCCESSFUL";
        }

        return "UPDATE FAILED: Confidence Score not lower than original";
    }
}
