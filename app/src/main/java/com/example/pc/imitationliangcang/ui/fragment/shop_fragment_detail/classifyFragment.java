package com.example.pc.imitationliangcang.ui.fragment.shop_fragment_detail;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseFragment;
import com.example.pc.imitationliangcang.bean.shopfragment.ClassifyFragmentBean;
import com.example.pc.imitationliangcang.common.NetWorkUrl;
import com.example.pc.imitationliangcang.ui.adapter.shopfragment.ClassifyFragmentRecyclerViewAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;

/**
 * Created by PC on 2017/7/6.
 */

public class classifyFragment extends BaseFragment {

    @BindView(R.id.classify_recyclerView)
    RecyclerView classifyRecyclerView;

    private ClassifyFragmentRecyclerViewAdapter adapter;

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
        //Log.e("TAG","class====json==="+s);
        ClassifyFragmentBean classifyFragmentBean = new Gson().fromJson(s, ClassifyFragmentBean.class);
        List<ClassifyFragmentBean.DataBean.ItemsBean> items = classifyFragmentBean.getData().getItems();

        //设置适配器，及布局
        GridLayoutManager manager = new GridLayoutManager(mContext,2);
        classifyRecyclerView.setLayoutManager(manager);

        adapter = new ClassifyFragmentRecyclerViewAdapter(mContext,items);
        classifyRecyclerView.setAdapter(adapter);
    }

    @Override
    public String getUrl() {
        return NetWorkUrl.CLASSIFYFRAGMENTURL;
    }

}
