package com.tmfortm.shihuo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.beans.Channel;
import com.tmfortm.shihuo.beans.Hot_listing;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import static android.R.attr.tag;

public class ViewPagerActivity extends AppCompatActivity {

    private WebView mWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        initView();
        EventBus.getDefault().register(this);
    }
    private void initView() {
        mWeb = (WebView) findViewById(R.id.view_pager_web);
    }
  @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void getMessage(String url){
        mWeb.setWebChromeClient(new WebChromeClient());
        mWeb.setWebViewClient(new WebViewClient());
        mWeb.getSettings().setAppCacheEnabled(true);
        mWeb.getSettings().setDomStorageEnabled(true);
        mWeb.getSettings().setDatabaseEnabled(true);
        mWeb.getSettings().setLoadWithOverviewMode(true);
        mWeb.getSettings().setUseWideViewPort(true);
               Log.d("flag", "----------->得到的数据为getMessage:String url:::" +url);
                //mWeb.loadDataWithBaseURL(null,url,"text/html","utf-8",null);
                mWeb.loadUrl(url);

    }
    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void getMessage(Hot_listing hot_listing){
        mWeb.setWebChromeClient(new WebChromeClient());
        mWeb.setWebViewClient(new WebViewClient());
        mWeb.getSettings().setAppCacheEnabled(true);
        mWeb.getSettings().setDomStorageEnabled(true);
        mWeb.getSettings().setDatabaseEnabled(true);
        mWeb.getSettings().setLoadWithOverviewMode(true);
        mWeb.getSettings().setUseWideViewPort(true);

            Log.d("flag", "----------->得到的数据为getMessage:hot_listing.getUrl():::" +hot_listing.getUrl());
            //mWeb.loadDataWithBaseURL(null,hot_listing.getUrl(),"text/html","utf-8",null);
            mWeb.loadUrl(hot_listing.getUrl());


    }
   @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void getMessage(Channel channel){
        mWeb.setWebChromeClient(new WebChromeClient());
        mWeb.setWebViewClient(new WebViewClient());
        mWeb.getSettings().setAppCacheEnabled(true);
        mWeb.getSettings().setDomStorageEnabled(true);
        mWeb.getSettings().setDatabaseEnabled(true);
        mWeb.getSettings().setLoadWithOverviewMode(true);
        mWeb.getSettings().setUseWideViewPort(true);

                //mWeb.loadDataWithBaseURL(null,channel.getHref(),"text/html","utf-8",null);
                Log.d("flag", "----------->得到的数据为getMessage:channel.getHref()" +channel.getHref());
                mWeb.loadUrl(channel.getHref());


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
