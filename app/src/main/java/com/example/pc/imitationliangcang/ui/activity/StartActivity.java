package com.example.pc.imitationliangcang.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.example.pc.imitationliangcang.R;

/**
 * 启动页面：播放GIF
 */

public class StartActivity extends AppCompatActivity {

    private ImageView start_iv;
    private static final String TAG = "StartActivity";
    private int duration = 0;//Gif的时长

    private static final int WHAT_GIF_FINISH = 0;//GIF播放完成的标志

    //handler来接收gif播放完成的消息
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT_GIF_FINISH:

                    //GIF播放结束，跳转到SplashActivity
                    startActivity(new Intent(StartActivity.this,SplashActivity.class));
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        start_iv = (ImageView) findViewById(R.id.start_iv);

        //Glide.with(this).asGif().load(R.drawable.loading_start).into(start_iv);

        Glide.with(this)
                .load(R.drawable.loading_start)//加载图片地址
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)//设置缓存为：缓存全部
                .listener(new RequestListener<Integer, GlideDrawable>() {//设置监听
                    @Override
                    public boolean onException(Exception e, Integer integer, Target<GlideDrawable> target, boolean b) {
                        Log.e(TAG, "StartActivity___onException: " );
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable glideDrawable, Integer integer, Target<GlideDrawable> target, boolean b, boolean b1) {
                        //Log.e(TAG, "StartActivity___onResourceReady: " );

                        // 计算动画时长
                        GifDrawable drawable = (GifDrawable) glideDrawable;
                        GifDecoder decoder = drawable.getDecoder();
                        for (int i = 0; i < drawable.getFrameCount(); i++) {
                            duration += decoder.getDelay(i);
                        }

                        //发送延时消息，通知动画结束
                        handler.sendEmptyMessageDelayed(WHAT_GIF_FINISH, duration);

                        return false;
                    }
                })
                .into(new GlideDrawableImageViewTarget(start_iv,1));//设置只加载一次gif
    }
}
