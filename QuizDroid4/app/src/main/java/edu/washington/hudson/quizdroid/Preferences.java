package edu.washington.hudson.quizdroid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Preferences extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    SharedPreferences getPrefs;
    boolean started;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.activity_preferences);
        getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String url = getPrefs.getString("url", "No URL");
        Log.i("Preferences", "URL is: " + url);
        EditTextPreference pref = (EditTextPreference) findPreference("url");
        pref.setSummary(url);
        String interval = getPrefs.getString("interval", "");
        pref = (EditTextPreference) findPreference("interval");
        pref.setSummary("" + interval);
        getPrefs.registerOnSharedPreferenceChangeListener(this);
        Log.i("Preferences", "Welcome to the preference menu");
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

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference pref = findPreference(key);
        if (pref instanceof EditTextPreference) {
            EditTextPreference textPref = (EditTextPreference) pref;
            pref.setSummary(textPref.getText());
            String url = getPrefs.getString("url", "No URL");
            String interval = getPrefs.getString("interval","");
            Log.i("Preferences",url);
            Log.i("Preferences", interval);
            if (!url.equals("No URL") && !interval.equals("")) {
                Log.i("Preferences","Hurray");

                // stop current intent
                stopBroadcaster();

                //start new intent
                AlarmManager alarmManager = (AlarmManager) Preferences.this.getSystemService(ALARM_SERVICE);
                Intent startTrigger = new Intent(Preferences.this,DownloadChecker.class);
                startTrigger.putExtra("url",url);
                startTrigger.putExtra("interval",interval);
                pendingIntent = PendingIntent.getBroadcast(Preferences.this, 0, startTrigger, 0);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),60 * 1000 * Integer.parseInt(interval),pendingIntent);
            } else {
                stopBroadcaster();
            }
        }
    }

    private void stopBroadcaster() {
        AlarmManager alarmManager=(AlarmManager) Preferences.this.getSystemService(ALARM_SERVICE);
        Intent stopTrigger = new Intent(Preferences.this,DownloadChecker.class);
        pendingIntent = PendingIntent.getBroadcast(Preferences.this, 0, stopTrigger, 0);
        pendingIntent.cancel();
        alarmManager.cancel(pendingIntent);
        Log.i("Awty","Stopping service");
    }
}
