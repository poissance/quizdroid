package edu.washington.hudson.quizdroid;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
import android.app.DownloadManager.Query;

import java.io.File;

public class DownloadChecker extends BroadcastReceiver {
    //private long enqueue;
    private DownloadManager downloadManager;
    private long downloadReference;
    private String url;


    public DownloadChecker() {
    }

    public void onCreate() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        context.getApplicationContext().registerReceiver(receiver, new IntentFilter(
                DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        url = intent.getStringExtra("url");
        Log.i("DownloadChecker", "URL received. Beginning check of " + intent.getStringExtra("url"));
        Toast.makeText(context, intent.getStringExtra("url"), Toast.LENGTH_SHORT).show();
        startDownload(context);

    }


    // check for download complete
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context context, final Intent intent) {
            String action = intent.getAction();
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                Query query = new Query();
                Cursor c = downloadManager.query(query);
                c.moveToFirst();
                int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
                if (status == DownloadManager.STATUS_FAILED) {
                    Log.i("DownloadChecker","Yup yup");
                    Intent toTopicList = new Intent(context, TopicList.class);
                    toTopicList.putExtra("FAIL",true);
                    toTopicList.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(toTopicList);
                }
            }
        }
    };

    private void startDownload(Context context) {
        File current = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/quizdata.json");
        if (current.exists()) {
            current.delete();
        }
        downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri download = Uri.parse(url); //"http://students.washington.edu/poisshud/info498/quizdata.json"
        DownloadManager.Request request = new DownloadManager.Request(download);

        //Enqueue a new download and same the referenceId
        //Restrict the types of networks over which this download may proceed.
        downloadManager.remove(downloadReference);
        downloadReference = downloadManager.enqueue(request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "quizdata.json"));
    }


}
