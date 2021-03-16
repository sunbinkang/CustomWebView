package com.example.webview;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.common.autpservice.IWebViewService;
import com.example.webview.utils.Constants;
import com.google.auto.service.AutoService;

/**
 * Created by BinKang on 2021/3/12.
 * Des :
 */
@AutoService(IWebViewService.class)
public class WebViewServiceImpl implements IWebViewService {

    @Override
    public void startWebViewActivity(Context context, String url, String title, boolean isShowActionBar) {
        if (context != null) {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra(Constants.TITLE, title);
            intent.putExtra(Constants.URL, url);
            intent.putExtra(Constants.IS_SHOW_ACTIONBAR, isShowActionBar);
            context.startActivity(intent);
        }
    }

    @Override
    public Fragment getWebViewFragment(String url, boolean canNativeRefresh) {
        return WebViewFragment.newInstance(url, canNativeRefresh);
    }

    @Override
    public void startDemoHtml(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra(Constants.TITLE, "本地测试");
            intent.putExtra(Constants.URL, Constants.ANDROID_ASSETS_URL + "demo.html");
            context.startActivity(intent);
        }
    }
}
