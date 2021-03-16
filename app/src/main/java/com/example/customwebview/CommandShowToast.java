package com.example.customwebview;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.example.base.BaseApplication;
import com.example.webview.ICallbackFromMainProcessToWebViewProcessInterface;
import com.example.webview.command.Command;
import com.google.auto.service.AutoService;

import java.util.Map;

/**
 * Created by BinKang on 2021/3/16.
 * Des :
 */
@AutoService(Command.class)
public class CommandShowToast implements Command {

    @Override
    public String name() {
        return "showToast";
    }

    @Override
    public void execute(Map parameters, ICallbackFromMainProcessToWebViewProcessInterface callbackFromMainProcessToWebViewProcessInterface) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> Toast.makeText(BaseApplication.sApplication, String.valueOf(parameters.get("message")), Toast.LENGTH_SHORT).show());
    }
}
