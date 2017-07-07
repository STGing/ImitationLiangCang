package com.example.pc.imitationliangcang.ui.adapter.shopfragment;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.imitationliangcang.R;
import com.example.pc.imitationliangcang.bean.shopfragment.TopicFragmentBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC on 2017/7/7.
 */

public class TopicFragmentRecyclerVIewAdapter extends RecyclerView.Adapter<TopicFragmentRecyclerVIewAdapter.TopicFragmentViewHolder> {

    private final FragmentActivity mContext;
    private final List<TopicFragmentBean.DataBean.ItemsBean> items;
    private final LayoutInflater mLayoutInflater;

    public TopicFragmentRecyclerVIewAdapter(FragmentActivity mContext, List<TopicFragmentBean.DataBean.ItemsBean> items) {
        this.mContext = mContext;
        this.items = items;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public TopicFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopicFragmentViewHolder(mLayoutInflater.inflate(R.layout.topic_fragment_item,null));
    }

    @Override
    public void onBindViewHolder(TopicFragmentViewHolder holder, int position) {
        //设置数据
        TopicFragmentBean.DataBean.ItemsBean itemsBean = items.get(position);
        holder.topicFragmentItemTvName.setText(itemsBean.getTopic_name());

        Picasso.with(mContext)
                .load(itemsBean.getCover_img())
                .into(holder.topicFragmentItemIv);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }


    static class TopicFragmentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.topic_fragment_item_iv)
        ImageView topicFragmentItemIv;
        @BindView(R.id.topic_fragment_item_tvName)
        TextView topicFragmentItemTvName;

        TopicFragmentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
