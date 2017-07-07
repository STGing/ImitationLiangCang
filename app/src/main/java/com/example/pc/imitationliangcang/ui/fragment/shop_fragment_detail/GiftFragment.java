package com.example.pc.imitationliangcang.ui.fragment.shop_fragment_detail;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseFragment;
import com.example.pc.imitationliangcang.common.NetWorkUrl;
import com.example.pc.imitationliangcang.ui.activity.GoodsListActivity;

import butterknife.BindView;

/**
 * Created by PC on 2017/7/6.
 */

public class GiftFragment extends BaseFragment {
    @BindView(R.id.gift_fragment_topIv)
    ImageView giftFragmentTopIv;

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

        //顶部图片的点击事件
        giftFragmentTopIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //礼物界面是跳转到商品详情页面，后面再做。

                Intent intent = new Intent(mContext, GoodsListActivity.class);
                intent.putExtra("topic_url", NetWorkUrl.GIFTFRAGMENT_FIRSTPICURL);
                mContext.startActivity(intent);
            }
        });
    }
}
