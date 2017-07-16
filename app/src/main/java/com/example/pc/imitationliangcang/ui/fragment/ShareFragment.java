package com.example.pc.imitationliangcang.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseFragment;
import com.example.pc.imitationliangcang.ui.adapter.shopfragment.ShopFragmenViewPagerAdapter;
import com.example.pc.imitationliangcang.ui.fragment.shard_fragment.share_duanzi_fragment;
import com.example.pc.imitationliangcang.ui.fragment.shard_fragment.share_recommend_fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by PC on 2017/7/6.
 */

public class ShareFragment extends BaseFragment {


    @BindView(R.id.title_tv_name)
    TextView titleTvName;
    @BindView(R.id.base_title_layout)
    RelativeLayout baseTitleLayout;
    @BindView(R.id.share_fragment_tabLayout)
    XTabLayout shareFragmentTabLayout;
    @BindView(R.id.share_fragment_viewPager)
    ViewPager shareFragmentViewPager;

    //存放fragment集合
    private List<BaseFragment> fragments;

    //存放tabLayout标题的集合
    private List<String> title_list;
    //viewpager的适配器
    private ShopFragmenViewPagerAdapter adapter;

    @Override
    public int getLayoutID() {
        return R.layout.share_fragment;
    }

    @Override
    public void initView() {

        //初始化标题
        titleTvName.setText("百思不得姐");
        titleTvName.setTextColor(Color.parseColor("#fffefe"));

        //初始化fragments
        fragments = new ArrayList<>();
        fragments.add(new share_recommend_fragment());
        fragments.add(new share_duanzi_fragment());

        //tablayout标题
        title_list = new ArrayList<>();
        title_list.add("推荐");
        title_list.add("段子");

        //设置ViewPager
        FragmentManager fm = getFragmentManager();
        adapter = new ShopFragmenViewPagerAdapter(fm,fragments,title_list);
        shareFragmentViewPager.setAdapter(adapter);

        //设置tablayout
        shareFragmentTabLayout.setTabMode(TabLayout.MODE_FIXED);
        shareFragmentTabLayout.setupWithViewPager(shareFragmentViewPager);

        //设置默认选中首页页面
        shareFragmentViewPager.setCurrentItem(0);
    }

}
