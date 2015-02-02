package edu.washington.hudson.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class QuizMain extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_main);

    }

    public void onClick(View v) {
        Intent toTopicsOverview = new Intent(QuizMain.this, TopicsOverview.class);
        ArrayList<String> topicContent = new ArrayList<String>();
        switch (v.getId()) {
            case R.id.math:
                topicContent.add("Math");
                topicContent.add("This section is about math basics.");
                topicContent.add("2 Questions");
                Log.i("QuizMain", "Math clicked");
                break;
            case R.id.physics:
                topicContent.add("Physics");
                topicContent.add("This section is about physics basics.");
                topicContent.add("3 Questions");
                Log.i("QuizMain","Physics clicked");
                break;
            case R.id.heroes:
                topicContent.add("Marvel Super Heroes");
                topicContent.add("This section is about super hero basics.");
                topicContent.add("4 Questions");
                Log.i("QuizMain","Marvel clicked");
                break;
        }
        toTopicsOverview.putStringArrayListExtra("TopicContent", topicContent);
        startActivity(toTopicsOverview);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz_main, menu);
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
