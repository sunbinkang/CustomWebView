package com.example.base;

import android.app.Application;

/**
 * Created by BinKang on 2021/3/12.
 * Des :
 */
public class BaseApplication extends Application {
    public static Application sApplication;//application的context不会有内存泄漏

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;

    }
}
