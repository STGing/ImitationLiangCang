package com.example.pc.imitationliangcang.ui.adapter.shopfragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.bean.shopfragment.BrandFragmentChildBean;
import com.example.pc.imitationliangcang.ui.activity.GoodsDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC on 2017/7/7.
 */

public class BrandGoodsListRecyclerViewAdapter extends RecyclerView.Adapter<BrandGoodsListRecyclerViewAdapter.BrandGoodsListViewHolder> {
    private final Context mContext;
    private final List<BrandFragmentChildBean.DataBean.ItemsBean> items;
    private final LayoutInflater mLayoutInflater;

    public BrandGoodsListRecyclerViewAdapter(Context mContext, List<BrandFragmentChildBean.DataBean.ItemsBean> items) {
        this.mContext = mContext;
        this.items = items;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public BrandGoodsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BrandGoodsListViewHolder( mLayoutInflater.inflate(R.layout.goods_list_item, null));
    }

    @Override
    public void onBindViewHolder(BrandGoodsListViewHolder holder, int position) {
        //设置数据
        BrandFragmentChildBean.DataBean.ItemsBean itemsBean = items.get(position);

        Picasso.with(mContext)
                .load(itemsBean.getGoods_image())
                .into(holder.goodsListItemIv);

        holder.goodsListItemTvName.setText(itemsBean.getGoods_name());
        holder.goodsListItemBrandName.setText(itemsBean.getBrand_info().getBrand_name());
        holder.goodsListItemLikeCount.setText(itemsBean.getLike_count());
        holder.goodsListItemPrice.setText(itemsBean.getPrice());

        //设置item点击事件
        holder.setListener(position);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }


    class BrandGoodsListViewHolder extends RecyclerView.ViewHolder{
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

        BrandGoodsListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }

        public void setListener(int position){
            BrandFragmentChildBean.DataBean.ItemsBean itemsBean = items.get(position);
            //获取商品ID
            final String goods_id = itemsBean.getGoods_id();

            //点击事件
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
