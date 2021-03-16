package com.example.webview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.webview.bean.JsParam;
import com.example.webview.webviewprocess.WebViewProcessCommandDispatcher;
import com.example.webview.webviewprocess.settings.WebViewDefaultSettings;
import com.example.webview.webviewprocess.webChromClient.XiangxueWebChromeClient;
import com.example.webview.webviewprocess.webviewclient.XiangxueWebViewClient;
import com.google.gson.Gson;


/**
 * Created by BinKang on 2021/3/15.
 * Des :
 */
public class BaseWebView extends WebView {

    private static final String TAG = "BaseWebView";

    WebViewCallBack mWebViewCallBack;

    public BaseWebView(@NonNull Context context) {
        super(context);
        init();
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        WebViewProcessCommandDispatcher.getInstance().initAidlConnection();//建立好链接
        WebViewDefaultSettings.getInstance().setSettings(this);
        addJavascriptInterface(this, "xiangxuewebview");
    }

    public void registerWebViewCallBack(WebViewCallBack webViewCallBack) {
        setWebViewClient(new XiangxueWebViewClient(webViewCallBack));
        setWebChromeClient(new XiangxueWebChromeClient(webViewCallBack));
    }

    @JavascriptInterface
    public void takeNativeAction(String jsParam) {
        Log.i(TAG, "takeNativeAction: " + jsParam);
        if (!TextUtils.isEmpty(jsParam)) {
            JsParam jsParamObject = new Gson().fromJson(jsParam, JsParam.class);
            if (jsParamObject != null) {
                WebViewProcessCommandDispatcher.getInstance().executeCommand(jsParamObject.name, new Gson().toJson(jsParamObject.param),this);
            }
        }
    }

    public void handleCallback(final String callbackname, final String response) {

        if (!TextUtils.isEmpty(callbackname) && !TextUtils.isEmpty(response)) {
            post(new Runnable() {
                @Override
                public void run() {
                    String jscode = "javascript:xiangxuejs.callback('" + callbackname + "'," + response + ")";
                    Log.e("xxxxxx", jscode);
                    evaluateJavascript(jscode, null);
                }
            });
        }

    }
}
