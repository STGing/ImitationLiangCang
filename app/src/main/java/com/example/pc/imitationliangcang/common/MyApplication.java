package com.example.pc.imitationliangcang.common;

import android.app.Application;
import android.content.Context;

import com.lzy.okgo.OkGo;

/**
 * Created by PC on 2017/7/6.
 */

public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        //初始化okGo
        initOkGo();
    }

    private void initOkGo() {
        OkGo.getInstance().init(this);
    }

    /**
     * 获取全局上下文*/
    public static Context getContext() {
        return mContext;
    }

}
