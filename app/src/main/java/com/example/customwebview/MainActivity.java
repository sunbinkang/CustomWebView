package com.example.customwebview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.base.autoservice.XiangxueServiceLoader;
import com.example.common.autpservice.IWebViewService;
import com.example.webview.WebViewActivity;

import java.util.ServiceLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.open).setOnClickListener(v -> {
//                startActivity(new Intent(MainActivity.this, WebViewActivity.class));
//                IWebViewService webViewService = ServiceLoader.load(IWebViewService.class).iterator().next();

            IWebViewService webViewService = XiangxueServiceLoader.load(IWebViewService.class);
            if (webViewService != null) {
//                webViewService.startWebViewActivity(MainActivity.this, "https://www.baidu.com", "百度", true);
                webViewService.startDemoHtml(MainActivity.this);
            }
        });
    }
}