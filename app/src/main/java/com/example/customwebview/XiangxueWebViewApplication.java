package com.example.customwebview;

import com.example.base.BaseApplication;
import com.example.base.loadsir.CustomCallback;
import com.example.base.loadsir.EmptyCallback;
import com.example.base.loadsir.ErrorCallback;
import com.example.base.loadsir.LoadingCallback;
import com.example.base.loadsir.TimeoutCallback;
import com.kingja.loadsir.core.LoadSir;

/**
 * Created by BinKang on 2021/3/12.
 * Des :
 */
public class XiangxueWebViewApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }
}
