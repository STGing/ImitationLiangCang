package com.example.pc.imitationliangcang.ui.fragment.shop_fragment_detail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.base.BaseFragment;
import com.example.pc.imitationliangcang.bean.shopfragment.BrandFragmentBean;
import com.example.pc.imitationliangcang.common.NetWorkUrl;
import com.example.pc.imitationliangcang.ui.adapter.shopfragment.BrandFragmentRecyclerViewAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;

/**
 * Created by PC on 2017/7/6.
 */

public class BrandFragment extends BaseFragment {

    private BrandFragmentRecyclerViewAdapter adapter;

    @BindView(R.id.brand_fragment_recyclerView)
    RecyclerView brandFragmentRecyclerView;

    @Override
    public int getLayoutID() {
        return R.layout.shop_fragment_brand_fragment;
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
        if (!TextUtils.isEmpty(s)) {
            BrandFragmentBean brandFragmentBean = new Gson().fromJson(s, BrandFragmentBean.class);
            //Log.e("TAG","获取的数据是===="+brandFragmentBean.getData().getItems().get(0).getBrand_name());
            List<BrandFragmentBean.DataBean.ItemsBean> items = brandFragmentBean.getData().getItems();

            //设置适配器及布局
            LinearLayoutManager manager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
            brandFragmentRecyclerView.setLayoutManager(manager);

            adapter = new BrandFragmentRecyclerViewAdapter(mContext,items);
            brandFragmentRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public String getUrl() {
        return NetWorkUrl.BRANDFRAGMENTURL;
    }

}
