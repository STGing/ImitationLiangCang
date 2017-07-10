package com.example.pc.imitationliangcang.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.androidkun.xtablayout.XTabLayout;
import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseActivity;
import com.example.pc.imitationliangcang.base.BaseFragment;
import com.example.pc.imitationliangcang.ui.adapter.shopfragment.ShopFragmenViewPagerAdapter;
import com.example.pc.imitationliangcang.ui.fragment.magazine_fragment.MagazineAutherFragment;
import com.example.pc.imitationliangcang.ui.fragment.magazine_fragment.MagazineClassifyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MaganizeActivity extends BaseActivity {


    @BindView(R.id.magazine_viewPager)
    ViewPager magazineViewPager;
    @BindView(R.id.magazine_bottom_ll)
    LinearLayout magazineBottomLl;
    @BindView(R.id.magazine_tabLayout)
    XTabLayout magazineTabLayout;
    @BindView(R.id.magazine_iv_classify)
    ImageView magazineIvClassify;
    @BindView(R.id.magazine_iv_auther)
    ImageView magazineIvAuther;

    //存放fragment集合
    private List<BaseFragment> fragments;

    //存放tabLayout标题的集合
    private List<String> title_list;

    private ShopFragmenViewPagerAdapter adapter;

    @Override
    public int getLayoutID() {
        return R.layout.activity_maganize;
    }


    @Override
    public void initView() {
        super.initView();

        //初始化fragments
        fragments = new ArrayList<>();
        fragments.add(new MagazineClassifyFragment());
        fragments.add(new MagazineAutherFragment());

        //初始化标题
        title_list = new ArrayList<>();
        title_list.add("分类");
        title_list.add("作者");

        //设置ViewPager
        FragmentManager fm = getSupportFragmentManager();
        adapter = new ShopFragmenViewPagerAdapter(fm, fragments, title_list);
        magazineViewPager.setAdapter(adapter);

        //设置tablayout
        magazineTabLayout.setTabMode(TabLayout.MODE_FIXED);
        magazineTabLayout.setupWithViewPager(magazineViewPager);

        //设置默认选中首页页面
        magazineViewPager.setCurrentItem(0);

    }

    @Override
    public void initLIstener() {
        super.initLIstener();


    }



    @OnClick({R.id.magazine_iv_classify, R.id.magazine_iv_auther, R.id.magazine_bottom_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.magazine_iv_classify:
                //点击分类
                magazineViewPager.setCurrentItem(0);
                break;
            case R.id.magazine_iv_auther:
                //点击作者
                magazineViewPager.setCurrentItem(1);
                break;
            case R.id.magazine_bottom_ll:
                finish();
                break;
        }
    }
}
