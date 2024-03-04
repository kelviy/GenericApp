package com.wxxkel001.genericapp.Manager;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Wrapper Class to display data in GUI. This class would need to be converted from a GenericData using the convertTableData() method in GenericData Class.
 * @author Kelvin Wei
 */
public class TableData {
        private final SimpleStringProperty term;
        private final SimpleStringProperty statement;
        private final SimpleDoubleProperty score;

    /**
     * Constructor that creates the object from given parameters
     * @param term
     * @param statement
     * @param score
     */
        public TableData(String term, String statement, double score) {
            this.term = new SimpleStringProperty(term);
            this.statement = new SimpleStringProperty(statement);
            this.score = new SimpleDoubleProperty(score);
        }

    /**
     *  Converts an inputted Generic Array and outputs a TableData Array without null elements trailing at the end. It will trim the null elements trailing
     * @param gData Array of GenericData (Type: GenericData[]). Array can have trailing null elements.
     * @return a TableData[] array (Type: TableData[]). Trimmed null trailing elements
     */
    public static TableData[] convertGenericToTable(GenericData[] gData) {
        int size = 0;

        // checks the size excluding null elements
        for(int i = 0; i < gData.length; i++) {
            if (gData[i] == null) {
                break;
            }
            size++;
        }

        TableData[] tData = new TableData[size];

        // converts to a TableData[] with size calculated
        for (int i = 0; i < tData.length; i++) {
            tData[i] = GenericData.convertTableData(gData[i]);
        }

        return tData;
    }

    // getters and setters
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
