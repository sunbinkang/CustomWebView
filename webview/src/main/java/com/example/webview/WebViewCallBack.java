package com.example.webview;

/**
 * Created by BinKang on 2021/3/12.
 * Des :
 */
public interface WebViewCallBack {

    void pageStarted(String url);

    void pageFinished(String url);

    void onError(String errorMsg);

    void updateTitle(String title);

}
