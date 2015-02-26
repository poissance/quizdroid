package edu.washington.hudson.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;


public class TopicOverview extends ActionBarActivity implements TopicRepository {

    private int topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topic = QuizApp.getInstance().getCurrentTopic();
        setContentView(R.layout.activity_topic_overview);
        TextView temp = (TextView) findViewById(R.id.topic_title);
        temp.setText(QuizApp.getInstance().getTopic(topic).getTitle());
        temp = (TextView) findViewById(R.id.topic_longDescription);
        temp.setText(QuizApp.getInstance().getTopic(topic).getLongDescription());
        temp = (TextView) findViewById(R.id.topic_num_questions);
        temp.setText("" + QuizApp.getInstance().getTopic(topic).getQuizes().size() + " Questions");
        Log.i("TopicOverview",QuizApp.getInstance().getTopic(topic).getQuizes().toString());
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topic_overview, menu);
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
        Log.i("TopicOverview", "Button clicked");
        Intent toQuizGiver = new Intent(TopicOverview.this, QuizGiver.class);
        toQuizGiver.putExtra("topic", topic);
        startActivity(toQuizGiver);
        finish();
    }

    @Override
    public void createTopic(String title, String shortDescription, String longDescription, ArrayList<Quiz> quizes) {

    }

    @Override
    public Topic getTopic(int title) {
        return null;
    }

    @Override
    public void updateTopic(String title) {

    }

    @Override
    public void deleteTopic(String title) {

    }
}
