package com.example.pc.imitationliangcang.ui.adapter.trendperson;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.bean.trenpersonfragment.TrendPersonFragmentBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC on 2017/7/7.
 */

public class TrendPersonFragmentAdaper extends RecyclerView.Adapter<TrendPersonFragmentAdaper.TrendPersonFragmentViewHolder> {

    private final Context mContext;
    private final List<TrendPersonFragmentBean.DataBean.ItemsBean> items;
    private final LayoutInflater mLayoutInflater;

    public TrendPersonFragmentAdaper(Context mContext, List<TrendPersonFragmentBean.DataBean.ItemsBean> items) {
        this.mContext = mContext;
        this.items = items;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public TrendPersonFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TrendPersonFragmentViewHolder(mLayoutInflater.inflate(R.layout.trendperson_fragment_item,null));
    }

    @Override
    public void onBindViewHolder(TrendPersonFragmentViewHolder holder, int position) {
        //设置数据
        TrendPersonFragmentBean.DataBean.ItemsBean itemsBean = items.get(position);

        Picasso.with(mContext)
                .load(itemsBean.getUser_images().getOrig())
                .into(holder.trendPersonItemIv);

        holder.trendPersonItemTvName.setText(itemsBean.getUsername());
        holder.trendPersonItemTvSummary.setText(itemsBean.getDuty());

    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }


    static class TrendPersonFragmentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.trend_person_item_iv)
        ImageView trendPersonItemIv;
        @BindView(R.id.trend_person_item_tvName)
        TextView trendPersonItemTvName;
        @BindView(R.id.trend_person_item_tvSummary)
        TextView trendPersonItemTvSummary;

        TrendPersonFragmentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
