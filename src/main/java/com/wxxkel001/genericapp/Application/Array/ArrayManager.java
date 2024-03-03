package com.wxxkel001.genericapp.Application.Array;

import com.wxxkel001.genericapp.Application.GenericData;
import com.wxxkel001.genericapp.Application.TableData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ArrayManager {

    private GenericData[] data = new GenericData[100_000];
    private int count = 0;
    public ArrayManager(String textFile) {
        loadData(textFile);
    }

    public ArrayManager() {

    }
    // loads data from TextFile name and returns a fixed size Array with loaded data
    public void loadData(String textFile) {
        try {
            BufferedReader ff = new BufferedReader(new FileReader(textFile));

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

    private int searchTerm(String term) {
        for(int i = 0; i < count; i++) {
            if (data[i].getTerm().equalsIgnoreCase(term)) {
                return i;
            }
        }
        return -1;
    }
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

    // returns all data Array converted to Table Array
    public TableData[] getTotalTableArray() {
        return TableData.convertGenericToTable(data);
    }

    // returns an all items that match the given term
    public TableData[] searchListTermResult(String term) {
        GenericData[] searchItems = searchMultiTerm(term);
        TableData[] returnData = TableData.convertGenericToTable(searchItems);

        return returnData;
    }

    // returns all items that match the given statement
    public TableData[] searchListSentenceResult(String sentence) {
        GenericData[] matched = searchMultiSentence(sentence);
        TableData[] returnData = TableData.convertGenericToTable(matched);

        return returnData;
    }


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
