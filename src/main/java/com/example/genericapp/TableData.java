package com.example.genericapp;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableData {
        private final SimpleStringProperty term;
        private final SimpleStringProperty statement;
        private final SimpleDoubleProperty score;

        public TableData(String term, String statement, double score) {
            this.term = new SimpleStringProperty(term);
            this.statement = new SimpleStringProperty(statement);
            this.score = new SimpleDoubleProperty(score);
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
