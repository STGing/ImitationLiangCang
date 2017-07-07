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
import com.example.pc.imitationliangcang.bean.shopfragment.BrandFragmentBean;
import com.example.pc.imitationliangcang.ui.activity.BrandGoodsListActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC on 2017/7/6.
 */

public class BrandFragmentRecyclerViewAdapter extends RecyclerView.Adapter<BrandFragmentRecyclerViewAdapter.BrandFragmentViewHolder> {

    private final Context mContext;
    private final List<BrandFragmentBean.DataBean.ItemsBean> items;
    private final LayoutInflater mLayoutInflater;

    public BrandFragmentRecyclerViewAdapter(Context mContext, List<BrandFragmentBean.DataBean.ItemsBean> items) {
        this.mContext = mContext;
        this.items = items;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public BrandFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BrandFragmentViewHolder(mLayoutInflater.inflate(R.layout.brand_fragment_item,null));
    }

    @Override
    public void onBindViewHolder(BrandFragmentViewHolder holder, int position) {
        //设置数据
        BrandFragmentBean.DataBean.ItemsBean bean = items.get(position);

        //设置图片
        Picasso.with(mContext)
                .load(bean.getBrand_logo())
                .into(holder.brandFragmentIv);

        //设置名字
        holder.brandFragmentTvName.setText(bean.getBrand_name());

        //设置点击事件
        holder.setListener(position);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    class BrandFragmentViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.brand_fragment_iv)
        ImageView brandFragmentIv;
        @BindView(R.id.brand_fragment_tvName)
        TextView brandFragmentTvName;

        View view;

        BrandFragmentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            this.view = view;
        }

        public void setListener(int position){

            //获取数据
            final int brand_id = items.get(position).getBrand_id();
            //view的点击事件
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转到品牌商品界面
                    Intent intent = new Intent(mContext, BrandGoodsListActivity.class);
                    //传递数据
                    intent.putExtra("brand_id",brand_id);
                    mContext.startActivity(intent);
                }
            });
        }

    }
}
