package edu.washington.hudson.quizdroid;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class TopicList extends ActionBarActivity implements TopicRepository {

    private int SETTINGS_RESULT = 1;
    SharedPreferences getPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_list);
        if (getIntent().getBooleanExtra("FAIL", false)) {
            // prompt user to retry or quit
            Log.i("DownloadChecker","Download failed");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            AlertDialog dialog;
            builder.setMessage("Download failed. Retry?");
            builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    AlarmManager alarmManager = (AlarmManager) TopicList.this.getSystemService(ALARM_SERVICE);
                    Intent stopTrigger = new Intent(TopicList.this, DownloadChecker.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(TopicList.this, 0, stopTrigger, 0);
                    pendingIntent.cancel();
                    alarmManager.cancel(pendingIntent);
                    Log.i("TopicList", "Stopping service");
                    finish();
                }
            });
            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent toDownloadChecker = new Intent();
                    getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                    String url = getPrefs.getString("url", "No URL");
                    toDownloadChecker.putExtra("url",url);
                    // this needs to go to download checker to start download
                }
            });
            dialog = builder.create();
            dialog.show();
        }
        initSingletons();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog;
        if (isAirplaneModeOn(this)) {
            Log.i("Preferences", "Airplane mode is on!");

            builder.setMessage("Airplane mode is on. Turn it off?");
            builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                }
            });
            dialog = builder.create();
            dialog.show();
            // Create pop up menu asking if want to turn off airplane mode.
            // if yes, go to settings
        } else if (!hasInternet(this)) {
            builder = new AlertDialog.Builder(this);
            builder.setMessage("You currently have no internet access!");
            dialog = builder.create();
            dialog.show();
        } else {
            // internet is on
            Log.i("Preferences", "Internet is on!" + hasInternet(this));
        }
    }


    private static boolean isAirplaneModeOn(Context context) {
        return Settings.Global.getInt(context.getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
    }

    private static boolean hasInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
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
        if (id == R.id.action_preferences) {
            Log.i("TopicList", "Preferences clicked!");
            Intent toPreferences = new Intent(TopicList.this, Preferences.class);
            startActivityForResult(toPreferences, SETTINGS_RESULT);
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
    public void onResume() {
        super.onResume();
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
