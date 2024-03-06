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
