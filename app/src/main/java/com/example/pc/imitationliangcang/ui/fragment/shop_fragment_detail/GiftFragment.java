package com.example.pc.imitationliangcang.ui.fragment.shop_fragment_detail;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseFragment;
import com.example.pc.imitationliangcang.common.NetWorkUrl;
import com.example.pc.imitationliangcang.ui.activity.GoodsListActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by PC on 2017/7/6.
 */

public class GiftFragment extends BaseFragment {
    @BindView(R.id.gift_fragment_topIv)
    ImageView giftFragmentTopIv;
    @BindView(R.id.gift_fragment_first_oneiv)
    ImageView giftFragmentFirstOneiv;
    @BindView(R.id.gift_fragment_first_twoiv)
    ImageView giftFragmentFirstTwoiv;
    @BindView(R.id.gift_fragment_first_threeiv)
    ImageView giftFragmentFirstThreeiv;
    @BindView(R.id.gift_fragment_second_oneiv)
    ImageView giftFragmentSecondOneiv;
    @BindView(R.id.gift_fragment_second_twoiv)
    ImageView giftFragmentSecondTwoiv;
    @BindView(R.id.gift_fragment_second_threeiv)
    ImageView giftFragmentSecondThreeiv;

    @Override
    public int getLayoutID() {
        return R.layout.shop_fragment_git_fragment;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        super.initListener();

    }

    @OnClick({R.id.gift_fragment_topIv, R.id.gift_fragment_first_oneiv, R.id.gift_fragment_first_twoiv, R.id.gift_fragment_first_threeiv, R.id.gift_fragment_second_oneiv, R.id.gift_fragment_second_twoiv, R.id.gift_fragment_second_threeiv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.gift_fragment_topIv:
                //礼物界面是跳转到商品详情页面，后面再做。
                start(NetWorkUrl.GIFTFRAGMENT_FIRSTPICURL);
                break;
            case R.id.gift_fragment_first_oneiv:
                //节日
                start(NetWorkUrl.GIFTFRAGMENT_HOLIDAY);
                break;
            case R.id.gift_fragment_first_twoiv:
                start(NetWorkUrl.GIFFRAGMENT_LOVE);
                break;
            case R.id.gift_fragment_first_threeiv:
                start(NetWorkUrl.GIFFRAGMENT_BIRTHDAY);
                break;
            case R.id.gift_fragment_second_oneiv:
                start(NetWorkUrl.GIFFRAGMENT_FRIEND);
                break;
            case R.id.gift_fragment_second_twoiv:
                start(NetWorkUrl.GIFFRAGMENT_CHILD);
                break;
            case R.id.gift_fragment_second_threeiv:
                start(NetWorkUrl.GIFFRAGMENT_PARENTS);
                break;
        }
    }


    private void start(String url) {
        Intent intent = new Intent(mContext, GoodsListActivity.class);
        intent.putExtra("topic_url", url);
        mContext.startActivity(intent);
        mContext.overridePendingTransition(R.anim.slide_int_right, R.anim.slide_out_left);
    }
}
