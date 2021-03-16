package com.example.webview.mainprocess;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * Created by BinKang on 2021/3/15.
 * Des :
 */
public class MainProcessCommandService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return MainProcessCommandsManager.getInstance();
    }
}
