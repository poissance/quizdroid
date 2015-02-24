package edu.washington.hudson.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class QuizResults extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String correct = getIntent().getStringExtra("Correct");
        String chosen = getIntent().getStringExtra("Chosen");
        setContentView(R.layout.activity_quiz_results);
        TextView temp = (TextView) findViewById(R.id.numCorrect);
        temp.setText("You have " + (QuizApp.getInstance().getNumCorrect() + " out of "));
        temp = (TextView) findViewById(R.id.numQuestions);
        temp.setText("" + (QuizApp.getInstance().getCurrentQuestionNum() + " correct"));
        temp = (TextView) findViewById(R.id.your_answer);
        temp.setText("Your answer: " + chosen);
        temp = (TextView) findViewById(R.id.answerIs);
        temp.setText("The correct answer is: " + correct);
        int currentTopic = QuizApp.getInstance().getCurrentTopic();
        if (QuizApp.getInstance().getCurrentQuestionNum() == QuizApp.getInstance().getTopic(currentTopic).getQuizes().size()) {
            temp = (TextView) findViewById(R.id.next);
            temp.setText("Finish");
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz_results, menu);
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

    public void resultsOnClick(View v) {
        Intent toQuizGiver = new Intent(QuizResults.this,QuizGiver.class);

        startActivity(toQuizGiver);
        finish();
    }
}
