package com.example.customwebview;

import android.os.RemoteException;
import android.util.Log;

import com.example.base.autoservice.XiangxueServiceLoader;
import com.example.common.autpservice.IUserCenterService;
import com.example.common.eventbus.LoginEvent;
import com.example.webview.ICallbackFromMainProcessToWebViewProcessInterface;
import com.example.webview.command.Command;
import com.google.auto.service.AutoService;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BinKang on 2021/3/16.
 * Des :
 */
@AutoService(Command.class)
public class CommandLogin implements Command {

    IUserCenterService mIUserCenterService = XiangxueServiceLoader.load(IUserCenterService.class);
    ICallbackFromMainProcessToWebViewProcessInterface mCallback;
    String callbacknameFromNativeJs;

    public CommandLogin() {
        EventBus.getDefault().register(this);
    }

    @Override
    public String name() {
        return "login";
    }

    @Override
    public void execute(Map parameters, ICallbackFromMainProcessToWebViewProcessInterface callbackFromMainProcessToWebViewProcessInterface) {
        Log.i("CommandLogin", new Gson().toJson(parameters));
        if (mIUserCenterService != null && !mIUserCenterService.isLogined()) {
            mIUserCenterService.login();
            this.mCallback = callbackFromMainProcessToWebViewProcessInterface;
            this.callbacknameFromNativeJs = parameters.get("callbackname").toString();
        }
    }

    @Subscribe
    public void onMessageEvent(LoginEvent loginEvent) {
        Log.i("CommandLogin", "onMessageEvent: " + loginEvent.name);
        HashMap hashMap = new HashMap();
        hashMap.put("accountName", loginEvent.name);
        if (this.mCallback != null) {
            try {
                this.mCallback.onResult(callbacknameFromNativeJs,new Gson().toJson(hashMap));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
