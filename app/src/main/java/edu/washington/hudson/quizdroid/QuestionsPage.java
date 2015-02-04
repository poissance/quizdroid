package edu.washington.hudson.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class QuestionsPage extends ActionBarActivity {


    private ArrayList<String> questions;
    private ArrayList<String> answers;
    private int questionNumValue;
    private RadioGroup radioGroupId;
    private String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Log.i("QuestionsPage","savedInstanceState is null");
        }
        setContentView(R.layout.activity_questions_page);
        if (savedInstanceState != null) {
            questions = (ArrayList<String>) savedInstanceState.getStringArrayList("questions");
            answers = (ArrayList<String>) savedInstanceState.getStringArrayList("Answers");
            questionNumValue = savedInstanceState.getInt("QuestionNumber");
            flag = savedInstanceState.getString("Flag");
        } else {
            flag = String.valueOf(getIntent().getStringExtra("Flag"));
            Log.i("QuestionsPage","Flag is " + flag);
            questions = new ArrayList<String>();
            answers = new ArrayList<String>();
            questionNumValue = 0;
            if (flag.equals("Math")) {
                Log.i("QuestionsPage","Yay Math!");
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
            if (flag.equals(("Physics"))) {
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
            if (flag.equals("Marvel")) {
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
        // Generating question and answers
        nextQuestion();
        if (savedInstanceState != null) {
            savedInstanceState.putSerializable("Questions", questions);
            savedInstanceState.putSerializable("Answers", answers);
            savedInstanceState.putInt("QuestionNumber", questionNumValue);
            savedInstanceState.putString("Flag",flag);
        }
        // Questions and answers are now generated.
        Button b = (Button) findViewById(R.id.submit);
        radioGroupId = (RadioGroup) findViewById(R.id.radioGroup);
        b.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if (questions == null) {
                    answers = null;
                    questions = null;
                    questionNumValue = 0;
                    Log.i("QuestionsPage", "Heading to QuizMain");
                    Intent toQuizMain = new Intent(QuestionsPage.this, QuizMain.class);
                    startActivity(toQuizMain);
                } else {
                        Log.i("QuestionsPage","Questions 0 is " + questions.get(0));
                    Intent toResultsPage = new Intent(QuestionsPage.this, Results.class);
                    startActivity(toResultsPage);
                    finish();
                }
            }
        });
        /*
        Intent toQuizMain = new Intent(QuestionsPage.this, QuizMain.class);
        startActivity(toQuizMain);
        finish();
        */
    }

    private void nextQuestion() {
        ArrayList<View> choices = new ArrayList<View>();
        choices.add(findViewById(R.id.choice1));
        choices.add(findViewById(R.id.choice2));
        choices.add(findViewById(R.id.choice3));
        choices.add(findViewById(R.id.choice4));
        Log.i("QuestionsPage","Choosing things");
        Log.i("QuestionsPage","questionNumValue is " + questionNumValue);
        String correct = answers.get(0);
        questionNumValue++;
        TextView temp = (TextView) findViewById(R.id.questionNumber);
        temp.setText("Question " + questionNumValue);
        temp = (TextView) findViewById(R.id.question);
        temp.setText(questions.remove(0));
        Log.i("QuestionsPage","Entering Loop");
        for (int i = 0; i < 4; i++) {
            Log.i("QuestionsPage","i is " + i);
            Log.i("QuestionsPage","Inside loop " + (i + 1) + " times");
            Random placer = new Random();
            int randInt = placer.nextInt(4 - i);
            temp = (TextView) choices.remove(randInt);
            Log.i("QuestionsPage","Adding answer: " + answers.get(randInt));
            temp.setText(answers.remove(0));
        }
        Log.i("QuestionsPage","Loop Ended");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_questions_page, menu);
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
}
