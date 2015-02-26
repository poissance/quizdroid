package edu.washington.hudson.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class QuizGiver extends ActionBarActivity {

    private RadioGroup radioGroupId;
    private Quiz question;
    private String correct;
    private int correctInt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_giver);
        radioGroupId = (RadioGroup) findViewById(R.id.radioGroup);
        nextQuestion();
        }

    private void nextQuestion() {
        int topic = QuizApp.getInstance().getCurrentTopic();
        int currentQuestionNum = QuizApp.getInstance().getCurrentQuestionNum();
        if (currentQuestionNum < QuizApp.getInstance().getTopic(topic).getQuizes().size()) {
            question = QuizApp.getInstance().getTopic(topic).getQuizes().get(currentQuestionNum);
            correct = question.getAnswers().get(question.getCorrect());
            Log.i("QuizGiver", "Apparently the correct answer is " + correct);
            correctInt = question.getCorrect();
            TextView temp = (TextView) findViewById(R.id.submit);
            temp.setText("Submit");
            QuizApp.getInstance().incrementCurrentQuestionNum();
            temp = (TextView) findViewById(R.id.questionNumber);
            temp.setText("Question " + (currentQuestionNum + 1));
            temp = (TextView) findViewById(R.id.question);
            temp.setText(question.getQuestion());
            temp = (TextView) findViewById(R.id.choice1);
            temp.setText(question.getAnswers().get(0));
            temp = (TextView) findViewById(R.id.choice2);
            temp.setText(question.getAnswers().get(1));
            temp = (TextView) findViewById(R.id.choice3);
            temp.setText(question.getAnswers().get(2));
            temp = (TextView) findViewById(R.id.choice4);
            temp.setText(question.getAnswers().get(3));
            temp = (TextView) findViewById(R.id.submit);
        } else {
            Intent toTopicList = new Intent(QuizGiver.this,TopicList.class);
            startActivity(toTopicList);
            QuizApp.getInstance().quizDone();
            finish();
        }
    }


    public void quizOnClick(View v) {
        Log.i("QuizGiver","Button clicked");
        int radioButtonID = radioGroupId.getCheckedRadioButtonId();
        View radioButton = radioGroupId.findViewById(radioButtonID);
        int selected = radioGroupId.indexOfChild(radioButton);
        RadioButton button = (RadioButton) findViewById(selected);
        //nextQuestion();
        Intent toResults = new Intent(QuizGiver.this,QuizResults.class);
        toResults.putExtra("Selected",selected);
        Log.i("QuizGiver","Selected number is " + selected);
        toResults.putExtra("Correct",correct);
        toResults.putExtra("Chosen",question.getAnswers().get(selected));
        if (selected == correctInt) {
            QuizApp.getInstance().incrementNumCorrect();
        }
        startActivity(toResults);
        finish();
    }




    // get question
        /*
        TextView temp = (TextView) findViewById(R.id.questionNumber);
        temp.setText(QuizApp.getInstance().getTopic(0).getTitle());
        temp = (TextView) findViewById(R.id.shortDescription1);
        temp.setText(QuizApp.getInstance().getTopic(0).getShortDescription());
        */


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
}
