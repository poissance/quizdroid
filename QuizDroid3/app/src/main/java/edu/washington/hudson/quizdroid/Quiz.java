package edu.washington.hudson.quizdroid;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Stuff goes here
 */

// Quiz is a a domain object meaning it is part of the business logic. It also resides within the M of MVC.
public class Quiz implements Serializable {

    // question text
    private String question;
    // four answers
    private ArrayList<String> answers;
    // integer for correct
    private int correct;

    public Quiz(String question, ArrayList<String> answers, int correct) {
        this.question = question;
        this.answers = answers;
        this.correct = correct;
        Log.i("Quiz","The correct answer position is " + correct);
    }


    // Not sure if these methods are necessary...
    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public int getCorrect() {
        return correct;
    }

    public String toString() {
        return "Question: " + question + " Answers " + answers.toString() + " Correct is " + correct;
    }
}
