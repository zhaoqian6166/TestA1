package com.bawei.texta2;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by 赵倩 on 2017/5/3.
 * <p/>
 * 类的用途：
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
    }
}
