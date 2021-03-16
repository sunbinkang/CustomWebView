package com.example.webview.webviewprocess.webChromClient;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.webview.WebViewCallBack;

/**
 * Created by BinKang on 2021/3/15.
 * Des :
 */
public class XiangxueWebChromeClient extends WebChromeClient {

    private static final String TAG = "xiangxueWebChromeClient";
    private WebViewCallBack mWebViewCallBack;

    public XiangxueWebChromeClient(WebViewCallBack webViewCallBack) {
        this.mWebViewCallBack = webViewCallBack;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if (mWebViewCallBack != null) {
            mWebViewCallBack.updateTitle(title);
        } else {
            Log.e(TAG, "onReceivedTitle: " + "WebViewCallBack is null");
        }
    }

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.i(TAG, consoleMessage.message());
        return super.onConsoleMessage(consoleMessage);
    }
}
