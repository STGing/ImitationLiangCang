package com.example.pc.imitationliangcang.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    public abstract void initData();

    /**
     * 返回布局文件ID
     * @return
     */
    public abstract int getLayoutID();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
