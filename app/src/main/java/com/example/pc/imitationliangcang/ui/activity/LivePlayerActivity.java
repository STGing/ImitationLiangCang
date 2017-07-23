package com.example.pc.imitationliangcang.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;

import com.androidkun.xtablayout.XTabLayout;
import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseActivity;
import com.example.pc.imitationliangcang.bean.BiLiBean;
import com.example.pc.imitationliangcang.ijkplayer.media.IMediaController;
import com.example.pc.imitationliangcang.ijkplayer.media.IRenderView;
import com.example.pc.imitationliangcang.ijkplayer.media.IjkVideoView;
import com.example.pc.imitationliangcang.utils.GlideUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import master.flame.danmaku.ui.widget.DanmakuView;

public class LivePlayerActivity extends BaseActivity {

    @BindView(R.id.live_player_title_ll)
    LinearLayout livePlayerTitleLl;
    @BindView(R.id.live_player_videoview)
    IjkVideoView mVideoView;
    @BindView(R.id.live_player_ivBack)
    ImageView livePlayerIvBack;
    @BindView(R.id.live_player_tvName)
    TextView livePlayerTvName;
    @BindView(R.id.live_player_ivMenu)
    ImageView livePlayerIvMenu;
    @BindView(R.id.live_player_tvtitle)
    TextView livePlayerTvtitle;
    @BindView(R.id.live_player_iv_avatar)
    ImageView livePlayerIvAvatar;
    @BindView(R.id.live_player_tvUpName)
    TextView livePlayerTvUpName;
    @BindView(R.id.live_player_tvWatchNumber)
    TextView livePlayerTvWatchNumber;
    @BindView(R.id.live_player_tvCareNumber)
    TextView livePlayerTvCareNumber;
    @BindView(R.id.live_player_btn_care)
    Button livePlayerBtnCare;
    @BindView(R.id.live_player_tablayout)
    XTabLayout livePlayerTablayout;
    @BindView(R.id.live_player_viewpager)
    ViewPager livePlayerViewpager;
    @BindView(R.id.live_player_danmaku)
    DanmakuView livePlayerDanmaku;
    private PopupMenu popup;


    @Override
    public int getLayoutID() {
        return R.layout.activity_live_player;
    }

    @Override
    public void initData() {
        super.initData();

        //获取数据
        Bundle extras = getIntent().getExtras();
        BiLiBean.DataBean.PartitionsBean.LivesBean bean = (BiLiBean.DataBean.PartitionsBean.LivesBean) extras.getSerializable("data");

        //设置数据

        //1.设置播放器
        IMediaController controller = new IMediaController() {
            @Override
            public void hide() {

            }

            @Override
            public boolean isShowing() {
                return false;
            }

            @Override
            public void setAnchorView(View view) {

            }

            @Override
            public void setEnabled(boolean enabled) {

            }

            @Override
            public void setMediaPlayer(MediaController.MediaPlayerControl player) {

            }

            @Override
            public void show(int timeout) {

            }

            @Override
            public void show() {

            }

            @Override
            public void showOnce(View view) {

            }
        };
        mVideoView.setMediaController(controller);
        mVideoView.setAspectRatio(IRenderView.AR_ASPECT_FIT_PARENT);
        mVideoView.setVideoPath(bean.getPlayurl());
        mVideoView.start();


        //2.设置标题
        livePlayerTvName.setText(bean.getOwner().getName() + "的直播间");

        //3.设置标题2
        livePlayerTvtitle.setText(bean.getOwner().getName());

        //4.主播头像
        GlideUtils.loadImageView(this, bean.getOwner().getFace(), livePlayerIvAvatar);

        //5.主播等级（暂未设置，无数据）
        //6.主播名称
        livePlayerTvUpName.setText(bean.getOwner().getName());

        //7.观看数量
        livePlayerTvWatchNumber.setText(bean.getOnline() + "");

        //8.关注人数(无数据，暂不设置)

        //倒计时2S后关闭title栏
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        livePlayerTitleLl.setVisibility(View.GONE);
                    }
                });

            }
        }, 2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideoView.pause();
    }

    @OnClick({R.id.live_player_ivBack, R.id.live_player_tvName, R.id.live_player_ivMenu, R.id.live_player_tvtitle, R.id.live_player_btn_care})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.live_player_ivBack:
                finish();
                break;
            case R.id.live_player_tvName:
                break;
            case R.id.live_player_ivMenu:

                if (popup == null) {
                    popup = new PopupMenu(this, livePlayerIvMenu);
                    popup.getMenuInflater()
                            .inflate(R.menu.live_player_popmenu, popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            Toast.makeText(LivePlayerActivity.this, "分享", Toast.LENGTH_SHORT).show();

                            return true;
                        }
                    });
                }
                popup.show();
                break;
            case R.id.live_player_tvtitle:
                break;
            case R.id.live_player_btn_care:
                break;
        }
    }

}
