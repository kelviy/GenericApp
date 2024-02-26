package com.example.genericapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class GenericsKbArrayApp {

    private GenericData[] data;
    private int count = 0;
    public GenericsKbArrayApp(String textFile) {
        data = loadData(textFile);
    }

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

    public String viewData() {
        String temp = "";
        for(int i = 0; i < count; i++) {
            temp += data[i].toString() + "\n";
        }
        return temp;
    }

    public int linearSearch(String term) {
        for(int i = 0; i < count; i++) {
            if(data[i].equals(term)) {
                return i;
            }
        }
        return -1;
    }

    public String search(String term) {
        int index = linearSearch(term);
        return data[index].toString();
    }

    public String search(String term, String statement) {
        //TODO: find the term and return the confidence level
        return null;
    }

    public TableData[] getTableArray() {
        TableData[] tableData = new TableData[count];
        for(int i = 0; i < count; i++) {
            tableData[i] = new TableData(data[i].getTerm(), data[i].getSentence(), data[i].getScore());
        }
        return tableData;
    }
}
