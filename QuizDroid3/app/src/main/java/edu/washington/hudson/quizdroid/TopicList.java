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


public class TopicList extends ActionBarActivity implements TopicRepository {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_list);
        initSingletons();
    }

    private void initSingletons() {
        QuizApp.initInstance();
        TextView temp = (TextView) findViewById(R.id.topic1);
        temp.setText(QuizApp.getInstance().getTopic(0).getTitle());
        temp = (TextView) findViewById(R.id.shortDescription1);
        temp.setText(QuizApp.getInstance().getTopic(0).getShortDescription());

        temp = (TextView) findViewById(R.id.topic2);
        temp.setText(QuizApp.getInstance().getTopic(1).getTitle());
        temp = (TextView) findViewById(R.id.shortDescription2);
        temp.setText(QuizApp.getInstance().getTopic(1).getShortDescription());

        temp = (TextView) findViewById(R.id.topic3);
        temp.setText(QuizApp.getInstance().getTopic(2).getTitle());
        temp = (TextView) findViewById(R.id.shortDescription3);
        temp.setText(QuizApp.getInstance().getTopic(2).getShortDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topic_list, menu);
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
        Intent toTopicsOverview = new Intent(TopicList.this, TopicOverview.class);
        switch (v.getId()) {
            case R.id.topic1:
                QuizApp.getInstance().setCurrentTopic(0);
                break;
            case R.id.topic2:
                QuizApp.getInstance().setCurrentTopic(1);
                break;
            case R.id.topic3:
                QuizApp.getInstance().setCurrentTopic(2);
                break;
        }
        startActivity(toTopicsOverview);
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
