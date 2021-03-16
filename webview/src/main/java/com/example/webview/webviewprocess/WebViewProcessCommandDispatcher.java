package com.example.webview.webviewprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.base.BaseApplication;
import com.example.webview.BaseWebView;
import com.example.webview.ICallbackFromMainProcessToWebViewProcessInterface;
import com.example.webview.IWebViewProcessToMainProcessInterface;
import com.example.webview.mainprocess.MainProcessCommandService;

/**
 * Created by BinKang on 2021/3/15.
 * Des :
 */
public class WebViewProcessCommandDispatcher implements ServiceConnection {

    private static WebViewProcessCommandDispatcher instance;
    private IWebViewProcessToMainProcessInterface iWebviewProcessToMainProcessInterface;

    private WebViewProcessCommandDispatcher() {
    }

    public static WebViewProcessCommandDispatcher getInstance() {
        if (instance == null) {
            synchronized (WebViewProcessCommandDispatcher.class) {
                instance = new WebViewProcessCommandDispatcher();
            }
        }
        return instance;
    }

    public void initAidlConnection() {
        Intent intent = new Intent(BaseApplication.sApplication, MainProcessCommandService.class);
        BaseApplication.sApplication.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        iWebviewProcessToMainProcessInterface = IWebViewProcessToMainProcessInterface.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        iWebviewProcessToMainProcessInterface = null;
        initAidlConnection();
    }

    @Override
    public void onBindingDied(ComponentName name) {
        iWebviewProcessToMainProcessInterface = null;
        initAidlConnection();
    }

    public void executeCommand(String commandName, String params, final BaseWebView baseWebView) {
        if (iWebviewProcessToMainProcessInterface != null) {
            try {
                iWebviewProcessToMainProcessInterface.handleWebCommand(commandName, params, new ICallbackFromMainProcessToWebViewProcessInterface.Stub() {
                    @Override
                    public void onResult(String callbackname, String response) throws RemoteException {
                        baseWebView.handleCallback(callbackname, response);
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
