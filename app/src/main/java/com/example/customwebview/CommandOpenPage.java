package com.example.customwebview;

import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;

import com.example.base.BaseApplication;
import com.example.webview.ICallbackFromMainProcessToWebViewProcessInterface;
import com.example.webview.command.Command;
import com.google.auto.service.AutoService;

import java.util.Map;

/**
 * Created by BinKang on 2021/3/15.
 * Des :
 */
@AutoService(Command.class)
public class CommandOpenPage implements Command {
    @Override
    public String name() {
        return "openPage";
    }

    @Override
    public void execute(Map parameters, ICallbackFromMainProcessToWebViewProcessInterface callbackFromMainProcessToWebViewProcessInterface) {
        String targetClass = String.valueOf(parameters.get("target_class"));
        if (!TextUtils.isEmpty(targetClass)) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.sApplication, targetClass));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.sApplication.startActivity(intent);
        }
    }
}
