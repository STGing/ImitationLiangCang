package com.example.pc.imitationliangcang.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

/**
 * 广告轮播的图片加载器
 */

public class MyImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        //使用Picasso加载图片
        Picasso.with(context).load((String) path).into(imageView);
    }
}
