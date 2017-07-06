package com.example.pc.imitationliangcang.ui.activity;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseActivity;
import com.example.pc.imitationliangcang.base.BaseFragment;
import com.example.pc.imitationliangcang.ui.fragment.MagazineFragment;
import com.example.pc.imitationliangcang.ui.fragment.ShareFragment;
import com.example.pc.imitationliangcang.ui.fragment.ShopFragment;
import com.example.pc.imitationliangcang.ui.fragment.TrendPersonFragment;
import com.example.pc.imitationliangcang.ui.fragment.UserCenterFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    //存放fragment的集合
    private List<BaseFragment> fragments;

    @Override
    public void initView() {
        super.initView();

        //初始化fragments集合
        fragments = new ArrayList<>();
        fragments.add(new ShopFragment());
        fragments.add(new MagazineFragment());
        fragments.add(new TrendPersonFragment());
        fragments.add(new ShareFragment());
        fragments.add(new UserCenterFragment());
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }
}
