package com.example.pc.imitationliangcang.ui.activity;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopFragmentWebViewActivity extends BaseActivity {

    @BindView(R.id.title_iv_search)
    ImageView titleIvSearch;
    @BindView(R.id.title_tv_name)
    TextView titleTvName;
    @BindView(R.id.homeFragment_webView_progressbar)
    ProgressBar homeFragmentWebViewProgressbar;
    @BindView(R.id.homeFragment_webView)
    WebView homeFragmentWebView;

    private WebView webView;
    private WebSettings settings;

    @Override
    public void initView() {
        super.initView();

        //设置标题返回按钮


    }

    @Override
    public void initData() {
        super.initData();
        String topic_url = getIntent().getStringExtra("topic_url");

        //建立webview
        webView = (WebView) findViewById(R.id.homeFragment_webView);
        settings = webView.getSettings();
        //适应屏幕
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //缩放操作
        settings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        settings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        settings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //设置不用系统浏览器打开,直接显示在当前Webview
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient(){
            //进度条
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
//                if (newProgress < 100) {
//                    homeFragmentWebViewProgressbar.setProgress(newProgress);
//                } else {
//                    homeFragmentWebViewProgressbar.setVisibility(View.GONE);
//                }
//            }
            //标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                titleTvName.setText(title);
            }
        });

        webView.loadUrl(topic_url);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_home_fragment_web_view;
    }


    @OnClick(R.id.title_iv_search)
    public void onViewClicked() {
    }
}
