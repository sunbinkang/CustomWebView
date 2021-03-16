package com.example.webview.webviewprocess.webviewclient;

import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;

import com.example.webview.WebViewCallBack;

/**
 * Created by BinKang on 2021/3/12.
 * Des :
 */
public class XiangxueWebViewClient extends WebViewClient {

    private WebViewCallBack mWebViewCallBack;
    private static final String TAG = "XiangxueWebViewClient";

    public XiangxueWebViewClient(WebViewCallBack webViewCallBack) {
        this.mWebViewCallBack = webViewCallBack;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mWebViewCallBack != null) {
            mWebViewCallBack.pageStarted(url);
        } else {
            Log.e(TAG, "onPageStarted: " + "WebViewCallBack is null");
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mWebViewCallBack != null) {
            mWebViewCallBack.pageFinished(url);
        } else {
            Log.e(TAG, "onPageFinished: " + "WebViewCallBack is null");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        if (mWebViewCallBack != null) {
            mWebViewCallBack.onError(error.getDescription().toString());
        } else {
            Log.e(TAG, "onReceivedError: " + "WebViewCallBack is null");
        }
    }
}
