package com.example.webview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.webview.databinding.ActivityWebviewBinding;
import com.example.webview.utils.Constants;

/**
 * Created by BinKang on 2021/3/12.
 * Des :
 */
public class WebViewActivity extends AppCompatActivity {

    private ActivityWebviewBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_webview);
        mBinding.title.setText(getIntent().getStringExtra(Constants.TITLE));
        mBinding.actionBar.setVisibility(getIntent().getBooleanExtra(Constants.IS_SHOW_ACTIONBAR, true) ? View.VISIBLE : View.GONE);

        mBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.this.finish();
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = WebViewFragment.newInstance(getIntent().getStringExtra(Constants.URL), true);
        transaction.replace(R.id.web_view_fragment, fragment).commit();
    }

    public void updateTitle(String title) {
        mBinding.title.setText(title);
    }
}
