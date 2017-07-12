package com.example.pc.imitationliangcang.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.bean.GoodsInfo;

import java.util.List;

/**
 * 购物车RecyclerView的适配器
 */

public class ShopCarAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private final List<GoodsInfo> goodsInfos;
    private final LayoutInflater mLayoutInflater;

    public ShopCarAdapter(Context mContext, List<GoodsInfo> goodsInfos) {
        this.mContext =mContext;
        this.goodsInfos = goodsInfos;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mLayoutInflater.inflate(R.layout.shop_car_item,null,false);
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
