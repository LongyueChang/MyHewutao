package com.tmfortm.shihuo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tmfortm.shihuo.R;

public class DetailMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_message);
        Intent intent = getIntent();
        String href = intent.getStringExtra("href");
        WebView webView= (WebView) findViewById(R.id.findDetail);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        Log.d("flag", "----------->得到的数据为getMessage:String url:::" +href);
        //mWeb.loadDataWithBaseURL(null,url,"text/html","utf-8",null);
        webView.loadUrl(href);
    }
}
