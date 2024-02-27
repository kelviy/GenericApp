package com.example.genericapp.Application.Array;

import com.example.genericapp.Application.GenericData;
import com.example.genericapp.GUI.TableData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GenericArrayManager {

    private GenericData[] data;
    private int count = 0;
    public GenericArrayManager(String textFile) {
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

    private int linearSearch(String term) {
        for(int i = 0; i < count; i++) {
            if(data[i].equals(term)) {
                return i;
            }
        }
        return -1;
    }

    private GenericData searchTerm(String term) {
        int index = linearSearch(term);
        return data[index];
    }

    private GenericData[] searchStatement(String statement) {
        GenericData[] temp = new GenericData[100_000];
        int foundCount = 0;

        for(int i = 0; i < count; i++) {
            if (data[i].getSentence().contains(statement)) {
                temp[foundCount] = data[i];
                foundCount++;
            }
        }

        GenericData[] matched = new GenericData[foundCount];
        for (int i = 0; i < matched.length; i++) {
            matched[i] = temp[i];
        }
        return matched;
    }

    public TableData[] getTotalTableArray() {
        TableData[] tableData = new TableData[count];
        for(int i = 0; i < count; i++) {
            tableData[i] = GenericData.convertTableData(data[i]);
        }
        return tableData;
    }

    public TableData getSearchItem(String term) {
        GenericData searchItem = searchTerm(term);
        return GenericData.convertTableData(searchItem);
    }

    public TableData[] getStatementItems(String statement) {
        GenericData[] matched = searchStatement(statement);
        TableData[] returnData = new TableData[matched.length];

        for(int i = 0; i < matched.length; i++) {
            if (matched[i] != null) {
                returnData[i] = new TableData(matched[i].getTerm(), matched[i].getSentence(), matched[i].getScore());
            }
        }
        return returnData;
    }

    public TableData[] convertGenericToTable(GenericData[] gData) {
        int size = 0;

        for(int i = 0; i < gData.length; i++) {
            if (gData[i] == null) {
                break;
            }
            size++;
        }

        TableData[] tData = new TableData[size];

        for (int i = 0; i < gData.length; i++) {
            tData[i] = GenericData.convertTableData(gData[i]);
        }

        return tData;
    }
}
