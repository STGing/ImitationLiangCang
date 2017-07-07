package com.example.pc.imitationliangcang.ui.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseActivity;
import com.example.pc.imitationliangcang.ui.adapter.SplashActivityViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity {


    @BindView(R.id.splashActivity_viewPager)
    ViewPager splashActivityViewPager;
    @BindView(R.id.splashActivity_iv_startActivity)
    ImageView splashActivityIvStartActivity;
    //适配器
    private SplashActivityViewPagerAdapter adapter;

    //用于存放ImageView的集合
    private List<ImageView> images;

    //imageView资源集合
    private int[] imagesID = new int[]{
            R.drawable.feature1, R.drawable.feature2, R.drawable.feature3
            , R.drawable.feature4, R.drawable.feature5
    };

    private static final String TAG = "SplashActivity";


    /**
     * 初始化View
     * 4个左右滑动的Gif图片
     * 1个静态的图片
     */
    @Override
    public void initView() {
        super.initView();

        images = new ArrayList<>();

        //建立图片
        for (int i = 0; i < imagesID.length; i++) {

            ImageView imageView = new ImageView(this);

            Glide.with(this)
                    .load(imagesID[i])//加载图片地址
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)//设置缓存为：缓存全部
                    .into(imageView);

            //将图片添加到集合中
            images.add(imageView);
        }


    }

    @Override
    public void initLIstener() {
        super.initLIstener();

        //设置ViewPager的监听
        splashActivityViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Log.e(TAG, "onPageScrolled: "+" position==== "+position+"   positionOffset==="+positionOffset );


            }

            @Override
            public void onPageSelected(int position) {
                //页面被选中的时候，判断是否最后一个页面，是显示按钮
                if (position == imagesID.length - 1) {
                    splashActivityIvStartActivity.setVisibility(View.VISIBLE);
                } else {
                    splashActivityIvStartActivity.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        //设置ViewPager

        adapter = new SplashActivityViewPagerAdapter(images);
        splashActivityViewPager.setAdapter(adapter);

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_splash;
    }




    @OnClick(R.id.splashActivity_iv_startActivity)
    public void onViewClicked() {
        //点击图片，进入MainActivity
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}
