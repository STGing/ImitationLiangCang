package com.example.pc.imitationliangcang.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.example.pc.imitationliangcang.utils.HttpUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by PC on 2017/7/5.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        unbinder = ButterKnife.bind(this);

        initView();
        initLIstener();
        initData();
    }


    /**
     * 初始化View
     */
    public void initView() {

    }

    /**
     * 初始化监听器
     */
    public void initLIstener() {

    }

    /**
     * 初始化数据
     */
    public void initData(){
        getNetData(getUrl());
    }

    /**
     * 返回布局文件ID
     * @return
     */
    public abstract int getLayoutID();

    public String getUrl(){
        return null;
    }

    /**
     * 根据URL请求数据
     * @param url
     */
    public void getNetData(String url){

        if (!TextUtils.isEmpty(url)) {
            HttpUtils.getInstance().getNetData(url)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull String s) {
                            if (!TextUtils.isEmpty(s)) {
                                processData(s);
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

    }

    //用来处理解析的数据
    public void processData(String s) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
