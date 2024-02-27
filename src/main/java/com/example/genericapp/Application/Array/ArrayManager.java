package com.example.genericapp.Application.Array;

import com.example.genericapp.Application.GenericData;
import com.example.genericapp.Application.TableData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ArrayManager {

    private GenericData[] data;
    private int count = 0;
    public ArrayManager(String textFile) {
        data = loadData(textFile);
    }

    // loads data from TextFile name and returns an fixed size Array with loaded data
    public GenericData[] loadData(String textFile) {

        GenericData[] textData = new GenericData[100_000];

        try {
            BufferedReader ff = new BufferedReader(new FileReader(textFile));

            String line;

            while((line = ff.readLine()) != null) {
                String[] parts = line.split("\t");

                String term = parts[0];
                String statement = parts[1];
                double score = Double.parseDouble(parts[2]);

                textData[count] = new GenericData(term, statement, score);
                count++;
            }

        } catch(FileNotFoundException e) {
            System.out.println(e);
        } catch(IOException e) {
            System.out.println(e);
        }

        return textData;
    }


    private GenericData[] searchTerm(String term) {
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

    private GenericData[] searchStatement(String sentence) {
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
        return convertGenericToTable(data);
    }

    // returns an all items that match the given term
    public TableData[] getSearchItem(String term) {
        GenericData[] searchItems = searchTerm(term);
        TableData[] returnData = convertGenericToTable(searchItems);

        return returnData;
    }

    // returns all items that match the given statement
    public TableData[] getStatementItems(String statement) {
        GenericData[] matched = searchStatement(statement);
        TableData[] returnData = convertGenericToTable(matched);

        return returnData;
    }

    // Converts an inputted Generic Array and outputs a TableData Array without null elements trailing at the end
    public TableData[] convertGenericToTable(GenericData[] gData) {
        int size = 0;

        for(int i = 0; i < gData.length; i++) {
            if (gData[i] == null) {
                break;
            }
            size++;
        }

        TableData[] tData = new TableData[size];

        for (int i = 0; i < tData.length; i++) {
            tData[i] = GenericData.convertTableData(gData[i]);
        }

        return tData;
    }
}
