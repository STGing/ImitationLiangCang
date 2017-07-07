package com.example.pc.imitationliangcang.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseFragment;
import com.example.pc.imitationliangcang.bean.trenpersonfragment.TrendPersonFragmentBean;
import com.example.pc.imitationliangcang.common.NetWorkUrl;
import com.example.pc.imitationliangcang.ui.adapter.trendperson.TrendPersonFragmentAdaper;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by PC on 2017/7/6.
 */

public class TrendPersonFragment extends BaseFragment {
    @BindView(R.id.title_iv_search)
    ImageView titleIvSearch;
    @BindView(R.id.title_tv_name)
    TextView titleTvName;
    @BindView(R.id.title_iv_menu)
    ImageView titleIvMenu;
    @BindView(R.id.trend_person_recyclerView)
    RecyclerView trendPersonRecyclerView;

    private TrendPersonFragmentAdaper adaper;

    @Override
    public int getLayoutID() {
        return R.layout.trendperson_fragment;
    }

    @Override
    public void initTitle() {
        super.initTitle();
        //设置标题
        titleIvSearch.setVisibility(View.VISIBLE);
        titleTvName.setText("达人");
        titleIvMenu.setVisibility(View.VISIBLE);
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void processData(String s) {
        super.processData(s);

        //解析数据
        TrendPersonFragmentBean trendPersonFragmentBean = new Gson().fromJson(s, TrendPersonFragmentBean.class);
        List<TrendPersonFragmentBean.DataBean.ItemsBean> items = trendPersonFragmentBean.getData().getItems();

        if (items != null){

            GridLayoutManager manager = new GridLayoutManager(mContext,3);
            trendPersonRecyclerView.setLayoutManager(manager);

            adaper = new TrendPersonFragmentAdaper(mContext,items);
            trendPersonRecyclerView.setAdapter(adaper);
        }
    }

    @Override
    public String getUrl() {
        return NetWorkUrl.TRENDPERSONFRAGMENTURL;
    }

    @OnClick({R.id.title_iv_search, R.id.title_tv_name, R.id.title_iv_menu, R.id.trend_person_recyclerView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_iv_search:
                break;
            case R.id.title_tv_name:
                break;
            case R.id.title_iv_menu:
                break;
            case R.id.trend_person_recyclerView:
                break;
        }
    }
}
