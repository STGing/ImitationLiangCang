package com.example.pc.imitationliangcang.ui.fragment;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseFragment;
import com.example.pc.imitationliangcang.bean.BiLiBean;
import com.example.pc.imitationliangcang.common.NetWorkUrl;
import com.example.pc.imitationliangcang.ui.adapter.bilibili.BiLiAdapter;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 仿bilibili直播界面
 */

public class UserCenterFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_iv1_bili)
    ImageView toolbarIv1Bili;
    @BindView(R.id.toolbar_iv2_bili)
    ImageView toolbarIv2Bili;
    @BindView(R.id.toolbar_iv3_bili)
    ImageView toolbarIv3Bili;
    @BindView(R.id.toolbar_iv4_bili)
    ImageView toolbarIv4Bili;

    @BindView(R.id.bili_rv)
    RecyclerView biliRv;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private BiLiAdapter adapter;

    @Override
    public int getLayoutID() {
        return R.layout.usercenter_fragment;
    }

    @Override
    public void initView() {

        AppCompatActivity activity = (AppCompatActivity) mContext;
        activity.setSupportActionBar(toolbar);


    }

    @Override
    public String getUrl() {
        return NetWorkUrl.BILIURL;
    }

    @Override
    public void processData(String s) {
        super.processData(s);

        //获取数据

        BiLiBean biLiBean = new Gson().fromJson(s, BiLiBean.class);
        BiLiBean.DataBean data = biLiBean.getData();

        //设置RecyclerView的数据

        LinearLayoutManager manager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        biliRv.setLayoutManager(manager);

        adapter = new BiLiAdapter(mContext,data);
        biliRv.setAdapter(adapter);

    }

    @Override
    public void initListener() {
        super.initListener();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                mContext, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

    }

    @OnClick({R.id.toolbar_iv1_bili, R.id.toolbar_iv2_bili, R.id.toolbar_iv3_bili, R.id.toolbar_iv4_bili})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_iv1_bili:
                break;
            case R.id.toolbar_iv2_bili:
                break;
            case R.id.toolbar_iv3_bili:
                break;
            case R.id.toolbar_iv4_bili:
                break;
        }
    }
}
