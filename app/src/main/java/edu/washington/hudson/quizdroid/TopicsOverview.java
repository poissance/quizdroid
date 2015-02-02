package edu.washington.hudson.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class TopicsOverview extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics_overview);

        //set page contents
        Intent setContent = getIntent();
        ArrayList<String> topicContent = setContent.getStringArrayListExtra("TopicContent");
        TextView temp = (TextView) findViewById(R.id.topic_title);
        temp.setText(topicContent.get(0));
        temp = (TextView) findViewById(R.id.topic_description);
        temp.setText(topicContent.get(1));
        temp = (TextView) findViewById(R.id.topic_num_questions);
        temp.setText(topicContent.get(2));


        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //math
                //physics
                //marvel
                Intent nextActivity = new Intent(MainActivity.this, MainActivity2.class);
                nextActivity.putExtra();
                startActivity(nextActivity);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topics_overview, menu);
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
