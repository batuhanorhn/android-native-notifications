package com.example.yazilimstajyer1.bildirimler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.yazilimstajyer1.bildirimler.R;



public class MainActivity extends Activity {
    public WebView webView;
    public TextView textview;
    public TextView textview2;
    private String uri;
    private String alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getExtras() != null && getIntent().getExtras().getString("URI_STRING", "ALERT_STRING") != null) {
            uri = getIntent().getExtras().getString("URI_STRING");
            alert = getIntent().getExtras().getString("ALERT_STRING");
            textview = (TextView) findViewById(R.id.textview);
            textview.setText(uri);
            textview2 = (TextView) findViewById(R.id.textview2);
            textview2.setText(alert);
            webView = (WebView) findViewById(R.id.webview);
            webView.setWebViewClient(new WebViewClient());
            //webView.getSettings().setJavaScriptEnabled(true);
            Log.d("msg_info", "haber yükleniyor");
            webView.loadUrl(uri);
        } else {
            // URI null, hata mesaji goster.
        }
    }
}