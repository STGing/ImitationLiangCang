package com.example.pc.imitationliangcang.ui.fragment;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseFragment;
import com.youth.banner.Banner;

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
    @BindView(R.id.banner_bili)
    Banner bannerBili;
    @BindView(R.id.tv_follow_bili)
    TextView tvFollowBili;
    @BindView(R.id.tv_center_bili)
    TextView tvCenterBili;
    @BindView(R.id.tv_slipvideo_bili)
    TextView tvSlipvideoBili;
    @BindView(R.id.tv_search_bili)
    TextView tvSearchBili;
    @BindView(R.id.tv_all_category_bili)
    TextView tvAllCategoryBili;
    @BindView(R.id.tv_recommend_uper)
    TextView tvRecommendUper;
    @BindView(R.id.tv_recommend_uperNumber)
    TextView tvRecommendUperNumber;
    @BindView(R.id.ll_uperNumber)
    RelativeLayout llUperNumber;
    @BindView(R.id.bili_rg)
    RecyclerView biliRg;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;



    @Override
    public int getLayoutID() {
        return R.layout.usercenter_fragment;
    }

    @Override
    public void initView() {

        AppCompatActivity activity = (AppCompatActivity) mContext;
        activity.setSupportActionBar(toolbar);



    }



    @OnClick({R.id.toolbar_iv1_bili, R.id.toolbar_iv2_bili, R.id.toolbar_iv3_bili, R.id.toolbar_iv4_bili, R.id.tv_follow_bili, R.id.tv_center_bili, R.id.tv_slipvideo_bili, R.id.tv_search_bili, R.id.tv_all_category_bili, R.id.ll_uperNumber, R.id.bili_rg})
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
            case R.id.tv_follow_bili:
                break;
            case R.id.tv_center_bili:
                break;
            case R.id.tv_slipvideo_bili:
                break;
            case R.id.tv_search_bili:
                break;
            case R.id.tv_all_category_bili:
                break;
            case R.id.ll_uperNumber:
                break;
            case R.id.bili_rg:
                break;
        }
    }
}
