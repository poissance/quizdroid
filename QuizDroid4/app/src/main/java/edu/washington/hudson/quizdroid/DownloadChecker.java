package edu.washington.hudson.quizdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class DownloadChecker extends BroadcastReceiver {
    public DownloadChecker() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("DownloadChecker", "URL received. Beginning check of " + intent.getStringExtra("url"));
        Toast.makeText(context, intent.getStringExtra("url"), Toast.LENGTH_SHORT).show();
    }
}
