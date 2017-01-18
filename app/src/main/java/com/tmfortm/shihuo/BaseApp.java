package com.tmfortm.shihuo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.xutils.x;

/**
 * Created by Administrator on 2016/12/12 0012.
 */

public class BaseApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
        Fresco.initialize(this);
    }
}
