package com.wxxkel001.genericapp.Manager.BinaryTree;

import com.wxxkel001.genericapp.Manager.GenericData;
import com.wxxkel001.genericapp.Manager.TableData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BinaryTreeManager {
    private BinarySearchTree<GenericData> dataStructure = new BinarySearchTree<>();

    public BinaryTreeManager(String textFile) {
        loadData(textFile);
    }

    public BinaryTreeManager() {

    }

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

    public TableData searchTerm(String term) {
        GenericData temp = new GenericData(term, null, -1);
        if (dataStructure.find(temp) == null) {
            return null;
        }
        return GenericData.convertTableData(dataStructure.find(temp).getData());
    }

    public String viewAll() {
        String temp = "";
        for(GenericData i: dataStructure.getAllData()) {
            temp += i.toString();
        }
        return temp;
    }

    public ArrayList<TableData> getTotalTableArray() {
        ArrayList<TableData> tableData = new ArrayList<>();
        for (GenericData i : dataStructure.getAllData()) {
            tableData.add(GenericData.convertTableData(i));
        }
        return tableData;
    }

    public void addItem(String term, String sentence, double score) {
        dataStructure.insertItem(new GenericData(term, sentence, score));
    }


}
