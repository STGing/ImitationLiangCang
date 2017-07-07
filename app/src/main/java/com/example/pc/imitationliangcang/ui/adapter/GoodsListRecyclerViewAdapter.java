package com.example.pc.imitationliangcang.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.bean.GoodsListBean;
import com.example.pc.imitationliangcang.ui.activity.GoodsDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC on 2017/7/7.
 */

public class GoodsListRecyclerViewAdapter extends RecyclerView.Adapter<GoodsListRecyclerViewAdapter.GoodsListViewHolder> {
    private final Context mContext;
    private final List<GoodsListBean.DataBean.ItemsBean> items;
    private final LayoutInflater mLayoutInflater;

    public GoodsListRecyclerViewAdapter(Context mContext, List<GoodsListBean.DataBean.ItemsBean> items) {
        this.mContext = mContext;
        this.items = items;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public GoodsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoodsListViewHolder(mLayoutInflater.inflate(R.layout.goods_list_item,null));
    }

    @Override
    public void onBindViewHolder(GoodsListViewHolder holder, int position) {
        //获取数据
        GoodsListBean.DataBean.ItemsBean itemsBean = items.get(position);
        //Log.e("TAG", "适配器中的数据" + itemsBean.getGoods_name());
        //设置数据
        Picasso.with(mContext)
                .load(itemsBean.getGoods_image())
                .into(holder.goodsListItemIv);

        holder.goodsListItemTvName.setText(itemsBean.getGoods_name());
        holder.goodsListItemBrandName.setText(itemsBean.getBrand_info().getBrand_name());
        holder.goodsListItemLikeCount.setText(itemsBean.getLike_count());
        holder.goodsListItemPrice.setText(itemsBean.getPrice());

        //设置点击事件
        holder.setListener(position);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }


    class GoodsListViewHolder extends RecyclerView.ViewHolder{
        private final View view;
        @BindView(R.id.goodsList_item_iv)
        ImageView goodsListItemIv;
        @BindView(R.id.goodsList_item_tvName)
        TextView goodsListItemTvName;
        @BindView(R.id.goodsList_item_brandName)
        TextView goodsListItemBrandName;
        @BindView(R.id.goodsList_item_likeCount)
        TextView goodsListItemLikeCount;
        @BindView(R.id.goodsList_item_price)
        TextView goodsListItemPrice;

        GoodsListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }

        public void setListener(int position){

            GoodsListBean.DataBean.ItemsBean itemsBean = items.get(position);
            final String goods_id = itemsBean.getGoods_id();

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                    intent.putExtra("goods_id",goods_id);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
