package com.tmfortm.shihuo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.fastjson.JSON;
import com.tmfortm.shihuo.R;
import com.tmfortm.shihuo.beans.WebHomepage;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class WebActivityTwo extends AppCompatActivity {

    private WebView mWebView;
    private String path = "http://www.shihuo.cn/app2/getNewsDetail?channel=wandoujia&clientCode=869336021137995&id=245970&platform=android&timestamp=1481784497400&token=6261dd0d1e46e61a9a85787b844172ba&v=4.2.2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_two);

        initView();
       initWebView();
        initData();
    }

    private void initData() {
        RequestParams entity = new RequestParams(path);
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                WebHomepage wh = JSON.parseObject(result,WebHomepage.class);
                mWebView.loadDataWithBaseURL(null,wh.getData().getContent()+wh.getData().getText(),"text/html","utf-8",null);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void initWebView() {
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setDatabaseEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.wv_web_two);
    }
}
