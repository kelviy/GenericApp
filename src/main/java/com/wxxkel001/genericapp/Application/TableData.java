package com.wxxkel001.genericapp.Application;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

// Wrapper Class to display data in GUI
public class TableData {
        private final SimpleStringProperty term;
        private final SimpleStringProperty statement;
        private final SimpleDoubleProperty score;

        public TableData(String term, String statement, double score) {
            this.term = new SimpleStringProperty(term);
            this.statement = new SimpleStringProperty(statement);
            this.score = new SimpleDoubleProperty(score);
        }

    // Converts an inputted Generic Array and outputs a TableData Array without null elements trailing at the end
    public static TableData[] convertGenericToTable(GenericData[] gData) {
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

    public String getTerm() {
        return term.get();
    }

    public SimpleStringProperty termProperty() {
        return term;
    }

    public void setTerm(String term) {
        this.term.set(term);
    }

    public String getStatement() {
        return statement.get();
    }

    public SimpleStringProperty statementProperty() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement.set(statement);
    }

    public double getScore() {
        return score.get();
    }

    public SimpleDoubleProperty scoreProperty() {
        return score;
    }

    public void setScore(double score) {
        this.score.set(score);
    }
}
