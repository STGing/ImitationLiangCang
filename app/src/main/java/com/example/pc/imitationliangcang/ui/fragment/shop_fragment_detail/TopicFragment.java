package com.example.pc.imitationliangcang.ui.fragment.shop_fragment_detail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseFragment;
import com.example.pc.imitationliangcang.bean.shopfragment.TopicFragmentBean;
import com.example.pc.imitationliangcang.common.NetWorkUrl;
import com.example.pc.imitationliangcang.ui.adapter.shopfragment.TopicFragmentRecyclerVIewAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;

/**
 * Created by PC on 2017/7/6.
 */

public class TopicFragment extends BaseFragment {

    TopicFragmentRecyclerVIewAdapter adapter;

    @BindView(R.id.topic_fragment_recyclerView)
    RecyclerView topicFragmentRecyclerView;

    @Override
    public int getLayoutID() {
        return R.layout.shop_fragment_topic_fragment;
    }

    @Override
    public void initView() {
    }

    @Override
    public void processData(String s) {
        super.processData(s);

        //Log.e("TAG","TopicFragment的数据==="+s);

        if (!TextUtils.isEmpty(s)) {
            //解析数据
            TopicFragmentBean topicFragmentBean = new Gson().fromJson(s, TopicFragmentBean.class);
            List<TopicFragmentBean.DataBean.ItemsBean> items = topicFragmentBean.getData().getItems();

            //设置布局及适配器
            LinearLayoutManager manager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
            topicFragmentRecyclerView.setLayoutManager(manager);

            adapter = new TopicFragmentRecyclerVIewAdapter(mContext,items);
            topicFragmentRecyclerView.setAdapter(adapter);
        }



    }

    @Override
    public void initData() {
        super.initData();
    }



    @Override
    public String getUrl() {
        return NetWorkUrl.TOPICFRAGMENTURL;
    }
}
