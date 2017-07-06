package com.example.pc.imitationliangcang.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseFragment;
import com.example.pc.imitationliangcang.ui.adapter.shopfragment.ShopFragmenViewPagerAdapter;
import com.example.pc.imitationliangcang.ui.fragment.shop_fragment_detail.BrandFragment;
import com.example.pc.imitationliangcang.ui.fragment.shop_fragment_detail.GiftFragment;
import com.example.pc.imitationliangcang.ui.fragment.shop_fragment_detail.HomeFragment;
import com.example.pc.imitationliangcang.ui.fragment.shop_fragment_detail.TopicFragment;
import com.example.pc.imitationliangcang.ui.fragment.shop_fragment_detail.classifyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by PC on 2017/7/6.
 */

public class ShopFragment extends BaseFragment {


    @BindView(R.id.title_iv_search)
    ImageView titleIvSearch;
    @BindView(R.id.title_tv_name)
    TextView titleTvName;
    @BindView(R.id.title_iv_shopCar)
    ImageView titleIvShopCar;
    @BindView(R.id.shop_fragment_tablayout)
    TabLayout shopFragmentTablayout;
    @BindView(R.id.shop_fragment_viewPager)
    ViewPager shopFragmentViewPager;

    //适配器
    private ShopFragmenViewPagerAdapter adapter;

    //存放fragment的集合
    private List<BaseFragment> fragments;

    //存放TabLayout显示文字的集合
    private List<String> title_list;

    @Override
    public int getLayoutID() {
        return R.layout.shop_fragment;
    }

    @Override
    public void initTitle() {
        super.initTitle();

        //显示标题中的内容
        titleIvSearch.setVisibility(View.VISIBLE);//搜索按钮
        titleTvName.setText("商店");//标题名称
        titleIvShopCar.setVisibility(View.VISIBLE);//购物车

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        super.initData();
        //初始化Fragments
        fragments = new ArrayList<>();
        fragments.add(new classifyFragment());
        fragments.add(new BrandFragment());
        fragments.add(new HomeFragment());
        fragments.add(new TopicFragment());
        fragments.add(new GiftFragment());

        //初始化title_list
        title_list = new ArrayList<>();
        title_list.add("分类");
        title_list.add("品牌");
        title_list.add("首页");
        title_list.add("专题");
        title_list.add("礼物");


        //设置ViewPager
        FragmentManager fragmentManager = getFragmentManager();
        adapter = new ShopFragmenViewPagerAdapter(fragmentManager,fragments,title_list);
        shopFragmentViewPager.setAdapter(adapter);

        //设置tablayout
        shopFragmentTablayout.setTabMode(TabLayout.MODE_FIXED);
        shopFragmentTablayout.setupWithViewPager(shopFragmentViewPager);

        //设置默认选中首页页面
        shopFragmentViewPager.setCurrentItem(2);
    }


    @Override
    public void initListener() {
        super.initListener();

        //设置Viewpager的事件监听
        shopFragmentViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @OnClick({R.id.title_iv_search, R.id.title_iv_shopCar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_search:
                break;
            case R.id.title_iv_shopCar:
                break;
        }
    }
}
