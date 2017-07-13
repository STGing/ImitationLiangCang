package com.example.pc.imitationliangcang.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.bean.GoodsInfo;
import com.example.pc.imitationliangcang.ui.view.AddSubView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 购物车RecyclerView的适配器
 */

public class ShopCarAdapter extends RecyclerView.Adapter<ShopCarAdapter.ShopCarViewHolder> {
    private final Context mContext;
    private final List<GoodsInfo> goodsInfos;
    private final LayoutInflater mLayoutInflater;

    public ShopCarAdapter(Context mContext, List<GoodsInfo> goodsInfos) {
        this.mContext = mContext;
        this.goodsInfos = goodsInfos;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ShopCarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShopCarViewHolder(mLayoutInflater.inflate(R.layout.shop_car_item,null,false));
    }

    @Override
    public void onBindViewHolder(ShopCarViewHolder holder, int position) {
        //获取数据
        GoodsInfo goodsInfo = goodsInfos.get(position);

        //设置数据

    }

    @Override
    public int getItemCount() {
        return goodsInfos == null ? 0 : goodsInfos.size();
    }


    class ShopCarViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.shop_car_item_iv_Check)
        ImageView shopCarItemIvCheck;
        @BindView(R.id.shop_car_item_iv_goodsPic)
        ImageView shopCarItemIvGoodsPic;
        @BindView(R.id.shop_car_item_addsub)
        AddSubView shopCarItemAddsub;
        @BindView(R.id.shop_car_item_tv_brandName)
        TextView shopCarItemTvBrandName;
        @BindView(R.id.shop_car_item_tv_goodName)
        TextView shopCarItemTvGoodName;
        @BindView(R.id.shop_car_item_skull)
        LinearLayout shopCarItemSkull;
        @BindView(R.id.shop_car_item_price)
        TextView shopCarItemPrice;
        @BindView(R.id.shop_car_tv_del)
        TextView shopCarTvDel;
        @BindView(R.id.shop_car_tv_shipPrice)
        TextView shopCarTvShipPrice;

        ShopCarViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
