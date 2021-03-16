package com.example.webview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.base.loadsir.ErrorCallback;
import com.example.base.loadsir.LoadingCallback;
import com.example.webview.databinding.FragmentWebviewBinding;
import com.example.webview.utils.Constants;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * Created by BinKang on 2021/3/12.
 * Des :
 */
public class WebViewFragment extends Fragment implements WebViewCallBack {
    private String mUrl;
    private FragmentWebviewBinding mBinding;
    private LoadService mLoadService;
    private boolean canNativeRefresh;
    private boolean mIsError = false;
    private static final String TAG = "WebViewFragment";

    public static WebViewFragment newInstance(String url, boolean canNativeRefresh) {
        WebViewFragment webViewFragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.URL, url);
        bundle.putBoolean(Constants.CAN_NATIVE_REFRESH, canNativeRefresh);
        webViewFragment.setArguments(bundle);
        return webViewFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mUrl = bundle.getString(Constants.URL);
            canNativeRefresh = bundle.getBoolean(Constants.CAN_NATIVE_REFRESH);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //第一步：获取布局View
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false);
        mBinding.webview.registerWebViewCallBack(this);
//        mBinding.webview.getSettings().setJavaScriptEnabled(true);
        mBinding.webview.loadUrl(mUrl);
        //第二步：注册布局View
        mLoadService = LoadSir.getDefault().register(mBinding.smartRefreshLayout, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                mLoadService.showCallback(LoadingCallback.class);
                mBinding.webview.reload();
            }
        });
//        WebViewDefaultSettings.getInstance().setSettings(mBinding.webview);
//        mBinding.webview.setWebViewClient(new XiangxueWebViewClient(this));
//        mBinding.webview.setWebChromeClient(new xiangxueWebChromeClient(this));
        mBinding.smartRefreshLayout.setEnableLoadMore(false);
        mBinding.smartRefreshLayout.setEnableRefresh(canNativeRefresh);
        mBinding.smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mBinding.webview.reload();
            }
        });
//        return mBinding.getRoot();//使用loadSir返回这个会报错
        //第三步：返回LoadSir生成的LoadLayout
        return mLoadService.getLoadLayout();

    }

    @Override
    public void pageStarted(String url) {
        if (mLoadService != null) {
            mLoadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void pageFinished(String url) {
        if (mIsError) {
            mBinding.smartRefreshLayout.setEnableRefresh(true);
        } else {
            mBinding.smartRefreshLayout.setEnableRefresh(canNativeRefresh);
        }
        Log.i(TAG, "pageFinished");
        mBinding.smartRefreshLayout.finishRefresh();
        if (mLoadService != null && !mIsError) {
            mLoadService.showSuccess();
        }
        mIsError = false;
    }

    @Override
    public void onError(String errorMsg) {
        Log.i(TAG, "onError" + errorMsg);
        mIsError = true;
        mBinding.smartRefreshLayout.finishRefresh();
        mLoadService.showCallback(ErrorCallback.class);
    }

    @Override
    public void updateTitle(String title) {
        if (getActivity() instanceof WebViewActivity) {
            ((WebViewActivity) getActivity()).updateTitle(title);
        }
    }
}
