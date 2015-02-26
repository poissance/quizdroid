package edu.washington.hudson.quizdroid;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Hudson on 2/15/2015.
 */
public class Topic implements Serializable {

    // title
    private String title;
    // short description
    private String shortDescription;
    // long description
    private String longDescription;
    // collection of question objects
    private ArrayList<Quiz> quizes;

    public Topic(String title, String shortDescription, String longDescription, ArrayList<Quiz> quizes) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.quizes = quizes;
    }

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public ArrayList<Quiz> getQuizes() {
        return quizes;
    }

    public String toString() {
        return "Title: " + title + " ShortDescription: " + shortDescription + " LongDescription: " + longDescription + " Questions: " + quizes.toString();
    }
}
