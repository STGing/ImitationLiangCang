package com.example.pc.imitationliangcang.common;

import android.app.Application;

import com.lzy.okgo.OkGo;

/**
 * Created by PC on 2017/7/6.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化okGo
        initOkGo();
    }

    private void initOkGo() {
        OkGo.getInstance().init(this);
    }
}
