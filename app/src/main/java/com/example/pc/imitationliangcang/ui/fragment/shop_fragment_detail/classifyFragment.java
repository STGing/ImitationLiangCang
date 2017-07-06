package com.example.pc.imitationliangcang.ui.fragment.shop_fragment_detail;

import android.support.v7.widget.RecyclerView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseFragment;
import com.example.pc.imitationliangcang.common.NetWorkUrl;

import butterknife.BindView;

/**
 * Created by PC on 2017/7/6.
 */

public class classifyFragment extends BaseFragment {

    @BindView(R.id.classify_recyclerView)
    RecyclerView classifyRecyclerView;


    @Override
    public int getLayoutID() {
        return R.layout.shop_fragment_classify_fragment;
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

    }

    @Override
    public String getUrl() {
        return NetWorkUrl.CLASSIFY;
    }

}
