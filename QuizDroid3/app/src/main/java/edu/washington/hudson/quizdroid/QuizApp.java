package edu.washington.hudson.quizdroid;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class QuizApp extends Application implements TopicRepository {

    private static QuizApp instance;
    private static ArrayList<Topic> topicStore;
    private static int currentQuestionNum;
    private static int numCorrect;
    private int currentTopic;

    public static void initInstance() {
        if (instance == null) {
            instance = new QuizApp();
        }
    }

    public static QuizApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("QuizApp", "QuizApp created");

        topicStore = new ArrayList<Topic>();

        // Math Questions
        ArrayList<Quiz> questions = new ArrayList<Quiz>();
        questions.add(createQuestion("What is 1 + 1?","1","2","3","4",1));
        questions.add(createQuestion("Who was the famous mathematician that created the coordinate system?","Isaac Newton","Archimedes","Euchlid","René Descartes",3));
        createTopic("Math", "Asks simple math related questions", "Asks questions related to math including addition and famous mathematicians.", questions);

        // Physics Questions
        questions = new ArrayList<Quiz>();
        questions.add(createQuestion("What is the equation for individual net force on an object?","F = ma","P = mv^2","G = m/2","F = mv",0));
        questions.add(createQuestion("Why is it easier to float in salt water than fresh water?","Salt water is less dense than fresh water","Salt water is more dense than fresh water","Fresh water is less dense than salt water","Fresh water is more dense than salt walter",1));
        questions.add(createQuestion("What is the wire inside of a light bulb called?", "The Illuminator","The Brightwire","The Filament", "The Copperglow", 2));
        createTopic("Physics", "Asks simple physics related questions", "Asks questions related to physics including force and buoyancy", questions);

        // Marvel Super Heroes
        questions = new ArrayList<Quiz>();
        questions.add(createQuestion("How heavy is Mjolnir, Thor's hammer?","9001 kg","42.4 lbs","20.5 lbs","100.8 kg",1));
        questions.add(createQuestion("What is the name of Hulk's alter ego?","Bane Dorrance","Bruce Banner","Robert Banner","Robert Bruce Banner",3));
        questions.add(createQuestion("Who is the actor that played Captain America in Winter Soldier?","Chris Evans","Chris Pratt","Chris Rock","Chris Matthews",1));
        questions.add(createQuestion("What is Batman's real name?","Bruce Wane","Bruce the Shark","Bruce Banner","Batman is not a Marvel Superhero so I am not going to answer this question.",3));
        createTopic("Marvel Super Heroes","Asks simple Marvel related questions","Asks questions related to Marvel Super Heroes including Thor, Hulk, and Captain America.", questions);
        Log.i("QuizApp", "Topics added to store");
    }

    // other methods go here

    public String check() {
        return "Yeah";
    }

    public Quiz createQuestion(String question, String answerOne, String answerTwo, String answerThree, String answerFour, int correct) {
        ArrayList<String> answers = new ArrayList<String>();
        answers.add(answerOne);
        answers.add(answerTwo);
        answers.add(answerThree);
        answers.add(answerFour);
        Log.i("QuizApp","Within quizapp, correct is " + correct);
        return new Quiz(question, answers, correct);
    }

    @Override
    public void createTopic(String title, String shortDescription, String longDescription, ArrayList<Quiz> quizes) {
        topicStore.add(new Topic(title, shortDescription, longDescription, quizes));
    }

    @Override
    public Topic getTopic(int title) {
        return topicStore.get(title);
    }

    @Override
    public void updateTopic(String title) {
    }

    @Override
    public void deleteTopic(String title) {
    }

    public void incrementCurrentQuestionNum() {
        currentQuestionNum++;
    }

    public int getCurrentQuestionNum() {
        return currentQuestionNum;
    }

    public void incrementNumCorrect() {
        numCorrect++;
    }

    public int getNumCorrect() {
        return numCorrect;
    }

    public void quizDone() {
        currentQuestionNum = 0;
        numCorrect = 0;
    }

    public int getCurrentTopic() {
        return currentTopic;
    }

    public void setCurrentTopic(int currentTopic) {
        this.currentTopic = currentTopic;
    }
}
