package com.wxxkel001.genericapp.Application;

// Class that stores data for each object

/**
 * Class that stores the data for the knowledge base.
 * It stores the term, sentence and score
 */
public class GenericData implements Comparable<GenericData> {
    private String term;
    private String sentence;
    private double score;

    /**
     * Constructor that creates the object
     * @param term
     * @param sentence
     * @param score
     */
    public GenericData(String term, String sentence, double score) {
        this.term = term;
        this.sentence = sentence;
        this.score = score;
    }

    /**
     * Converts a GenericData to a TableData for the GUI
     * @param convertData GenericData to be converted
     * @return TableData that has been converted
     */
    public static TableData convertTableData(GenericData convertData) {
        return new TableData(convertData.getTerm(), convertData.getSentence(), convertData.getScore());
    }

    /**
     * Checks if it is equal to the term
     * @param term
     * @return
     */
    public boolean equals(String term) {
        return this.term.equals(term);
    }

    /**
     * Compare to method that uses the String.compareTo() method
     * @param other the object to be compared.
     * @return
     */
    public int compareTo(GenericData other) {
        return this.term.compareToIgnoreCase(other.term);
    }

    /**
     * To String method that prints the data stored
     * @return returns a String representation of data in object
     */
    public String toString() {
        return term + "\t | \t" + sentence + " | \t" + score;
    }

    //getters and setters
    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

}
