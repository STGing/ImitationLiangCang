package com.example.pc.imitationliangcang.ui.fragment.tend_person;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseFragment;
import com.example.pc.imitationliangcang.bean.trenpersonfragment.TrendPersonDetailBean;
import com.example.pc.imitationliangcang.common.NetWorkUrl;
import com.example.pc.imitationliangcang.ui.adapter.trendperson.TrendPersonDetailAdapter;
import com.google.gson.Gson;

import butterknife.BindView;

/**
 * Created by PC on 2017/7/9.
 */

public class TrendPersonFansFragment extends BaseFragment {

    @BindView(R.id.trend_person_detail_fans_rv)
    RecyclerView trendPersonDetailFansRv;

    private TrendPersonDetailAdapter adapter;

    @Override
    public int getLayoutID() {
        return R.layout.trend_person_fans_fragment;
    }

    @Override
    public void initView() {

    }

    @Override
    public String getUrl() {

        //获得地址
        String uid = getActivity().getIntent().getStringExtra("uid");
        String url = NetWorkUrl.TRENDPERSONDETAILFANSURL01 + uid + NetWorkUrl.TRENDPERSONDETAILFANSURL02;

        return url;
    }

    //处理数据
    @Override
    public void processData(String s) {
        super.processData(s);
        //Log.e("TAG","fragment接受的数据=="+s);

        if (!TextUtils.isEmpty(s)) {

            //处理接受到的数据
            TrendPersonDetailBean.DataBean.ItemsBean items = new Gson().fromJson(s, TrendPersonDetailBean.class).getData().getItems();

            //设置适配器及布局管理
            GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
            trendPersonDetailFansRv.setLayoutManager(manager);

            adapter = new TrendPersonDetailAdapter(getActivity(), items);
            trendPersonDetailFansRv.setAdapter(adapter);

        }

    }

}
