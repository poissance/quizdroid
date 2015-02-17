package edu.washington.hudson.quizdroid;

import java.util.ArrayList;

/**
 * Created by Hudson on 2/15/2015.
 */
public interface TopicRepository {

    // create
    // Creates new topic. Takes a title, short description, long description, and an ArrayList of quizes.
    public void createTopic(String title, String shortDescription, String longDescription, ArrayList<Quiz> quizes);

    // read
    // Returns existing topic based on title.
    public Topic getTopic(int title);

    // update
    // Modifies existing topic based on title.
    public void updateTopic(String title);

    // delete
    // Deletes an existing topic based on title.
    public void deleteTopic(String title);
}
