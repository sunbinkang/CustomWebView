package com.example.common.autpservice;

import android.content.Context;

import androidx.fragment.app.Fragment;

/**
 * Created by BinKang on 2021/3/12.
 * Des :
 */
public interface IWebViewService {

    void startWebViewActivity(Context context, String url, String title, boolean isShowActionBar);

    Fragment getWebViewFragment(String url, boolean canNativeRefresh);

    void startDemoHtml(Context context);

}
