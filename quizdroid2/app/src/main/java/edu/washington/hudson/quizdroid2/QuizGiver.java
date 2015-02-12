package edu.washington.hudson.quizdroid2;


import android.content.Intent;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class QuizGiver extends ActionBarActivity{

    private static ArrayList<String> topicContent;
    private static ArrayList<String> questions;
    private static ArrayList<String> answers;
    private static int questionNumValue;
    private static String currentQuestion;
    private static int currentQuestionNumber;
    private static Overview overview;
    private static Answers answer;
    private static int numCorrect;
    private static String correct;
    private static String selectedAnswer;

    public static int getCurrentQuestionNumber() {
        return currentQuestionNumber;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        numCorrect = 0;
        currentQuestionNumber = 1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_giver);
        if (savedInstanceState == null) {
        }
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the container with the new fragment
        overview = new Overview();
        answer = new Answers();
        ft.replace(R.id.quiz_giver, overview);
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Execute the changes specified
        ft.commit();

        Log.i("QuizGiver", "Something should have happened by now");
        Intent setContent = getIntent();
        topicContent = setContent.getStringArrayListExtra("TopicContent");
        String flag = topicContent.get(0);
        questions = new ArrayList<String>();
        answers = new ArrayList<String>();
        questionNumValue = 0;
        if (flag.equals("Math")) {
            Log.i("QuizGiver", "Math was clicked");
            questions.add("What is 1 + 1?");
            answers.add("2");
            answers.add("1");
            answers.add("3");
            answers.add("4");
            questions.add("Who was the famous mathematician that created the coordinate system?");
            answers.add("René Descartes");
            answers.add("Isaac Newton");
            answers.add("Archimedes");
            answers.add("Euchlid");
        }
        if (flag.equals("Physics")) {
            Log.i("QuizGiver", "Physics was clicked");
            questions.add("What is the equation for individual net force on an object?");
            answers.add("F = ma");
            answers.add("P = mv^2");
            answers.add("G = m/2");
            answers.add("F = mv");
            questions.add("Why is it easier to float in salt water than fresh water?");
            answers.add("Salt water is more dense than fresh water");
            answers.add("Salt water is less dense than fresh water");
            answers.add("Fresh water is more dense than salt walter");
            answers.add("Fresh water is less dense than salt water");
            questions.add("What is the wire inside of a light bulb called?");
            answers.add("The Filament");
            answers.add("The Brightwire");
            answers.add("The Illuminator");
            answers.add("The Copperglow");
        }
        if (flag.equals("Marvel Super Heroes")) {
            Log.i("QuizGiver", "Marvel Super Heroes was clicked");
            questions.add("How heavy is Mjolnir, Thor's hammer?");
            answers.add("42.4 lbs");
            answers.add("100.8 kg");
            answers.add("20.5 lbs");
            answers.add("9001 kg");
            questions.add("What is the name of Hulk's alter ego?");
            answers.add("Robert Bruce Banner");
            answers.add("Robert Banner");
            answers.add("Bruce Banner");
            answers.add("Bane Dorrance");
            questions.add("Who is the actor that played Captain America in Winter Soldier?");
            answers.add("Chris Evans");
            answers.add("Chris Pratt");
            answers.add("Chris Rock");
            answers.add("Chris Matthews");
            questions.add("What is Batman's real name?");
            answers.add("Batman is not a Marvel Superhero so I am not going to answer this question.");
            answers.add("Bruce Wane");
            answers.add("Bruce the Shark");
            answers.add("Bruce Banner");
        }
    }

    @Override
     protected void onStart() {
        super.onStart();
        Log.i("QuizGiver", "onStart called");
        View view = overview.getView();
        TextView temp = (TextView) view.findViewById(R.id.topic_title);
        temp.setText(topicContent.get(0));
        temp = (TextView) findViewById(R.id.topic_description);
        temp.setText(topicContent.get(1));
        temp = (TextView) findViewById(R.id.topic_num_questions);
        temp.setText(topicContent.get(2));
        Button btn = (Button) view.findViewById(R.id.buttonf1);
        Questions question = new Questions();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("QuizGiver", "onStart called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz_giver, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonf1:
                Log.i("Overview", "Overview Test clicked");
                //go to question fragment
               /* FragmentTransaction replace = transaction.replace(R.id.quiz_giver, QuizGiver.getQuestion());
                transaction.addToBackStack(null);
                transaction.commit();
                */
                break;
            case R.id.submit:

        }
    }

    public static String getQuestion() {
        return questions.remove(0);
    }

    public static ArrayList<String> getChoices() {
        correct = answers.get(0);
        ArrayList<String> choices = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            choices.add(answers.remove(0));
        }
        return choices;
    }

    public static boolean isLast() {
        Log.i("QuizGiver","Is Last? " + questions.isEmpty());
        return questions.isEmpty();
    }

    public static int getNumCorrect() {
        return numCorrect;
    }

    public static String getCorrect() {
        return correct;
    }


    public static void incrementCorrect() {
        numCorrect++;
        Log.i("QuizGiver","Correct incremented to " + numCorrect);
    }

    public static void setSelected(String selected){
        selectedAnswer = selected;
    }

    public static String getSelected(){
        return selectedAnswer;
    }

    public static void reset() {
        Log.i("QuizGiver", "Resetting information");
        topicContent = null;
        questions = null;
        answers = null;
        questionNumValue = 0;
        currentQuestionNumber = 0;
        currentQuestion = null;
        numCorrect = 0;
        correct = null;
        selectedAnswer = null;
    }

    public static void incrementCurrentQuestionNum() {
        currentQuestionNumber++;
    }
}
