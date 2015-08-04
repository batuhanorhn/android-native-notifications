package com.example.yazilimstajyer1.bildirimler;

import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.webkit.WebView;

import com.parse.ParseAnalytics;
import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

/*
 * Created by yazilim.stajyer1 on 09.07.2015.
 */
public class Receiver extends ParsePushBroadcastReceiver {

    @Override
    protected void onPushOpen(Context context, Intent intent) {
        super.onPushOpen(context, intent);
        ParseAnalytics.trackAppOpenedInBackground(intent);
        // Anlik Bildirim acildiginda MainActivity'imizi baslatiyoruz.

        Log.d("msg_info", "onPushOpen");

        String uriString = null;
        String alertString =null;
        try {
            Log.d("msg_info", "icerdeyim");

            JSONObject pushData = new JSONObject(intent.getStringExtra("com.parse.Data"));
            JSONObject alertdata = new JSONObject(intent.getStringExtra("com.parse.Data"));
            uriString = pushData.optString("uri");
            alertString=alertdata.optString("alert");
        } catch (JSONException e) {
            Log.v("com.parse.ParsePushReceiver", "Unexpected JSONException when receiving push data: ", e);
        }
        Class<? extends Activity> cls = getActivity(context, intent);
        Intent activityIntent;
        if (uriString != null  && alertString != null ) {
            activityIntent = new Intent(context, MainActivity.class);
            activityIntent.putExtra("URI_STRING", uriString);
            activityIntent.putExtra("ALERT_STRING", alertString);
        } else {
            activityIntent = new Intent(context, MainActivity.class);
        }
        activityIntent.putExtras(intent.getExtras());
        if (Build.VERSION.SDK_INT >= 9) {
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(cls);
            stackBuilder.addNextIntent(activityIntent);
            stackBuilder.startActivities();
        } else {
            activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(activityIntent);
        }
    }
}



























      /*try {
            Log.d("msg_info", "basarilii!!!");
          JSONObject ahmet = new JSONObject(intent.getExtras().getString("url"));

      } catch (JSONException e) {
          e.printStackTrace();
      }
        Log.d("msg_info", "webviewdeyim");
            WebView webview = null;
            //assert webview != null;
            webview.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    // create your intent here
                    //HttpURLConnection.setFollowRedirects(true);
                    super.onPageFinished(view, url);
                    anasayfa.sayfaAc(url);
                }
            });
    };};*/

