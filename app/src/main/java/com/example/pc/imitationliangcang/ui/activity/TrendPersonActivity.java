package com.example.pc.imitationliangcang.ui.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseActivity;
import com.example.pc.imitationliangcang.base.BaseFragment;
import com.example.pc.imitationliangcang.bean.trenpersonfragment.TrendPersonListBean;
import com.example.pc.imitationliangcang.common.NetWorkUrl;
import com.example.pc.imitationliangcang.ui.fragment.tend_person.TrendPersonCareFragment;
import com.example.pc.imitationliangcang.ui.fragment.tend_person.TrendPersonFansFragment;
import com.example.pc.imitationliangcang.ui.fragment.tend_person.TrendPersonLikedFragment;
import com.example.pc.imitationliangcang.ui.fragment.tend_person.TrendPersonRecommendFragment;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TrendPersonActivity extends BaseActivity {

    @BindView(R.id.title_iv_back)
    ImageView titleIvBack;
    @BindView(R.id.title_tv_name)
    TextView titleTvName;
    @BindView(R.id.base_title_layout)
    RelativeLayout baseTitleLayout;
    @BindView(R.id.trend_person_detail_userIv)
    ImageView trendPersonDetailUserIv;
    @BindView(R.id.trend_person_detail_nameTv)
    TextView trendPersonDetailNameTv;
    @BindView(R.id.trend_person_detail_workTv)
    TextView trendPersonDetailWorkTv;
    @BindView(R.id.trend_person_detail_careBtn)
    Button trendPersonDetailCareBtn;
    @BindView(R.id.trend_person_detail_messageBtn)
    Button trendPersonDetailMessageBtn;
    @BindView(R.id.trend_person_detail_likeConuntRB)
    RadioButton trendPersonDetailLikeConuntRB;
    @BindView(R.id.trend_person_detail_recommendRB)
    RadioButton trendPersonDetailRecommendRB;
    @BindView(R.id.trend_person_detail_careRB)
    RadioButton trendPersonDetailCareRB;
    @BindView(R.id.trend_person_detail_fansRB)
    RadioButton trendPersonDetailFansRB;
    @BindView(R.id.trend_person_detail_framenLayout)
    FrameLayout trendPersonDetailFramenLayout;
    @BindView(R.id.trend_person_detail_radioGroup)
    RadioGroup trendPersonDetailRadioGroup;

    //存放fragment的集合
    private List<BaseFragment> fragments;

    private int position = 0;//当前页面索引位置
    private BaseFragment currentFragment;//当前页面的fragment
    private BaseFragment lastFragment;//之前页面的fragment


    @Override
    public void initView() {
        super.initView();

        //初始化fragments集合
        fragments = new ArrayList<>();
        fragments.add(new TrendPersonLikedFragment());
        fragments.add(new TrendPersonRecommendFragment());
        fragments.add(new TrendPersonCareFragment());
        fragments.add(new TrendPersonFansFragment());

        //设置标题
        titleIvBack.setVisibility(View.VISIBLE);
    }

    @Override
    public void initLIstener() {
        super.initLIstener();

        trendPersonDetailRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.trend_person_detail_likeConuntRB://喜欢数量
                        position = 0;
                        break;
                    case R.id.trend_person_detail_recommendRB://推荐
                        position = 1;
                        break;
                    case R.id.trend_person_detail_careRB://关注
                        position = 2;
                        break;
                    case R.id.trend_person_detail_fansRB://粉丝
                        position = 3;
                        break;
                }
                //根据位置，切换Fragment
                switchFragment(position);
            }
        });

        //设置默认选中的button
        trendPersonDetailRadioGroup.check(R.id.trend_person_detail_recommendRB);

    }

    /**
     * 根据位置切换Fragment
     * 使用：隐藏和显示的方法来切换fragmnet
     * @param position
     */
    private void switchFragment(int position) {
        //1.根据位置获取当前的fragment
        currentFragment = fragments.get(position);

        //2.获取事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        //3.如果不是之前的页面就切换
        if(lastFragment != currentFragment) {

            //如果当前的页面没有添加过
            if(!currentFragment.isAdded()) {

                //隐藏之前的
                if(lastFragment != null) {
                    ft.hide(lastFragment);
                }

                //添加当前页面
                ft.add(R.id.trend_person_detail_framenLayout,currentFragment);
            } else {
                //如果当前的添加过

                //隐藏之前的
                ft.hide(lastFragment);

                //显示当前的
                ft.show(currentFragment);
            }

            //提交事务
            ft.commit();

            //最后一定要赋值
            lastFragment = currentFragment;

        }
    }

    @Override
    public String getUrl() {

        //获取url
        String uid = getIntent().getStringExtra("uid");

        //获取url
        String url = NetWorkUrl.TRENDPERSONDETAILURL01 + uid + NetWorkUrl.TRENDPERSONDETAILURL02;

        return url;
    }

    //处理数据


    @Override
    public void processData(String s) {
        super.processData(s);
        if (!TextUtils.isEmpty(s)) {
            //解析数据
            TrendPersonListBean.DataBean.ItemsBean items = new Gson().fromJson(s, TrendPersonListBean.class).getData().getItems();

            //设置数据
            if (items != null) {

                //1.设置标题
                titleTvName.setText(items.getUser_name());

                //2.设置人物图片
                Picasso.with(this)
                        .load(items.getUser_image().getOrig())
                        .into(trendPersonDetailUserIv);

                //3.设置名称
                trendPersonDetailNameTv.setText(items.getUser_name());

                //4.设置工作信息
                trendPersonDetailWorkTv.setText(items.getUser_desc());

                //5.设置喜欢的数量
                trendPersonDetailLikeConuntRB.setText("喜欢 \n "+items.getLike_count());
                //6.设置推荐的数量
                trendPersonDetailRecommendRB.setText("推荐  \n  "+items.getRecommendation_count() );
                //7.设置关注的数量
                trendPersonDetailCareRB.setText("关注 \n "+items.getFollowing_count());
                //8.设置粉丝数量
                trendPersonDetailFansRB.setText("粉丝 \n "+items.getFollowed_count());

            }
        }

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_trend_person;
    }


}
