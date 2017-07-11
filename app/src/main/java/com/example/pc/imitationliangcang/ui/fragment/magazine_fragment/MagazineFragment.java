package com.example.pc.imitationliangcang.ui.fragment.magazine_fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseFragment;
import com.example.pc.imitationliangcang.bean.magazinefragment.MagazineListBean;
import com.example.pc.imitationliangcang.common.NetWorkUrl;
import com.example.pc.imitationliangcang.ui.activity.MaganizeActivity;
import com.example.pc.imitationliangcang.ui.adapter.magazine.MagazineFragmentAdapter;
import com.google.gson.Gson;

import butterknife.BindView;

/**
 * Created by PC on 2017/7/6.
 */

public class MagazineFragment extends BaseFragment {

    @BindView(R.id.title_tv_date)
    TextView titleTvDate;
    @BindView(R.id.title_tv_magazine)
    TextView titleTvMagazine;
    @BindView(R.id.magazine_fragment_rv)
    RecyclerView magazineFragmentRv;

    private MagazineFragmentAdapter adapter;

    @Override
    public int getLayoutID() {
        return R.layout.magazine_fragment;
    }

    @Override
    public void initTitle() {
        super.initTitle();

        //设置标题
        titleTvMagazine.setVisibility(View.VISIBLE);
        titleTvMagazine.setText("杂志");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        super.initListener();

        //杂志的点击事件
        titleTvMagazine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MaganizeActivity.class);
                mContext.startActivity(intent);
                mContext.overridePendingTransition(R.anim.magazine_slide_in_top, 0);
            }
        });
    }

    @Override
    public String getUrl() {
        return NetWorkUrl.MAGAZINELISTURL;
    }

    @Override
    public void processData(String s) {
        super.processData(s);

        //解析数据
        MagazineListBean.DataBean.ItemsBean items =
                new Gson().fromJson(s, MagazineListBean.class).getData().getItems();

        //设置数据
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        magazineFragmentRv.setLayoutManager(manager);
        adapter = new MagazineFragmentAdapter(getActivity(),items);
        magazineFragmentRv.setAdapter(adapter);

    }

}
